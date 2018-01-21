package org.s2n.ddt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.s2n.ddt.pojo.Runner;
import org.s2n.ddt.pojo.SuiteScenarioMapping;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.TestSuiteId;
import org.s2n.ddt.pojo.TestplanTestscenarioMap;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestPlanId;
import org.s2n.ddt.pojo.output.TestScenario;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;

//import org.s2n.ddt.runner.mock.CoreMock;

public class CoreTestPlan {

	private static final Logger logger = Logger.getLogger(CoreTestPlan.class);
	private static ArrayList<TestCase> cases = new ArrayList<TestCase>();

	public static ArrayList<TestCase> getCases() {
		return cases;
	}

	public void setCases(ArrayList<TestCase> cases) {
		this.cases = cases;
	}

	public static TestPlan TestPlanToSend() {

		Runner runner1 = new Runner();
		runner1.setRunnerId(new BigDecimal(10000));
		runner1.setRunnerName("SA");
		// makeMockTestCase();
		TestPlan testPlan = new TestPlan();
		TestPlanId planId = new TestPlanId();
		planId.setTestPlanId(new BigDecimal(30000));
		planId.setPlanName("Plan GRX");
		ArrayList<TestPlan> testplanList = new ArrayList<TestPlan>();
		ArrayList<TestplanTestscenarioMap> testplanTestscenarioMapsList = new ArrayList<TestplanTestscenarioMap>();
		TestplanTestscenarioMap testplanTestscenarioMap = new TestplanTestscenarioMap();
		TestScenario testscenario = new TestScenario();
		testscenario.setTestScenarioName("Cancelling in GRX");
		ArrayList<SuiteScenarioMapping> suiteScenarioMappingsList = new ArrayList<SuiteScenarioMapping>();
		SuiteScenarioMapping suiteScenarioMapping = new SuiteScenarioMapping();
		ArrayList<TestSuite> testSuitesList = new ArrayList<TestSuite>();
		TestSuite testSuite = new TestSuite();
		// TestSuiteResultSummary testSuiteResultSummary = new
		// TestSuiteResultSummary();
		// TestStepResult testStepResult = new TestStepResult();

		TestSuiteId testsuiteid = new TestSuiteId();
		testsuiteid.setSuiteName("Cancel Order");
		testsuiteid.setTestCases(getCases());
		testSuite.setTestSuiteId(testsuiteid);
		suiteScenarioMapping.setCreatedDateTime(new Date());
		suiteScenarioMapping.setTestSuite(testSuite);
		testSuitesList.add(testSuite);
		suiteScenarioMappingsList.add(suiteScenarioMapping);
		testscenario.setSuiteScenarioMappings(suiteScenarioMappingsList);
		testplanTestscenarioMap.setTestScenario(testscenario);
		testplanTestscenarioMapsList.add(testplanTestscenarioMap);
		planId.setPlanScenarioMappingList(testplanTestscenarioMapsList);
		testPlan.setTestPlanId(planId);
		testplanList.add(testPlan);

		/*
		 * AgentInterface faInterface = (AgentInterface) new Agent();// new
		 * 
		 * System.out.println(" test Plan ID    " +
		 * testplanList.get(0).getTestplanid().getPlanname());
		 * System.out.println(" Test scenario name  " +
		 * testplanList.get(0).getTestplanid
		 * ().getTestplanTestscenarioMaps().get(
		 * 0).getTestscenario().getTestscenarioname());
		 * System.out.println(" Test Suite  name  " +
		 * testplanList.get(0).getTestplanid
		 * ().getTestplanTestscenarioMaps().get(
		 * 0).getTestscenario().getSuiteScenarioMappings().get(0)
		 * .getTestsuite().getTestsuiteid().getSuitename());
		 * System.out.println(" Test Case  name  " +
		 * testplanList.get(0).getTestplanid
		 * ().getTestplanTestscenarioMaps().get(
		 * 0).getTestscenario().getSuiteScenarioMappings().get(0)
		 * .getTestsuite()
		 * .getTestsuiteid().getTestcases().get(0).getCasename());
		 * 
		 * faInterface.startTestPlan(testPlan);
		 * faInterface.startTestPlan(testPlan);
		 * faInterface.startTestSuite(testPlan, testSuite,
		 * testSuiteResultSummary); int stepsToTest[][] = { { 2, 3, 4 }, { 3, 5
		 * } }; boolean[][] stepResultExpected = { { true, true, true }, {
		 * false, true } }; String[][] stepResultMsgsExpected = { { "1", "2",
		 * "3" }, { "4", "5" } };// TODO // some // messages // expected // as
		 * // comments String[][] stepResultAppMsgsExpected = { { "1", "2", "3"
		 * }, { "4", "5" } };// TODO // some // messages // expected // app //
		 * msgs // collected // from // screen int currToTest = 0; boolean
		 * caseResFail = false; for (int tstCaseCntr = 0; tstCaseCntr <
		 * getCases().size(); tstCaseCntr++) { for (int tstStepCtr = 0;
		 * tstStepCtr < getCases().get(tstCaseCntr).getTeststeps().size();
		 * tstStepCtr++) { faInterface.testStep(testPlan, testSuite,
		 * getCases().get(tstCaseCntr),
		 * getCases().get(tstCaseCntr).getTeststeps().get(tstStepCtr),
		 * testStepResult, testSuiteResultSummary); logger.log(Level.INFO,
		 * "did> case :" + tstCaseCntr + ", tst step :" + tstStepCtr + ", l " +
		 * stepsToTest[tstCaseCntr].length + ", currToTest " + currToTest);
		 * 
		 * 
		 * 
		 * } }
		 */
		logger.log(Level.INFO, "cases:" + getCases().size());

		return testPlan;

	}
}
