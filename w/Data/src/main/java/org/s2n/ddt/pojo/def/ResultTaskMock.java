package org.s2n.ddt.pojo.def;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.pojo.Actions;
import org.s2n.ddt.pojo.Feature;
import org.s2n.ddt.pojo.Functional;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.TestSuiteId;
import org.s2n.ddt.pojo.input.Objects;
import org.s2n.ddt.pojo.input.ObjectsId;
import org.s2n.ddt.pojo.input.Param;
import org.s2n.ddt.pojo.input.ParamGroup;
import org.s2n.ddt.pojo.input.ParamGroupId;
import org.s2n.ddt.pojo.input.Task;
import org.s2n.ddt.pojo.input.TestParamData;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.input.TestStepId;
import org.s2n.ddt.pojo.output.AgentRunResult;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.pojo.output.TaskResult;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestPlanId;
import org.s2n.ddt.pojo.output.TestPlanResult;
import org.s2n.ddt.pojo.output.TestScenarioResult;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResult;

public class ResultTaskMock {

	@SuppressWarnings({ "deprecation", "unused" })
	public TaskResult mockMap(String planRunName,BigDecimal id) {

		Date d1 = new Date(2013,8,05,13,10,30);
		Date d2 = new Date(2013,8,05,13,15,40);
		Date d3 = new Date(2013,8,05,13,12,30);
		Date d4 = new Date(2013,8,05,13,16,30);
		
		
		Date caseD1 = new Date(2013,8,05,13,10,40);
		Date caseD2 = new Date(2013,8,05,13,11,10);
		Date caseD3 = new Date(2013,8,05,13,12,30);
		Date caseD4 = new Date(2013,8,05,13,13,30);
		
		Date caseD5 = new Date(2013,8,05,13,12,40);
		Date caseD6 = new Date(2013,8,05,13,13,40);
		Date caseD7 = new Date(2013,8,05,13,14,30);
		Date caseD8 = new Date(2013,8,05,13,15,30);
		
		ArrayList<TestSuite> testSuitesList = new ArrayList<TestSuite>();
		
		TestPlan testPlan = new TestPlan();
		TestPlanId testPlanId = new TestPlanId();
		RunResult runResult = new RunResult();
		Functional functionality = new Functional();
		functionality.setFunctionalId(new BigDecimal(0001));
		functionality.setFunctionalName("Create");
		functionality.setDescription("GRX");
		functionality.setTotalCount(2);
		functionality.setPassCount(1);
		functionality.setFailCount(1);
		functionality.setExecutedCount(2);
		functionality.setNotExecutedCount(0);
		
		testPlanId.setTestPlanId(new BigDecimal(12345));
		testPlan.setTestPlanId(testPlanId);
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
		testPlan.setTestPlanRunName("Plan_GRX");
		String relativePathToRes = "/reports/res/";
		File root = new File(UtlConf.getProperty("reports.home") + "/" + testPlan.getTestPlanRunName() + "/" + sdf.format(dt) );
		root.mkdirs();
		File f1 = new File(root, "Agent1Report.html");
		File f2 = new File(root, "Agent2Report.html");
		
		TaskResult taskResult1 = new TaskResult();
		TestPlanResult testPlanResult = new TestPlanResult();
		TestScenarioResult testScenarioResult = new TestScenarioResult();
		
		TestSuiteResult testSuitResult1 = new TestSuiteResult();
		TestCaseResult testCaseResult1 = new TestCaseResult();
		TestCaseResult testCaseResult2 = new TestCaseResult();
		TestCaseResult testCaseResult3 = new TestCaseResult();
		TestCaseResult testCaseResult4 = new TestCaseResult();

		TestStepResult testStepResult1 = new TestStepResult();
		TestStep testSteps1 = new TestStep();
		TestStepId teststepid1 = new TestStepId();
		teststepid1.setStepName("Launching the browser");
		teststepid1.setTestStepId(new BigDecimal(50000));
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
		teststepid1.setTestParamDatas(testParamDataList);

		
		Actions actions1 = new Actions();
		actions1.setActionId(new BigDecimal(20000));
		actions1.setActionName("launchBrowser");
		testSteps1.setActions(actions1);
		testSteps1.setTestStepId(teststepid1);
		testStepResult1.setTestStep(testSteps1);
		testStepResult1.setComment("Launched Browser successfully");
		testStepResult1.setDetailMsgs("Firefox browser launched successfully");
		testStepResult1.setResult(true);
		

		TestStepResult testStepResult2 = new TestStepResult();
		TestStep testSteps2 = new TestStep();
		TestStepId teststepid2 = new TestStepId();
		teststepid2.setStepName("Launching the application");
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
		testSteps2.setTestStepId(teststepid2);
		
		testStepResult2.setTestStep(testSteps2);
		testStepResult2.setComment("Launching the application");
		testStepResult2.setDetailMsgs("Application launched");
		testStepResult2.setResult(true);
		
		
		TestStepResult testStepResult3 = new TestStepResult();
		TestStep testSteps3 = new TestStep();
		TestStepId teststepid3 = new TestStepId();
		teststepid3.setStepName("Inserting values inside the textbox");
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
		testSteps3.setTestStepId(teststepid3);
		testStepResult3.setResult(true);
		testStepResult3.setTestStep(testSteps3);
		testStepResult3.setComment("Passing value to text box");
		testStepResult3.setDetailMsgs(param3+ " is passed to textbox");
		
		
		TestStepResult testStepResult4 = new TestStepResult();
		TestStep testSteps4 = new TestStep();
		TestStepId teststepid4 = new TestStepId();
		teststepid4.setStepName("Inserting Value into Password field");
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
		testSteps4.setTestStepId(teststepid4);
		testStepResult4.setResult(true);
		testStepResult4.setTestStep(testSteps4);
		testStepResult4.setComment("Passing value to text box");
		testStepResult4.setDetailMsgs(param4 + " is passed to textbox");

		TestStepResult testStepResult5 = new TestStepResult();
		TestStep testSteps5 = new TestStep();
		TestStepId teststepid5 = new TestStepId();
		teststepid5.setStepName("Click on Login Button");
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
		testSteps5.setTestStepId(teststepid5);
		testStepResult5.setResult(true);
		testStepResult5.setTestStep(testSteps5);
		testStepResult5.setComment("Clciking button");
		testStepResult5.setDetailMsgs("Clicking on sign in buton");

		
		List<TestStepResult> testStepResults = new ArrayList<TestStepResult>();
		testStepResults.add(testStepResult1);
		testStepResults.add(testStepResult2);
		testStepResults.add(testStepResult3);
		testStepResults.add(testStepResult4);
		testStepResults.add(testStepResult5);
		testCaseResult1.setTestStepResultsList(testStepResults);
		
		testCaseResult1.setPlanRunStartDateTime(caseD1);
		testCaseResult1.setPlanRunEndDateTime(caseD2);
		testCaseResult1.setTestCaseId(new BigDecimal(1111));
		testCaseResult1.setPassCount(4);
		testCaseResult1.setFailCount(0);
		testCaseResult1.setTotalCount(4);
		testCaseResult1.setSkipCount(0);
		testCaseResult1.setTimeDuration(00.01);
		testCaseResult1.setTestCaseName("Logging into Grx application");
		testCaseResult1.setDescription("Logging Successfull");
		testCaseResult1.setTestCaseResult("Pass");
		
		TestStepResult testStepResult6 = new TestStepResult();
		TestStep testSteps6 = new TestStep();
		TestStepId teststepid6 = new TestStepId();
		teststepid6.setStepName("Clicking on cancel menu item");
		teststepid6.setTestStepId(new BigDecimal(50005));
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
		teststepid6.setTestParamDatas(testParamDataList1TC2);
		testSteps6.setTestStepId(teststepid6);
		Actions actions1TC2 = new Actions();
		actions1TC2.setActionId(new BigDecimal(20002));
		actions1TC2.setActionName("click");
		testSteps6.setActions(actions1TC2);
		testSteps6.setTestStepId(teststepid6);
		testStepResult6.setResult(true);
		testStepResult6.setTestStep(testSteps6);
		testStepResult6.setComment("Clciking button");
		testStepResult6.setDetailMsgs("Clicking on Cancel button");

		TestStepResult testStepResult7 = new TestStepResult();
		TestStep testSteps7 = new TestStep();
		TestStepId teststepid7 = new TestStepId();
		teststepid7.setStepName("Verifing the cancel details text");
		teststepid7.setTestStepId(new BigDecimal(50006));
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
		teststepid7.setTestParamDatas(testParamDataList2TC2);
		testSteps7.setTestStepId(teststepid7);
		Actions actions2TC2 = new Actions();
		actions2TC2.setActionId(new BigDecimal(20002));
		actions2TC2.setActionName("verificationByIds");
		testSteps7.setActions(actions2TC2);
		testSteps7.setTestStepId(teststepid7);
		testStepResult7.setResult(true);
		testStepResult7.setComment("Verified cancel details");
		testStepResult7.setDetailMsgs("Verifing cancel order page");
		testStepResult7.setTestStep(testSteps7);

		TestStepResult testStepResult8 = new TestStepResult();
		TestStep testSteps8 = new TestStep();
		TestStepId teststepid8 = new TestStepId();
		teststepid8.setStepName("Verifing the cancel details text");
		teststepid8.setTestStepId(new BigDecimal(50007));
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
		teststepid8.setTestParamDatas(testParamDataList3TC2);
		testSteps8.setTestStepId(teststepid8);
		Actions actions3TC2 = new Actions();
		actions3TC2.setActionId(new BigDecimal(20002));
		actions3TC2.setActionName("verificationByIds");
		testSteps8.setActions(actions3TC2);
		testSteps8.setTestStepId(teststepid8);
		testStepResult8.setResult(true);
		testStepResult8.setTestStep(testSteps8);
		testStepResult8.setComment("Verified cancel details tetx");
		testStepResult8.setDetailMsgs("Verifing cancel order page texts");

		
		
		TestStepResult testStepResult9 = new TestStepResult();
		TestStep testSteps9 = new TestStep();
		TestStepId teststepid9 = new TestStepId();
		teststepid9.setStepName("Inserting device Id inside Cancel order textbox");
		teststepid9.setTestStepId(new BigDecimal(50008));
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
		teststepid9.setTestParamDatas(testParamDataList4TC2);
		testSteps9.setTestStepId(teststepid9);
		Actions actions4TC2 = new Actions();
		actions4TC2.setActionId(new BigDecimal(20002));
		actions4TC2.setActionName("textBox");
		testSteps9.setActions(actions4TC2);
		testSteps9.setTestStepId(teststepid9);
		testStepResult9.setResult(true);
		testStepResult9.setComment("Passing value to textbox");
		testStepResult9.setDetailMsgs(param4TC2 +" is passed to deviceid textbox");
		testStepResult9.setTestStep(testSteps9);

		TestStepResult testStepResult10 = new TestStepResult();
		TestStep testSteps10 = new TestStep();
		TestStepId teststepid10 = new TestStepId();
		teststepid10.setStepName("tabbing out the cancel order text box");
		teststepid10.setTestStepId(new BigDecimal(50009));
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
		teststepid10.setTestParamDatas(testParamDataList5TC2);
		testSteps10.setTestStepId(teststepid10);
		Actions actions5TC2 = new Actions();
		actions5TC2.setActionId(new BigDecimal(20002));
		actions5TC2.setActionName("tabOutAction");
		testSteps10.setActions(actions5TC2);
		testSteps10.setTestStepId(teststepid10);
		testStepResult10.setResult(false);
		testStepResult10.setComment("Tabbed out");
		testStepResult10.setDetailMsgs("Validating device id");
		testStepResult10.setTestStep(testSteps10);
		
		TestStepResult testStepResult11 = new TestStepResult();
		TestStep testSteps11 = new TestStep();
		TestStepId teststepid11 = new TestStepId();
		teststepid11.setStepName("tabbing out the cancel order text box");
		teststepid11.setTestStepId(new BigDecimal(50010));
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
		teststepid11.setTestParamDatas(testParamDataList6TC2);
		testSteps11.setTestStepId(teststepid11);
		Actions actions6TC2 = new Actions();
		actions6TC2.setActionId(new BigDecimal(20002));
		actions6TC2.setActionName("tabOutAction");
		testSteps11.setActions(actions6TC2);
		testSteps11.setTestStepId(teststepid11);
		testStepResult11.setResult(true);
		testStepResult11.setTestStep(testSteps11);
		testStepResult11.setComment("Tabbed out in Grx Window");
		testStepResult11.setDetailMsgs("Tabbed Out in Cancel order page");

		
		List<TestStepResult> testStepResults1 = new ArrayList<TestStepResult>();
		testStepResults1.add(testStepResult6);
		testStepResults1.add(testStepResult7);
		testStepResults1.add(testStepResult8);
		testStepResults1.add(testStepResult9);
		testStepResults1.add(testStepResult10);
		testStepResults1.add(testStepResult11);
		testCaseResult1.setTestStepResultsList(testStepResults1);	
		
		
		testCaseResult2.setTestStepResultsList(testStepResults1);
		testCaseResult2.setPlanRunStartDateTime(caseD3);
		testCaseResult2.setPlanRunEndDateTime(caseD4);
		testCaseResult2.setTestCaseId(new BigDecimal(2222));
		testCaseResult2.setPassCount(4);
		testCaseResult2.setFailCount(2);
		testCaseResult2.setTotalCount(6);
		testCaseResult2.setSkipCount(0);
		testCaseResult2.setTimeDuration(00.05);
		testCaseResult2.setTestCaseName("Cancel order");
		testCaseResult2.setDescription("Cancel order Successfull");
		testCaseResult2.setTestCaseResult("Pass");
		testCaseResult2.setErrorMsg("Text Box is disabled");
			
		
		List<TestCaseResult> testCaseResults = new ArrayList<TestCaseResult>();
		testCaseResults.add(testCaseResult1);
		testCaseResults.add(testCaseResult2); 
		
		TestSuite testSuite = new TestSuite();
		TestSuiteId testsuiteid = new TestSuiteId();
		testsuiteid.setTestSuiteId(new BigDecimal(1000));
		testsuiteid.setSuiteName("Cancel Order");
		testSuite.setTestSuiteId(testsuiteid);
		testSuitResult1.setCaseResultsList(testCaseResults);
		testSuitResult1.setSuiteId(testsuiteid.getTestSuiteId());
		testSuitResult1.setTestCaseName("Cancel Order");
		testSuitResult1.setPassCount(2);
		testSuitResult1.setFailCount(0);
		testSuitResult1.setSkipCount(0);
		testSuitResult1.setNotExecuted(0);
		testSuitResult1.setTotalCount(2);
		testSuitResult1.setTestSuite(testSuite);
		
		
		List<TestSuiteResult> testSuiteResults = new ArrayList<TestSuiteResult>();
		testSuiteResults.add(testSuitResult1);
		testScenarioResult.setScenarioId(new BigDecimal(101));
		testScenarioResult.setSuiteResultsList(testSuiteResults);
		
		List<TestScenarioResult> testScenarioResults = new ArrayList<TestScenarioResult>();
		testScenarioResults.add(testScenarioResult);
		
		List<Feature> featureList = new ArrayList<Feature>();
		
		
		Feature featurs = new Feature();
		featurs.setFeatureId(new BigDecimal(12345));
		featurs.setFeatureName("Feature1");
		featurs.setTestSuites(testSuitesList);
		featureList.add(featurs);
		
		Functional functional = new Functional();
		functional.setFunctionalId(new BigDecimal(123));
		functional.setFunctionalName("Functional 1");
		List<Functional> functionalList = new ArrayList<Functional>();
		functionalList.add(functional);
		
		testPlanResult.setFunctionalList(functionalList);
		testPlanResult.setTestplanid(new BigDecimal(5555555));
	
		testPlanResult.setTestScenarioResults(testScenarioResults);
		
		List<TestPlanResult> testPlanResults = new ArrayList<TestPlanResult>();
		testPlanResults.add(testPlanResult);
		testPlanResult.setTestScenarioResults(testScenarioResults);
	
	    taskResult1.setTestPlanResult(testPlanResult);
	    taskResult1.setAgentRunnerType("SEL");
	    taskResult1.setLaneName("LAne1 ");
	    taskResult1.setRunnerName("SEL");
	    taskResult1.setLaneNo(1);
	    taskResult1.setNextTask("NEXT TASK");
	    taskResult1.setNumberOfLaneCloning(2);
	    taskResult1.setTaskId(new BigDecimal(1233456));
	 
		return taskResult1;

	}

}


