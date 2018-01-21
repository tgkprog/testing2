package org.s2n.ddt.agent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.s2n.ddt.pojo.Feature;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.AgentRunResult;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResult;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;
import org.s2n.ddt.utils.DataConstants;

public class DataSetsAgent implements org.s2n.ddt.pojo.def.DdtRemoteInterface2 {
	private static final Logger logger = Logger.getLogger(DataSetsAgent.class);
	private AgentRunResult result;
	private Agent2 agent2;
	private TestSuiteResult currentTestSuiteResult;
	private TestCaseResult caseResult;
	private TestStepResult stepResult;
	private List<TestStepResult> testStepResults;
	private boolean failed;
	private long afterEnd;
	private long beforeStart;
	private long beforeSuiteStart;

	DataSetsAgent(AgentRunResult result, Agent2 agent2) {
		this.agent2 = agent2;
		this.result = result;
	}

	
	public void startTestPlan(TestPlan plan) {

	}

	public void runDataSet(TestPlan plan) {

	}

	
	public void startTestSuite(TestPlan plan, TestSuite suite, TestSuiteResultSummary tstSmmryFromRemote) {
		logger.info("Start TestSuite iterating ");
		currentTestSuiteResult = result.getPlanResult().getTestScenarioResults().get(0).getSuiteResultsList().get(0);
		beforeSuiteStart = System.currentTimeMillis();

	}

	
	public void startTestCase(TestPlan plan, TestSuite suite, TestSuiteResultSummary suiteSumamryResult, TestCase tcase) {
		logger.info("Inside Test Case data agent");
		Date dt = new Date();
		failed = false;
		logger.info("Start test cases");
		caseResult = new TestCaseResult();
		testStepResults = new ArrayList<TestStepResult>();
		caseResult.setTestCaseId(tcase.getTestCaseId());
		caseResult.setTestCaseName(tcase.getCaseName());
		caseResult.setDescription(tcase.getDescription());
		caseResult.setPlanRunStartDateTime(dt);
		beforeStart = System.currentTimeMillis();

		if (tcase.getActive() == 0) {
			// skipCount = skipCount+1;
			caseResult.setActive(0);
			caseResult.setTestCaseResult("Skipped");
			caseResult.setRunStatus(DataConstants.USER_SKIPPED);
			caseResult.setTimeDuration((double) 0);
		}
		logger.info("Case res  " + caseResult);
	}

	
	public void testStep(TestPlan plan, TestSuite suite, TestCase tcase, TestCaseResult caseResult1, TestStep step, TestStepResult stepResult1,
			TestSuiteResultSummary suiteSumamryResult) {
		String stepErrorMsg = "";
		String stepSuccessMsg = "";
		try {
			stepResult = new TestStepResult();
			// testStepResult.setTestStep(step);//memory
			// need this? TODO
			if (step.getActions() != null) {
				logger.log(Level.INFO, "getActionname :" + step.getActions().getActionName());

			} else {
				logger.info("Action obj null");
			}
			// final id can be be null but
			// containing hierarchy should be non
			// null for every step
			String obj1Id = step.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			if (obj1Id == null || obj1Id.length() == 0 || obj1Id.equalsIgnoreCase("NoObjectFound")) {
				String objRealName = step.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getObjectName();
				logger.info(objRealName + " not found in Object Map");
				stepResult.setComment(objRealName + " not found in Object Map");
				stepResult.setResult(false);
				stepResult.setTimeTaken(1);
				stepResult.setTestStep(step);

			} else {
				if (!failed) {
					logger.info("caseResult  " + caseResult);
					agent2.testStep(plan, suite, tcase, caseResult, step, stepResult, suiteSumamryResult);
					logger.info("Called the respective Runner & testStepResult is =" + stepResult.getResult());
				} else {
					stepResult.setResult(false);
					stepResult.setComment("skipping step as predecssar failed");
				}

			}
			testStepResults.add(stepResult);
			if (!stepResult.getResult()) {
				failed = true;
				stepErrorMsg = stepResult.getComment();
			} else {
				stepSuccessMsg = stepResult.getComment();
			}
		} catch (Exception e) {
			logger.warn("step error " + e, e);
		}
		// steps
		if (stepErrorMsg != null && !stepErrorMsg.equals("")) {
			caseResult.setDescription(stepErrorMsg);
			caseResult.setErrorMsg(stepErrorMsg);
		} else {
			caseResult.setDescription(stepSuccessMsg);

		}
	}

	public void endTestCase(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult2, TestStepResult lastStep) {
		logger.info("End test cases");
		caseResult.setTestStepResultsList(testStepResults);
		Iterator<TestStepResult> itr1 = caseResult.getTestStepResultsList().iterator();
		int passCount = 0;
		int failCount = 0;
		while (itr1.hasNext()) {
			TestStepResult testStepRslt = itr1.next();
			if (testStepRslt.getResult()) {
				passCount++;
			} else {
				failCount++;

			}
		}

		if (testStepResults.size() == passCount) {
			caseResult.setRunStatus(DataConstants.PASSED_AFTER_RUN);
			caseResult.setTestCaseResult("Pass");

		} else if (failCount > 0) {
			caseResult.setRunStatus(DataConstants.FAILED_AFTER_RUN);
			caseResult.setTestCaseResult("Failed");

		}
		caseResult.setTotalCount(caseResult.getTestStepResultsList().size());
		caseResult.setPassCount(passCount);
		caseResult.setFailCount(failCount);
		caseResult.setDescription(testCase.getDescription());
		caseResult.setTestCase(testCase);
		afterEnd = System.currentTimeMillis();
		double d = afterEnd - beforeStart;
		caseResult.setTimeDuration(d);
		currentTestSuiteResult.getCaseResultsList().add(caseResult);

	}

	public void endTestSuite(TestPlan plan, TestSuite suite, TestSuiteResult ddd) {
		currentTestSuiteResult.setSkipCount(-currentTestSuiteResult.getSkipCount());
		currentTestSuiteResult.setPassCount(-currentTestSuiteResult.getPassCount());
		currentTestSuiteResult.setFailCount(-currentTestSuiteResult.getFailCount());
		currentTestSuiteResult.setNotExecuted(-currentTestSuiteResult.getNotExecuted());
		currentTestSuiteResult.setTotalCount(-currentTestSuiteResult.getTotalCount());
		logger.info("End of testsuite ");
		Iterator<TestCaseResult> itr2 = currentTestSuiteResult.getCaseResultsList().iterator();

		while (itr2.hasNext()) {
			TestCaseResult testCasRsl = itr2.next();
			if (testCasRsl.getRunStatus() == 0) {
				currentTestSuiteResult.setSkipCount(1);
			} else if (testCasRsl.getRunStatus() == 1) {
				currentTestSuiteResult.setPassCount(1);
			} else if (testCasRsl.getRunStatus() == 2) {
				currentTestSuiteResult.setFailCount(1);
			} else if (testCasRsl.getRunStatus() == 3) {
				currentTestSuiteResult.setNotExecuted(1);
			}
		}
		long afterSuiteEnd = System.currentTimeMillis();

		double d1 = afterSuiteEnd - beforeSuiteStart;
		currentTestSuiteResult.setTimeDuration(d1);
		currentTestSuiteResult.setTotalCount(currentTestSuiteResult.getCaseResultsList().size());
		currentTestSuiteResult.getTotalCount();
		currentTestSuiteResult.setSuiteName(suite.getTestSuiteId().getSuiteName());
		currentTestSuiteResult.setSuiteId(suite.getTestSuiteId().getTestSuiteId());
		currentTestSuiteResult.setSuitSatus("Complete");
		// // currentTestSuiteListResult.add(currentTestSuiteResult);
		// result.getPlanResult().getTestScenarioResults().get(0).getSuiteResultsList();
		// //
		// testScenarioResult.setSuiteResultsList(currentTestSuiteListResult);
		// // logger.info("TestScenario result   " + testScenarioResult);
		result.getPlanResult().getFunctionalList().get(0).getFeatures().get(0)
				.setPassCount(-result.getPlanResult().getFunctionalList().get(0).getFeatures().get(0).getPassCount());
		result.getPlanResult().getFunctionalList().get(0).getFeatures().get(0)
				.setFailCount(-result.getPlanResult().getFunctionalList().get(0).getFeatures().get(0).getFailCount());
		result.getPlanResult().getFunctionalList().get(0).getFeatures().get(0)
				.setTotalCount(-result.getPlanResult().getFunctionalList().get(0).getFeatures().get(0).getTotalCount());
		result.getPlanResult().getFunctionalList().get(0).getFeatures().get(0)
				.setSkipCount(-result.getPlanResult().getFunctionalList().get(0).getFeatures().get(0).getSkipCount());
		Iterator<TestSuiteResult> iterator2 = result.getPlanResult().getTestScenarioResults().get(0).getSuiteResultsList().iterator();
		while (iterator2.hasNext()) {
			TestSuiteResult suitResult = (TestSuiteResult) iterator2.next();
			if (suitResult != null) {
				result.getPlanResult().getFunctionalList().get(0).getFeatures().get(0).setPassCount(suitResult.getPassCount());
				result.getPlanResult().getFunctionalList().get(0).getFeatures().get(0).setFailCount(suitResult.getFailCount());
				result.getPlanResult().getFunctionalList().get(0).getFeatures().get(0).setTotalCount(suitResult.getTotalCount());
				result.getPlanResult().getFunctionalList().get(0).getFeatures().get(0).setSkipCount(suitResult.getSkipCount());
			}
		}
		logger.info("feature Cases >>" + result.getPlanResult().getFunctionalList().get(0).getFeatures().get(0).getTotalCount());

		result.getPlanResult().getFunctionalList().get(0).setPassCount(-result.getPlanResult().getFunctionalList().get(0).getPassCount());
		result.getPlanResult().getFunctionalList().get(0).setFailCount(-result.getPlanResult().getFunctionalList().get(0).getFailCount());
		result.getPlanResult().getFunctionalList().get(0).setTotalCount(-result.getPlanResult().getFunctionalList().get(0).getTotalCount());
		result.getPlanResult().getFunctionalList().get(0).setSkipCount(-result.getPlanResult().getFunctionalList().get(0).getSkipCount());
		Iterator<Feature> iterator3 = result.getPlanResult().getFunctionalList().get(0).getFeatures().iterator();
		while (iterator3.hasNext()) {

			Feature featureResult = (Feature) iterator3.next();

			if (featureResult != null) {
				result.getPlanResult().getFunctionalList().get(0).setPassCount(featureResult.getPassCount());
				result.getPlanResult().getFunctionalList().get(0).setFailCount(featureResult.getFailCount());
				result.getPlanResult().getFunctionalList().get(0).setTotalCount(featureResult.getTotalCount());
				result.getPlanResult().getFunctionalList().get(0).setSkipCount(featureResult.getSkipCount());
			}
		}
		logger.info("functional Cases>>" + result.getPlanResult().getFunctionalList().get(0).getTotalCount());
		// features

		result.getPlanResult().setFunctionalList(plan.getTestPlanId().getFunctionals());
		result.getPlanResult().setTestplanid(plan.getTestPlanId().getTestPlanId());
		logger.info("Plan Name  " + plan.getTestPlanId().getPlanName());
		result.getPlanResult().setTestPlanRunName(plan.getTestPlanId().getPlanName());
		logger.info("  Plan result  " + result.getPlanResult().getTestScenarioResults().get(0).getSuiteResultsList().get(0).getSuiteName());
		result.setPlanResult(result.getPlanResult());
		logger.info("result  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  " + result);
	}

	public String getConfigPath() {
		return null;
	}

	public void setConfigPath(String configPath) {

	}

}
