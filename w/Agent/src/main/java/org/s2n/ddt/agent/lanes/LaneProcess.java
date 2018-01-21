package org.s2n.ddt.agent.lanes;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.common.share.CommonInformationDefault;
import org.s2n.ddt.excelreader.ReadPlan;
import org.s2n.ddt.pojo.def.TaskListener;
import org.s2n.ddt.pojo.input.Lane;
import org.s2n.ddt.pojo.input.MasterPlan;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.pojo.output.TaskResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.service.impl.TestDao;
import org.s2n.ddt.util.LangUtils;
import org.s2n.ddt.util.threads.PoolOptions;
import org.s2n.ddt.util.threads.DdtPools;

/**
 * Process lane as Runnable so we can detach from calling code. TODO : Need so we can monitor lane 0 for before clone tasks, we start clones only after
 * clone tasks have completed in lane 0. And we do not process them in clones.
 * 
 * @author Tushar Kapila
 * 
 */
public class LaneProcess implements Runnable {
	private static final Logger logger = Logger.getLogger(LaneProcess.class);

	private TaskListener taskListn;

	private boolean modeOneJvm;
	private RunResult runRlt;
	private Lane lane;

	private static boolean runOnce = false;

	public static final String AGENT_LANES_POOL = "agentLanes";

	static {
		// reinitPool();
	}

	private static String getPoolName(Lane lane, RunResult runr) {
		return AGENT_LANES_POOL + lane.getLaneName() + runr.getRunName();
	}

	private void initPool(Lane lane, RunResult runr) {
		final String poolName = getPoolName(lane, runr);

		// String smax = UtlConf.getProperty("agent.lanePoolSize", "16");

		int max = lane.getClones() + 1;
		logger.warn("pool :" + poolName + ", run :" + runr.getRunName() + ", " + runr.getMasterReportName() + ", threads from lane clones :" + max);
		PoolOptions options = new PoolOptions();
		options.setMaxThreads(max);
		options.setCoreThreads(max);
		DdtPools.initPool(poolName, options);
	}

	public TaskListener getTaskListn() {
		return taskListn;
	}

	public void setTaskListn(TaskListener taskListn) {
		this.taskListn = taskListn;
	}

	public boolean isModeOneJvm() {
		return modeOneJvm;
	}

	public void setModeOneJvm(boolean modeOneJvm) {
		this.modeOneJvm = modeOneJvm;
	}

	/** 
	 * Initializes pool for this job and kicks off starting of lanes asynchronously. 
	 * Same pool is used to kick of clones, as to process clones.
	 * Can reuse the pool name as only 1 thread will kick off all, and by the time 
	 * all jobs are done the kick off job will be done too
	 * **/
	public void process(Lane l2, boolean sing, TaskListener tl, RunResult rr) {
		this.lane = l2;
		this.runRlt = rr;
		this.taskListn = tl;
		setModeOneJvm(sing);
		
		final String poolName = getPoolName(lane, runRlt);
		DdtPools.offer(poolName, this);
	}

	public void run() {
		final String poolName = getPoolName(lane, runRlt);
		// Inserting to DB
		int clones = lane.getClones();

		initPool(lane, runRlt);
		dbLane(lane);
		StringBuilder sbLog = new StringBuilder();
		sbLog.append("Agent int id:").append(System.getProperty("Agent.self.id"));
		sbLog.append("Starting lane on agent:").append(lane.getAgentName()).append(", lid ").append(lane.getLaneId()).append(", l nm :")
				.append(lane.getLaneName()).append(", numbr plan: ").append(lane.getLaneNumberInPlan()).append(", clones :").append(lane.getClones());
		logger.warn(sbLog);
		for (int cloneId = 0; cloneId <= clones; cloneId++) {
			LaneTask lt = new LaneTask(lane, cloneId, this);
			waitBeforeLaneStart(cloneId);	
			if (isRunOnce()) {
				lt.run();
			} else {
				DdtPools.offer(poolName, lt);
			}
		}

	}

	private void dbLane(Lane l2) {
		String dbEnable = UtlConf.getProperty("DB.save.laneResult", "0");
		if (LangUtils.isTrue(dbEnable, false)) {
			int lId = TestDao.insertLane(l2);
			l2.setLaneId(lId);
		}

	}

	public RunResult getRunResult() {
		return runRlt;
	}

	public TaskListener getTaskListener() {
		return this.taskListn;
	}

	/**
	 * in core, when starting a new master plan go thru all lanes and call each unique agent once if its included in the master plan's lanes
	 * */
	public void masterPlanStart(RunResult runr, MasterPlan mplan) {
		AgentCache.getInstance().clear();
	}

	/**
	 * Stop a master plan, timeout is the max time to stop, if 0 then immediate, if less than 0 then its max int.
	 * 
	 * @param runr
	 * @param mplan
	 * @param timeout
	 * @param hardStop
	 */

	public static String masterPlanStop(RunResult runr, Lane lane, int timeout, boolean hardStop) {
		String poolName = null;
		StringBuilder msg = new StringBuilder();
		try {

			poolName = getPoolName(lane, runr);
			// TODO set flag to shut down in agent2
			ThreadPoolExecutor tpe = DdtPools.getPool(poolName);
			tpe.shutdown();
			if (hardStop) {
				int tim = 2000;
				String sTim = UtlConf.getProperty("POOL_SHUTDOWN_TIMEOUT", tim + "");
				tim = LangUtils.getInt(sTim, tim, "timeout for shutdown of master pool : " + poolName);
				// TODO put this in a task with time out
				if (!tpe.awaitTermination(tim, TimeUnit.MILLISECONDS)) {
					msg.append("Shutdown now<br>");
					tpe.shutdownNow();
				}
				List<String> rt = org.s2n.ddt.util.threads.ThrdsStop.stopThatMatch(poolName, tpe);
				for (String ss : rt) {
					msg.append(ss + "<br>");
				}
			}
		} catch (Throwable e) {
			logger.warn("shutdown pool : " + poolName + ", " + e, e);
		}
		return msg.toString();
	}

	public static boolean isRunOnce() {
		return runOnce;
	}

	/*** For running unattended with no clones, one lane, from command line, support for process stops after run. */
	public static void setRunOnce(boolean runOnce) {
		LaneProcess.runOnce = runOnce;
	}

	public static void done(TestPlan plan, ReadPlan rp, TaskResult tr, LaneProcess lp, CommonInformationDefault commonInfo) {
		if (isRunOnce()) {
			try {
				logger.warn("RunOnce :Stopping threads in preperation of exit");
				masterPlanStop(lp.runRlt, lp.lane, 300, true);
				LangUtils.sleep(300);
			} catch (Throwable e) {
				logger.warn("RunOnce stopping : " + e, e);
			}
			logger.warn("RunOnce :exit, bye");
			System.out.println("RunOnce :exit, bye.");
			System.exit(0);
		}

	}

	public void waitBeforeLaneStart(int cloneId) {
		//TODO Config based on master plan, OR app, and type of test (functional, unit or performance)
		Date dt = new Date();
		final StringBuilder sKey = new StringBuilder().append("[").append(getRunResult().getRunName()).append(",").append(lane.getLaneNumberInPlan()).append(",").append(cloneId);
		final StringBuilder sVal = new StringBuilder().append("Before clone start ").append(LaneTask.sdf.format(dt)).append("]\n");
		LaneTask.putStatus(sKey.toString(), sVal.toString());
		String sleepEnabledS = UtlConf.getProperty("AGENT.CLONES.SLEEP.ENABLED", null);
		boolean sleepEnabled = LangUtils.isTrue(sleepEnabledS, false);
		if (!sleepEnabled) {
			return;
		}
		if(cloneId == 0 ){
			sleepEnabledS = UtlConf.getProperty("AGENT.CLONE0.SLEEP.ENABLED", null);
			sleepEnabled = LangUtils.isTrue(sleepEnabledS, false);
			if (!sleepEnabled) {
				return;
			}
		}
		try {
			String sleepS = null;
			sleepS = UtlConf.getProperty("AGENT.CLONES.SLEEP." + cloneId, null);
			if (sleepS == null) {
				sleepS = UtlConf.getProperty("AGENT.CLONES.SLEEP.ALL", "1");
			}
			int slp = LangUtils.getInt(sleepS, 1, "sleep amt for clone " + cloneId);
			if(slp > 0){
				logger.warn("INFO : Ramp up for " + cloneId + ", is " + slp);
				Thread.sleep(slp);
			}
		} catch (Exception e) {
			logger.warn("Sleep before clone, clone id " + cloneId + ", " + e, e);
		}

	}
}
