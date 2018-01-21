package exilant.ddt.test;

import java.math.BigDecimal;

import org.s2n.ddt.agent.AgentThread;
import org.s2n.ddt.excelreader.ReadPlan;
import org.s2n.ddt.pojo.output.AgentDetails;
import org.s2n.ddt.pojo.output.AgentRunResult;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.apache.log4j.Logger;

public class DdtRun {
	private static final Logger logger = Logger.getLogger(DdtRun.class);

	void agentSetup() {
		// in real get from db
		System.out.println("inside DDT agentSetup§§");
		org.s2n.ddt.core.Agents.getAgents().clear();

		AgentDetails a1 = new AgentDetails();
		a1.setAgentName("tushar");
		a1.setIp("EXIMR-MB-071.local");// From system information/netowrk/
										// Ethernet - advanced/ WINS
		a1.setPort(8180);

		org.s2n.ddt.core.Agents.putAgent(a1.getAgentName(), a1);
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
			logger.info("Deta  1");
			TestPlan plan = null;
			String path = "/data/ddt/config/TestAppHttp.xls";
			ReadPlan planMK = new ReadPlan();
			plan = planMK.readPlanObj(path); 

			RunResult runr = org.s2n.ddt.core.RunResults.executePlan(plan);
			logger.info("Details :" + args);
			agentSetup();
			BigDecimal pid = new BigDecimal(12345);
			AgentThread agt = new AgentThread(runr.getRunName(), pid, path);
			//AgentThread.start(agt);
			agt.run();
			AgentRunResult arr = agt.getAgentRunResultInstnace();
			org.s2n.ddt.core.RunResults.result(arr);
		} catch (Exception e) {
			logger.warn("e " + e, e);
		}
		

	}

}
