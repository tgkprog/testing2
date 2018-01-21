package org.s2n.ddt.pojo.def;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import org.s2n.ddt.pojo.Actions;
import org.s2n.ddt.pojo.Runner;
import org.s2n.ddt.pojo.SuiteScenarioMapping;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.TestSuiteId;
import org.s2n.ddt.pojo.TestplanTestscenarioMap;
import org.s2n.ddt.pojo.input.Objects;
import org.s2n.ddt.pojo.input.ObjectsId;
import org.s2n.ddt.pojo.input.Param;
import org.s2n.ddt.pojo.input.ParamGroup;
import org.s2n.ddt.pojo.input.ParamGroupId;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestParamData;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.input.TestStepId;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestPlanId;
import org.s2n.ddt.pojo.output.TestScenario;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;

/**
 * Test Class to make a TestPlan for runner/ agent/ core/ pojo testing - tusahr
 * */
@SuppressWarnings("unused")
public class PlanMock {

	private static final Logger logger = Logger.getLogger(PlanMock.class);
	private ArrayList<TestCase> cases = new ArrayList<TestCase>();
	private TestPlan testPlan = null;

	public static void main(String[] args) {
		PlanMock coreMock = new PlanMock();
	}

	public static void startOnThread(TestPlan plan) {

	}

	public void makeMockTestCase1() {
		TestCase testCase1 = new TestCase();
		// testcase for login (Authentication)

		ArrayList<TestStep> testStepsList1 = new ArrayList<TestStep>();
		testCase1.setCaseName("Logging into Grx application");
		testCase1.setCreatedBy("abc");
		testCase1.setTestCaseId(new BigDecimal(1234));
		testCase1.setActive(Integer.valueOf(1));

		TestStep testSteps1 = new TestStep();
		TestStepId teststepid = new TestStepId();
		teststepid.setStepName("Launching the browser");
		teststepid.setCreatedBy("abc");
		teststepid.setTestStepId(new BigDecimal(50000));
		TestParamData testparamdatas = new TestParamData();
		Param param = new Param();
		param.setParamName("Firefox" + "");
		ObjectsId objIdBtn1 = new ObjectsId();
		objIdBtn1.setIndentifier("NoObject");// id in html
		Objects objBtn1 = new Objects();
		ParamGroupId paramGroupId = new ParamGroupId();
		ParamGroup paramGroup = new ParamGroup();
		paramGroup.setParamGroupId(paramGroupId);
		param.setObjects(objBtn1);
		param.setParamGroup(paramGroup);
		paramGroup.getParamGroupId().getParamsList().add(param);
		paramGroupId.setParamGroupName("PG_1");// some helpful name
		objBtn1.setObjectsId(objIdBtn1);
		testparamdatas.setParam(param);
		ArrayList<TestParamData> testParamDataList = new ArrayList<TestParamData>();
		testParamDataList.add(testparamdatas);
		teststepid.setTestParamDatas(testParamDataList);

		// Object objIdInHtml =
		// testSteps1.getParamgroupByInputparam().getParamgroupid().getParams().get(0).getObject().getObjectsId().getIndentifier();

		testSteps1.setTestStepId(teststepid);
		Actions actions1 = new Actions();
		actions1.setActionId(new BigDecimal(20000));
		actions1.setActionName("launchBrowser");
		testSteps1.setActions(actions1);
		testStepsList1.add(testSteps1);

		// Second Step

		TestStep testSteps2 = new TestStep();
		TestStepId teststepid2 = new TestStepId();
		teststepid2.setStepName("Launching the application");
		teststepid2.setCreatedBy("abc");
		teststepid2.setTestStepId(new BigDecimal(50001));
		TestParamData testparamdatas2 = new TestParamData();
		Param param2 = new Param();
		param2.setParamName("http://ma-grxd-lapp03.corp.apple.com:58080/grx-web/");
		ObjectsId objectsId2 = new ObjectsId();
		objectsId2.setIndentifier("NoObject");// id in html
		Objects objects2 = new Objects();
		ParamGroupId paramGroupId2 = new ParamGroupId();
		ParamGroup paramGroup2 = new ParamGroup();
		paramGroup2.setParamGroupId(paramGroupId2);
		param2.setObjects(objects2);
		param2.setParamGroup(paramGroup2);
		paramGroup2.getParamGroupId().getParamsList().add(param2);
		paramGroupId2.setParamGroupName("PG_2");// some helpful name
		objects2.setObjectsId(objectsId2);
		testparamdatas2.setParam(param2);
		ArrayList<TestParamData> testParamDataList2 = new ArrayList<TestParamData>();
		testParamDataList2.add(testparamdatas2);
		teststepid2.setTestParamDatas(testParamDataList2);
		testSteps2.setTestStepId(teststepid2);
		Actions actions2 = new Actions();
		actions2.setActionId(new BigDecimal(20001));
		actions2.setActionName("launchApplication");
		testSteps2.setActions(actions2);
		testStepsList1.add(testSteps2);

		// Third test step

		TestStep testSteps3 = new TestStep();
		TestStepId teststepid3 = new TestStepId();
		teststepid3.setStepName("Inserting values inside the textbox");
		teststepid3.setCreatedBy("abc");
		teststepid3.setTestStepId(new BigDecimal(50002));
		TestParamData testparamdatas3 = new TestParamData();
		Param param3 = new Param();
		param3.setParamName("pn_annamareddy");
		ObjectsId objectsId3 = new ObjectsId();
		objectsId3.setIndentifier("accountname");// id in html
		Objects objects3 = new Objects();
		ParamGroupId paramGroupId3 = new ParamGroupId();
		ParamGroup paramGroup3 = new ParamGroup();
		paramGroup3.setParamGroupId(paramGroupId3);
		param3.setObjects(objects3);
		param3.setParamGroup(paramGroup3);
		paramGroup3.getParamGroupId().getParamsList().add(param3);
		paramGroupId3.setParamGroupName("PG_3");// some helpful name
		objects3.setObjectsId(objectsId3);
		testparamdatas3.setParam(param3);
		ArrayList<TestParamData> testParamDataList3 = new ArrayList<TestParamData>();
		testParamDataList3.add(testparamdatas3);
		teststepid3.setTestParamDatas(testParamDataList3);
		testSteps3.setTestStepId(teststepid3);
		Actions actions3 = new Actions();
		actions3.setActionId(new BigDecimal(20002));
		actions3.setActionName("textBox");
		testSteps3.setActions(actions3);
		testStepsList1.add(testSteps3);

		// Fourth test step

		TestStep testSteps4 = new TestStep();
		TestStepId teststepid4 = new TestStepId();
		teststepid4.setStepName("Inserting Value into Password field");
		teststepid4.setCreatedBy("abc");
		teststepid4.setTestStepId(new BigDecimal(50003));
		TestParamData testparamdatas4 = new TestParamData();
		Param param4 = new Param();
		param4.setParamName("Apr@2013");
		ObjectsId objectsId4 = new ObjectsId();
		objectsId4.setIndentifier("accountpassword");// id in
		Objects objects4 = new Objects();
		ParamGroupId paramGroupId4 = new ParamGroupId();
		ParamGroup paramGroup4 = new ParamGroup();
		paramGroup4.setParamGroupId(paramGroupId4);
		param4.setObjects(objects4);
		param4.setParamGroup(paramGroup4);
		paramGroup4.getParamGroupId().getParamsList().add(param4);
		paramGroupId4.setParamGroupName("PG_4");// some helpful name
		objects4.setObjectsId(objectsId4);
		testparamdatas4.setParam(param4);
		ArrayList<TestParamData> testParamDataList4 = new ArrayList<TestParamData>();
		testParamDataList4.add(testparamdatas4);
		teststepid4.setTestParamDatas(testParamDataList4);
		testSteps4.setTestStepId(teststepid4);
		Actions actions4 = new Actions();
		actions4.setActionId(new BigDecimal(20002));
		actions4.setActionName("textBox");
		testSteps4.setActions(actions4);
		testStepsList1.add(testSteps4);

		// Fifth test step

		TestStep testSteps5 = new TestStep();
		TestStepId teststepid5 = new TestStepId();
		teststepid5.setStepName("Click on Login Button");
		teststepid5.setCreatedBy("abc");
		teststepid5.setTestStepId(new BigDecimal(50004));
		TestParamData testparamdatas5 = new TestParamData();
		Param param5 = new Param();
		param5.setParamName("Apr@2013");
		ObjectsId objectsId5 = new ObjectsId();
		objectsId5.setIndentifier("signInHyperLink");// id in html
		Objects objects5 = new Objects();
		ParamGroupId paramGroupId5 = new ParamGroupId();
		ParamGroup paramGroup5 = new ParamGroup();
		paramGroup5.setParamGroupId(paramGroupId5);
		param5.setObjects(objects5);
		param5.setParamGroup(paramGroup5);
		paramGroup5.getParamGroupId().getParamsList().add(param5);
		paramGroupId5.setParamGroupName("PG_5");// some helpful name
		objects5.setObjectsId(objectsId5);
		testparamdatas5.setParam(param5);
		ArrayList<TestParamData> testParamDataList5 = new ArrayList<TestParamData>();
		testParamDataList5.add(testparamdatas5);
		teststepid5.setTestParamDatas(testParamDataList5);
		testSteps5.setTestStepId(teststepid5);
		Actions actions5 = new Actions();
		actions5.setActionId(new BigDecimal(20002));
		actions5.setActionName("click");
		testSteps5.setActions(actions5);
		testStepsList1.add(testSteps5);
		testCase1.setTestSteps(testStepsList1);
		getCases().add(testCase1);

		TestCase testCase2 = new TestCase();

		// 2nd test case for Cancel order.

		ArrayList<TestStep> testStepsList2 = new ArrayList<TestStep>();
		testCase2.setCaseName("Cancel order");
		testCase2.setCreatedBy("abc");
		testCase2.setTestCaseId(new BigDecimal(1235));
		testCase2.setActive(new Integer(1));

		// First test Step

		TestStep testSteps1TC2 = new TestStep();
		TestStepId teststepid1TC2 = new TestStepId();
		teststepid1TC2.setStepName("Clicking on cancel menu item");
		teststepid1TC2.setCreatedBy("abc");
		teststepid1TC2.setTestStepId(new BigDecimal(50005));
		TestParamData testparamdatas1TC2 = new TestParamData();
		Param param1TC2 = new Param();
		param1TC2.setParamName("");
		ObjectsId objectsId1TC2 = new ObjectsId();
		objectsId1TC2.setIndentifier("menuCancelAEOrderGUI");// id in html
		Objects objects1TC2 = new Objects();
		ParamGroupId paramGroupId1TC2 = new ParamGroupId();
		ParamGroup paramGroup1TC2 = new ParamGroup();
		paramGroup1TC2.setParamGroupId(paramGroupId1TC2);
		param1TC2.setObjects(objects1TC2);
		param1TC2.setParamGroup(paramGroup1TC2);
		paramGroup1TC2.getParamGroupId().getParamsList().add(param1TC2);
		paramGroupId1TC2.setParamGroupName("PG_2TC_1");// some helpful name
		objects1TC2.setObjectsId(objectsId1TC2);
		testparamdatas1TC2.setParam(param1TC2);
		ArrayList<TestParamData> testParamDataList1TC2 = new ArrayList<TestParamData>();
		testParamDataList1TC2.add(testparamdatas1TC2);
		teststepid1TC2.setTestParamDatas(testParamDataList1TC2);
		testSteps1TC2.setTestStepId(teststepid1TC2);
		Actions actions1TC2 = new Actions();
		actions1TC2.setActionId(new BigDecimal(20002));
		actions1TC2.setActionName("click");
		testSteps1TC2.setActions(actions1TC2);
		testStepsList2.add(testSteps1TC2);

		// Second test Step

		TestStep testSteps2TC2 = new TestStep();
		TestStepId teststepid2TC2 = new TestStepId();
		teststepid2TC2.setStepName("Verifing the cancel details text");
		teststepid2TC2.setCreatedBy("abc");
		teststepid2TC2.setTestStepId(new BigDecimal(50006));
		TestParamData testparamdatas2TC2 = new TestParamData();
		Param param2TC2 = new Param();
		param2TC2.setParamName("Cancellation Details");
		ObjectsId objectsId2TC2 = new ObjectsId();
		objectsId2TC2.setIndentifier("cancelDetails");// id in html
		Objects objects2TC2 = new Objects();
		ParamGroupId paramGroupId2TC2 = new ParamGroupId();
		ParamGroup paramGroup2TC2 = new ParamGroup();
		paramGroup2TC2.setParamGroupId(paramGroupId2TC2);
		param2TC2.setObjects(objects2TC2);
		param2TC2.setParamGroup(paramGroup2TC2);
		paramGroup2TC2.getParamGroupId().getParamsList().add(param2TC2);
		paramGroupId2TC2.setParamGroupName("PG_2TC_2");// some helpful name
		objects2TC2.setObjectsId(objectsId2TC2);
		testparamdatas2TC2.setParam(param2TC2);
		ArrayList<TestParamData> testParamDataList2TC2 = new ArrayList<TestParamData>();
		testParamDataList2TC2.add(testparamdatas2TC2);
		teststepid2TC2.setTestParamDatas(testParamDataList2TC2);
		testSteps2TC2.setTestStepId(teststepid2TC2);
		Actions actions2TC2 = new Actions();
		actions2TC2.setActionId(new BigDecimal(20002));
		actions2TC2.setActionName("verificationByIds");
		testSteps2TC2.setActions(actions2TC2);
		testStepsList2.add(testSteps2TC2);

		// Third test step

		TestStep testSteps3TC2 = new TestStep();
		TestStepId teststepid3TC2 = new TestStepId();
		teststepid3TC2.setStepName("Verifing the cancel details text");
		teststepid3TC2.setCreatedBy("abc");
		teststepid3TC2.setTestStepId(new BigDecimal(50007));
		TestParamData testparamdatas3TC2 = new TestParamData();
		Param param3TC2 = new Param();
		param3TC2
				.setParamName("Refunds may only be provided to the customer that purchased AppleCare. Please confirm the customer details prior to processing the refund.");
		ObjectsId objectsId3TC2 = new ObjectsId();
		objectsId3TC2.setIndentifier("cancelDescription");// id in html
		Objects objects3TC2 = new Objects();
		ParamGroupId paramGroupId3TC2 = new ParamGroupId();
		ParamGroup paramGroup3TC2 = new ParamGroup();
		paramGroup3TC2.setParamGroupId(paramGroupId3TC2);
		param3TC2.setObjects(objects3TC2);
		param3TC2.setParamGroup(paramGroup3TC2);
		paramGroup3TC2.getParamGroupId().getParamsList().add(param3TC2);
		paramGroupId3TC2.setParamGroupName("PG_2TC_3");// some helpful name
		objects3TC2.setObjectsId(objectsId3TC2);
		testparamdatas3TC2.setParam(param3TC2);
		ArrayList<TestParamData> testParamDataList3TC2 = new ArrayList<TestParamData>();
		testParamDataList3TC2.add(testparamdatas3TC2);
		teststepid3TC2.setTestParamDatas(testParamDataList3TC2);
		testSteps3TC2.setTestStepId(teststepid3TC2);
		Actions actions3TC2 = new Actions();
		actions3TC2.setActionId(new BigDecimal(20002));
		actions3TC2.setActionName("verificationByIds");
		testSteps3TC2.setActions(actions3TC2);
		testStepsList2.add(testSteps3TC2);

		// Fourth test step

		TestStep testSteps4TC2 = new TestStep();
		TestStepId teststepid4TC2 = new TestStepId();
		teststepid4TC2.setStepName("Inserting device Id inside Cancel order textbox");
		teststepid4TC2.setCreatedBy("abc");
		teststepid4TC2.setTestStepId(new BigDecimal(50008));
		TestParamData testparamdatas4TC2 = new TestParamData();
		Param param4TC2 = new Param();
		param4TC2.setParamName("243566Y");
		ObjectsId objectsId4TC2 = new ObjectsId();
		objectsId4TC2.setIndentifier("deviceId");// id in html
		Objects objects4TC2 = new Objects();
		ParamGroupId paramGroupId4TC2 = new ParamGroupId();
		ParamGroup paramGroup4TC2 = new ParamGroup();
		paramGroup4TC2.setParamGroupId(paramGroupId4TC2);
		param4TC2.setObjects(objects4TC2);
		param4TC2.setParamGroup(paramGroup4TC2);
		paramGroup4TC2.getParamGroupId().getParamsList().add(param4TC2);
		paramGroupId4TC2.setParamGroupName("PG_2TC_4");// some helpful name
		objects4TC2.setObjectsId(objectsId4TC2);
		testparamdatas4TC2.setParam(param4TC2);
		ArrayList<TestParamData> testParamDataList4TC2 = new ArrayList<TestParamData>();
		testParamDataList4TC2.add(testparamdatas4TC2);
		teststepid4TC2.setTestParamDatas(testParamDataList4TC2);
		testSteps4TC2.setTestStepId(teststepid4TC2);
		Actions actions4TC2 = new Actions();
		actions4TC2.setActionId(new BigDecimal(20002));
		actions4TC2.setActionName("textBox");
		testSteps4TC2.setActions(actions4TC2);
		testStepsList2.add(testSteps4TC2);

		// Fifth test Step

		TestStep testSteps5TC2 = new TestStep();
		TestStepId teststepid5TC2 = new TestStepId();
		teststepid5TC2.setStepName("tabbing out the cancel order text box");
		teststepid5TC2.setCreatedBy("abc");
		teststepid5TC2.setTestStepId(new BigDecimal(50009));
		TestParamData testparamdatas5TC2 = new TestParamData();
		Param param5TC2 = new Param();
		param5TC2.setParamName("");
		ObjectsId objectsId5TC2 = new ObjectsId();
		objectsId5TC2.setIndentifier("NoObject");// id in html
		Objects objects5TC2 = new Objects();
		ParamGroupId paramGroupId5TC2 = new ParamGroupId();
		ParamGroup paramGroup5TC2 = new ParamGroup();
		paramGroup5TC2.setParamGroupId(paramGroupId5TC2);
		param5TC2.setObjects(objects5TC2);
		param5TC2.setParamGroup(paramGroup5TC2);
		paramGroup5TC2.getParamGroupId().getParamsList().add(param5TC2);
		paramGroupId5TC2.setParamGroupName("PG_2TC_5");// some helpful name
		objects5TC2.setObjectsId(objectsId5TC2);
		testparamdatas5TC2.setParam(param5TC2);
		ArrayList<TestParamData> testParamDataList5TC2 = new ArrayList<TestParamData>();
		testParamDataList5TC2.add(testparamdatas5TC2);
		teststepid5TC2.setTestParamDatas(testParamDataList5TC2);
		testSteps5TC2.setTestStepId(teststepid5TC2);
		Actions actions5TC2 = new Actions();
		actions5TC2.setActionId(new BigDecimal(20002));
		actions5TC2.setActionName("tabOutAction");
		testSteps5TC2.setActions(actions5TC2);
		testStepsList2.add(testSteps5TC2);

		// Sixth test step

		TestStep testSteps6TC2 = new TestStep();
		TestStepId teststepid6TC2 = new TestStepId();
		teststepid6TC2.setStepName("tabbing out the cancel order text box");
		teststepid6TC2.setCreatedBy("abc");
		teststepid6TC2.setTestStepId(new BigDecimal(50010));
		TestParamData testparamdatas6TC2 = new TestParamData();
		Param param6TC2 = new Param();
		param6TC2.setParamName("");
		ObjectsId objectsId6TC2 = new ObjectsId();
		objectsId6TC2.setIndentifier("NoObject");// id in html
		Objects objects6TC2 = new Objects();
		ParamGroupId paramGroupId6TC2 = new ParamGroupId();
		ParamGroup paramGroup6TC2 = new ParamGroup();
		paramGroup6TC2.setParamGroupId(paramGroupId6TC2);
		param6TC2.setObjects(objects6TC2);
		param6TC2.setParamGroup(paramGroup6TC2);
		paramGroup6TC2.getParamGroupId().getParamsList().add(param6TC2);
		paramGroupId6TC2.setParamGroupName("PG_2TC_6");// some helpful name
		objects6TC2.setObjectsId(objectsId6TC2);
		testparamdatas6TC2.setParam(param6TC2);
		ArrayList<TestParamData> testParamDataList6TC2 = new ArrayList<TestParamData>();
		testParamDataList6TC2.add(testparamdatas6TC2);
		teststepid6TC2.setTestParamDatas(testParamDataList6TC2);
		testSteps6TC2.setTestStepId(teststepid6TC2);
		Actions actions6TC2 = new Actions();
		actions6TC2.setActionId(new BigDecimal(20002));
		actions6TC2.setActionName("tabOutAction");
		testSteps6TC2.setActions(actions6TC2);
		testStepsList2.add(testSteps6TC2);

		testCase2.setTestSteps(testStepsList2);
		getCases().add(testCase2);

	}

	// setCases(cases);//NO-OP, not required.

	public ArrayList<TestCase> getCases() {
		return cases;
	}

	public void setCases(ArrayList<TestCase> cases) {
		this.cases = cases;
	}

	public PlanMock() {
	}

	public TestPlan createPlan(String planRunName) {
		Runner runner1 = new Runner();
		runner1.setRunnerId(new BigDecimal(10000));
		runner1.setRunnerName("SA");
		// makeMockTestCase1();
		testPlan = new TestPlan();
		TestPlanId planId = new TestPlanId();
		planId.setTestPlanId(new BigDecimal(30000));
		planId.setPlanName("Plan GRX");
		testPlan.setTestPlanRunName(planRunName);

		ArrayList<TestPlan> testplanList = new ArrayList<TestPlan>();
		ArrayList<TestplanTestscenarioMap> testplanTestscenarioMapsList = new ArrayList<TestplanTestscenarioMap>();
		TestplanTestscenarioMap testplanTestscenarioMap = new TestplanTestscenarioMap();
		TestScenario testscenario = new TestScenario();
		testscenario.setTestScenarioName("Cancelling in GRX");
		ArrayList<SuiteScenarioMapping> suiteScenarioMappingsList = new ArrayList<SuiteScenarioMapping>();
		SuiteScenarioMapping suiteScenarioMapping = new SuiteScenarioMapping();
		ArrayList<TestSuite> testSuitesList = new ArrayList<TestSuite>();
		TestSuite testSuite = new TestSuite();
		TestSuiteResultSummary testSuiteResultSummary = new TestSuiteResultSummary();
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
		System.out.println("testsuiteid.setTestcases(getCases());" + getCases());
		return testPlan;

	}

	public TestPlan getTestPlan() {
		return testPlan;
	}

	public void setTestPlan(TestPlan testPlan) {
		this.testPlan = testPlan;
	}
}
