package org.s2n.ddt.run;

import java.io.File;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import org.s2n.ddt.agent.lanes.LaneProcess;
import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.bean.UtlProps;
import org.s2n.ddt.core.RunResults;
import org.s2n.ddt.core.master.MasterExecute;
import org.s2n.ddt.pojo.def.TaskListener;
import org.s2n.ddt.pojo.input.Lane;
import org.s2n.ddt.pojo.input.MasterPlan;
import org.s2n.ddt.pojo.input.Task;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.pojo.output.TaskResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.runner.http.HttpRunner;
import org.s2n.ddt.util.LangUtils;

public class MasterRun implements TaskListener {
	static {

		System.setProperty("log4j.debug", "true");
		// System.setProperty("log4j.config", "./log4j.xml");
		org.s2n.ddt.util.DdtIoUtls.printCurrentFolderName();
		// LangUtils.sleep(3000);

	}
	
	private static final Logger logger = Logger.getLogger(MasterRun.class);
	// "/data/ddt/config/master/DdtMasterPlanOneTaskOneLaneDefault.xls";//"/data/ddt/config/master/DdtMasterPlanGrx_performance.xls";
	/*
	 * /data/ddt/config/master/master-grx.xls /data/ddt/config/master/DdtMasterPlanOneTaskOneLaneDefault.xls
	 */
	private static String masterXls = "/data/ddt/config/master/DdtMasterPlanOneTaskOneLaneDefault.xls";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		org.s2n.ddt.util.LangUtils.log4Default(true);
		String arg1 = "none";
		if(args.length > 0){
			arg1 = args[0];
		}
		logger.info("Master Plan execute (v1.6.3):" + arg1);
		logger.info("Agent " + org.s2n.ddt.agent.AgentThread.getVersion());
		logger.info("Core " + org.s2n.ddt.core.RunResults.getVersion());
		try {
			logger.info("HttpRunner " + HttpRunner.getVersion());
		} catch (Throwable e) {
			// seperate try catch so even if running without this runner will continue
			logger.warn("HttpRunner not in cp : " + e);
		}
		
		if (args.length < 1) {
			args = new String[2];
			args[0] = masterXls;
		}
		logger.info("master Xls " + args[0]);
		/*if (args.length >= 2) {
			File propsF = new File(args[1]);
			 props = new UtlProps(propsF);
		
		}else{
			props = new UtlProps(null);
		}*/
		//For running unattended with no clones, one lane, from command line, so that process stops after run.
		boolean stopAfter = LangUtils.isTrue(UtlConf.getProperty("RUN_ONCE"), false);
		logger.info("Stop after single lane : " + stopAfter);
		LaneProcess.setRunOnce(stopAfter);
		org.s2n.ddt.agent.Agent2.setMockRunner(false);
		MasterRun ddt = new MasterRun();
		ddt.do1(args);
		LangUtils.sleep(2100);
		// System.exit(1);

	}

	void do1(String[] args) {

		// TODO Auto-generated method stub
		// PlanMock pm = new PlanMock();
		// pm.makeMockTestCase1();
		// TestPlan tp = pm.createPlan("Plan1");
		try {
			org.s2n.ddt.agent.Agent2.setCoreUrl(null);
			// logger.info("Deta  1");
			TestPlan plan = null;

			org.s2n.ddt.core.master.MasterExecute coreMaster = new MasterExecute();
			// coreMaster.setLaneListener(laneListener);
			String path = args[0];
			String appid = "grx";
			String planName = "master1";
			BigInteger plnId = null;
			MasterPlan mp = coreMaster.readMasterPlan(path, appid, planName, plnId);
			RunResult rr = RunResults.initPlan(mp);
			rr.setSummaryReport(false);
			rr.setDetailReport(true);
			coreMaster.planDo(path, appid, planName, plnId, rr, null, false);
			// = coreMaster.getMasterPlan();

			List<Lane> lanes = mp.getLanes();
			org.s2n.ddt.agent.lanes.LaneProcess lp = new org.s2n.ddt.agent.lanes.LaneProcess();
			for (int laneId = 0; laneId < lanes.size(); laneId++) {
				Lane lane = lanes.get(laneId);
				lp.process(lane, true, this, rr);
			}

		} catch (Exception e) {
			logger.warn("MasterRun e " + e, e);
		}

	}

	public void completed(Lane lane, Task task, TaskResult tskResult, RunResult rr) {
		RunResults.result(tskResult, rr);

	}

	
}
