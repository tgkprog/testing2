package org.s2n.ddt.agent.lanes;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import org.s2n.ddt.agent.AgentThread;
import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.common.share.CommonInformationDefault;
import org.s2n.ddt.excelreader.ReadPlan;
import org.s2n.ddt.pojo.def.TaskListener;
import org.s2n.ddt.pojo.input.Lane;
import org.s2n.ddt.pojo.input.Task;
import org.s2n.ddt.pojo.output.TaskResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.service.impl.TestDao;
import org.s2n.ddt.util.LangUtils;

/**
 * Process one clone
 * */

public class LaneTask implements Runnable {
	private static final Logger logger = Logger.getLogger(LaneProcess.class);

	static final SimpleDateFormat sdf = LangUtils.getSdfForFormat("dd HH mm ss");
	private static final Map<String,  Map<Integer, Map<Integer, String>>> status2 = new HashMap<String,  Map<Integer, Map<Integer, String>>>();//new HashMap<String, String>();
	private static final Map<String, String> status = new HashMap<String, String>();
	private static final StringBuffer sbHist = new StringBuffer();// TODO use StringBuffer or StringBuilder ?

	private final Lane lane;
	private final int cloneId;
	private final LaneProcess lp;

	public LaneTask(Lane lane, int clonId, LaneProcess lp) {
		this.lane = lane;
		this.cloneId = clonId;
		this.lp = lp;
	}

	public void run() {
		CommonInformationDefault commonInfo = new CommonInformationDefault();
		StringBuilder sb = new StringBuilder();
		StringBuilder sbSt = new StringBuilder();
		String agent2Create = UtlConf.getProperty("Agent.create", "task");

		if (lane != null) {
			sb.append(" ");
			sb.append(lane.getLaneName());
			sb.append(" clone ");
			sb.append(cloneId);
		}
		logger.info("Lane-clone-start " + sb);
		Date dt = new Date();
		sbHist.append(lp.getRunResult().getRunName()).append(" Lane clone start at ").append(sdf.format(dt));
		sbHist.append(", ").append(sb).append("\n<br>");
		AgentCache cache = AgentCache.getInstance();
		boolean dbSave = LangUtils.isTrue(UtlConf.getProperty("dbSave", "0"), false);
		List<Task> tasks = lane.getTasks();
		AgentThread agt = null;
		AgentCreate agntWhen = AgentCreate.fromDescription(agent2Create);
		if (agntWhen == AgentCreate.CLONE) {
			agt = new AgentThread();
		}
		logger.warn("agent2Create :" + agent2Create + ", w:" + agntWhen);
		// at least do the lane once + any repeats
		// LANE
		for (int laneRpt = 0; laneRpt <= lane.getIterations(); laneRpt++) {
			if (agntWhen == AgentCreate.CLONE_REPEAT) {
				agt = new AgentThread();
			}
			for (int taskId = 0; taskId < tasks.size(); taskId++) {
				if (agntWhen == AgentCreate.TASK_REPEAT) {
					agt = new AgentThread();
				}
				Task task = tasks.get(taskId);
				task.setLaneId(lane.getLaneId());
				ReadPlan rp = cache.getReader(task.getTestPlanXlsPath(), null);
				TestPlan plan = null;
				if (rp == null) {
					logger.warn("run read plan err ");
					continue;

				} else {
					plan = rp.getTestPlan();
				}

				// at least do the task once + any repeats
				for (int taskRpt = 0; taskRpt <= task.getRepeats(); taskRpt++) {
					if (agntWhen == AgentCreate.TASK) {
						agt = new AgentThread();
					}
					TaskResult tr = new TaskResult();
					tr.setLaneName(lane.getLaneName());
					tr.setLaneCloneId(this.cloneId);
					tr.setLaneRepeatId(laneRpt);
					tr.setTaskRepeatId(taskRpt);
					tr.setTaskName(task.getTaskName());
					tr.setTestSuiteFilePath(task.getTestPlanXlsPath());
					tr.setLaneNo(lane.getLaneNumberInPlan());
					// added for performance
					
					if (LangUtils.isTrue(UtlConf.getProperty("logging.warn.agent2", "0"), true)) {
						sbSt.delete(0, sbSt.length());
						sbSt.append("Lane Name:").append(lane.getLaneName()).append(" LaneRepeat:").append(lane.getIterations())
						.append(" Clones:").append(cloneId).append("TaskNo:").append(taskId).append(" TaskRepeat:")
						.append(taskRpt).append(" TestSuiteFilePath:").append(task.getTestPlanXlsPath());
						logger.warn(sbSt);
					}
					// added for db operations test
					tr.setTaskId(new BigDecimal(taskId + 1));
					tr.setRunResultId(lp.getRunResult().getRunResultId());

					String dbEnable = UtlConf.getProperty("DB.save.taskResult", "0");
					if (LangUtils.isTrue(dbEnable, false)) {
						int taskResultDbId = TestDao.insertTaskResult(tr, taskRpt, laneRpt, this.cloneId, task.getTaskId().intValue());
						tr.setTaskResultId(taskResultDbId);
					}
					// testing ends
					try {
						dt = new Date();
						final StringBuilder sKey = new StringBuilder().append("[").append(lp.getRunResult().getRunName()).append(",").append(lane.getLaneNumberInPlan()).append(",").append(cloneId);
						final StringBuilder sVal = new StringBuilder().append(laneRpt).append(",").append(taskId).append(",").append(taskRpt).append(" start ").append(sdf.format(dt)).append("]\n");
						LaneTask.putStatus(sKey.toString(), sVal.toString());
						agt.init(plan, rp, tr, lp);
						agt.setCommonInfo(commonInfo);
						agt.process();
						dt = new Date();
						sVal.delete(0, sVal.length());
						sVal.append(laneRpt).append(",").append(taskId).append(",").append(taskRpt).append(" done ").append(sdf.format(dt)).append("]\n");
						LaneTask.putStatus(sKey.toString(), sVal.toString());
					} catch (Throwable e) {
						// want to catch class not found etc
						logger.warn("laneTask - Agent process : " + e, e);
					}
					// tr = agt.getTaskResultInstance();
					TaskListener tLstn = lp.getTaskListener();
					if (tLstn != null) {
						tLstn.completed(lane, task, tr, lp.getRunResult());
					}
					if (LaneProcess.isRunOnce()) {
						LaneProcess.done(plan, rp, tr, lp, commonInfo);
					}
				}
			}
		}
		dt = new Date();
		sbHist.append(lp.getRunResult().getRunName()).append(" Lane clone done At ").append(sdf.format(dt)).append(sb).append("\n<br>");
		logger.info("Lane-clone-done " + sb);
	}

	public static String clearHistory() {
		try {
			sbHist.delete(0, sbHist.length());
			return "okay";
		} catch (Exception e) {
			logger.info("Ignoreable Lane history clear exception " + e);
			return "Err :" + e;
		}

	}

	public static String getHistory() {
		return sbHist.toString();

	}

	public static Map<String, String> getStatus() {
		return status;
	}

	public static void putStatus(String id, String status) {
		try {
			LaneTask.status.put(id, status);
		} catch (Exception e) {
			logger.info("Ignoreable Lane status  exception " + e);
		}
	}

	public static String clearStatus() {
		try {
			LaneTask.status.clear();
			return "okay";
		} catch (Exception e) {
			logger.info("Ignoreable Lane status clear exception " + e);
			return "Err :" + e;
		}
	}
	
	private static void ensureStatus(String plan, int lane, int clone){
		
	}
}
