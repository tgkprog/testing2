package org.s2n.ddt.report.detail;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.def.TestResultTmp;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.AgentRunResult;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestPlanResult;
import org.s2n.ddt.pojo.output.TestScenarioResult;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;

public class Reports {
	private static final Logger logger = Logger.getLogger(Reports.class);

	private Map<String, RunResult> testCaseResultMap = new HashMap<String, RunResult>();

//	private BigDecimal testPlanId;
//	private String runName;
//	private BigDecimal scenarioId;
//	private BigDecimal suiteId;
//	private BigDecimal caseId;
//	private List<BigDecimal> stepId;
//	private List<String> stepName;
//	private Boolean stepResult;
//	private String runnerMsg;
//	private long time;
//	private String detailError;

	public void startTestPlan(RunResult obj) {

		testCaseResultMap.put(obj.getRunName(), obj);
		org.s2n.ddt.pojo.def.PlanMock m = new org.s2n.ddt.pojo.def.PlanMock();
		// obj.set

	}

	public void startTestSuite(TestResultTmp r) {
		try {
			String runName = r.getRunName();
			String agentName = r.getAgentName();
			RunResult testPlanR = testCaseResultMap.get(runName);
			BigDecimal sceId = r.getScenarioId();

			AgentRunResult agentResult = testPlanR.getAgentRunResultFromMap(agentName);
			TestPlanResult planR = agentResult.getPlanResult();
			if (planR == null) {
				planR = new TestPlanResult();
				// planR.setAgentRun(agentRun)
				agentResult.setPlanResult(planR);
			}

			TestScenarioResult scResult = planR.getScenarioResult(sceId);

			if (scResult == null) {
				scResult = new TestScenarioResult();
				BigDecimal sid = new BigDecimal(1);
				scResult.setTestScenarioResultId(sid);
				// planR.putSuiteResultsM(sid, scResult);
			}

		} catch (Exception e) {
			logger.log(Level.WARN, "Err " + e, e);
		}
	}

	public void startTestCase(TestResultTmp r) {
		try {

		} catch (Exception e) {
			logger.log(Level.WARN, "Err " + e, e);
		}
	}

	public void testStep(TestPlan obj, TestSuite suite, TestCase testCase, TestStep step, TestStepResult resultFromRemote,
			TestSuiteResultSummary tstSmmryFromCache) {
	}

	public void endTestCase(TestPlan obj, TestSuite suite, TestCase testCase, TestSuiteResultSummary tstSmmryFromCache, TestStepResult testStep) {
	}

	public void planEnd(RunResult runRslt) throws Exception {
		logger.info("Plan End get called ......");
		ReportHelper.getDetails(runRslt);
		DetailedReportHelper.getDetails(runRslt);
		
	}

	public void stepEnd(TestResultTmp result) {

	}

}
