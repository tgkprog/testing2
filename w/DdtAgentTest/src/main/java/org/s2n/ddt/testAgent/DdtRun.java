package org.s2n.ddt.testAgent;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import org.s2n.ddt.agent.AgentThread;
import org.s2n.ddt.excelreader.ReadPlan;
import org.s2n.ddt.pojo.output.AgentDetails;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.pojo.output.TaskResult;
import org.s2n.ddt.pojo.output.TestPlan;

public class DdtRun {
	static {
		
		System.setProperty("log4j.debug", "true");
		org.s2n.ddt.util.DdtIoUtls.printCurrentFolderName();
		//LangUtils.sleep(3000);
		
	}
	private static final Logger logger = Logger.getLogger(DdtRun.class);
	
	private static String testcasesXls = "/data/ddt/config/TestAppHttpGrx2.xls";

	void agentSetup() {
		org.s2n.ddt.run.AgentSetup as1 = new org.s2n.ddt.run.AgentSetup();
		as1.reinit();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		org.s2n.ddt.agent.Agent2.setMockRunner(false);
		DdtRun ddt = new DdtRun();
		ddt.do1(args);

	}

	void do1(String[] args) {

		// TODO Auto-generated method stub
		// PlanMock pm = new PlanMock();
		// pm.makeMockTestCase1();
		// TestPlan tp = pm.createPlan("Plan1");
		try {

			
			org.s2n.ddt.agent.Agent2.setCoreUrl(null);

			//logger.info("Deta  1");
			TestPlan plan = null;

			

			String path = "/Users/support/Downloads/TestAppHttpGrxlatest.xls";

			ReadPlan planMK = new ReadPlan();

			//plan = planMK.readPlanObj(testcasesXls, null);

			plan = planMK.readPlanObj(path,null);


			RunResult runr = org.s2n.ddt.core.RunResults.executePlan(plan);
			//logger.info("Details :" + args);
			agentSetup();
			BigDecimal pid = new BigDecimal(12345);
			org.s2n.ddt.agent.lanes.LaneProcess lp = new org.s2n.ddt.agent.lanes.LaneProcess();
			lp.setModeOneJvm(true);
			//lp.setTaskListn(this);
			AgentThread agt = new AgentThread(runr.getRunName(), pid, testcasesXls, lp);
			//AgentThread.start(agt);
			agt.process();

		
			TaskResult tr = agt.getTaskResult();
			org.s2n.ddt.core.RunResults.result(tr, runr);
			
		} catch (Exception e) {
			logger.warn("e " + e, e);
		}
		

	}

}
