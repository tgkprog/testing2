package org.s2n.ddt.test;

public class CodeSnippets {

	/** 29-08-2013 */
	// public void getReadPlanObjects(String path) throws DataAccessException,
	// InvalidFormatException, IOException {
	// ReadPlan readPlan = new ReadPlan();
	// Map<String, TestScenario> testScenarioMap = new HashMap<String,
	// TestScenario>();
	// Map<Integer, TestSuite> testSuiteMap = new HashMap<Integer, TestSuite>();
	// Map<Integer, TestCase> testCaseMap = new HashMap<Integer, TestCase>();
	// Map<Integer, TestStep> testStepMap = new HashMap<Integer, TestStep>();
	// Map<Integer, Actions> actionMap = new HashMap<Integer, Actions>();
	// Map<Integer, Runner> runnerMap = new HashMap<Integer, Runner>();
	// Map<Integer, ParamGroup> paramGroupMap = new HashMap<Integer,
	// ParamGroup>();
	//
	// Map<Integer, SuiteScenarioMapping> SuiteScenarioMap = new
	// HashMap<Integer, SuiteScenarioMapping>();
	//
	// TestPlan testPlan = readPlan.readPlanObj(path);
	// List<TestScenario> testScenarios =
	// testPlan.getTestPlanId().getTestScenariosList();
	//
	// Iterator<TestScenario> testScenarioItr = testScenarios.iterator();
	//
	// while (testScenarioItr.hasNext()) {
	// TestScenario testScenario = testScenarioItr.next();
	// String testScenarioName = testScenario.getTestScenarioName();
	// int testScenarioId =
	// this.testScenarioDao.getTestScenarioByName(testScenarioName);
	//
	// if (testScenarioId == 0) {
	// testScenario.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
	// testScenario.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
	// testScenario.setDescription(testScenarioName);
	// testScenario.setCreatedDateTime(new Date());
	// testScenario.setUpdatedDateTime(new Date());
	// testScenarioMap.put(testScenarioName, testScenario);
	// }
	//
	// List<SuiteScenarioMapping> suiteScenarioMap =
	// testScenario.getSuiteScenarioMappings();
	// Iterator<SuiteScenarioMapping> suiteScenarioMappingItr =
	// suiteScenarioMap.iterator();
	// while (suiteScenarioMappingItr.hasNext()) {
	// SuiteScenarioMapping suiteScenarioMapping =
	// suiteScenarioMappingItr.next();
	// int suiteScenarioId =
	// this.suiteScenarioMappingDao.getSuiteScenarioId(suiteScenarioMapping);
	// if (suiteScenarioId == 0)
	// this.suiteScenarioMappingDao.insertSuiteScenarioMappingDetails(suiteScenarioMapping);
	// SuiteScenarioMap.put(suiteScenarioId, suiteScenarioMapping);
	//
	// List<TestSuite> testSuite =
	// suiteScenarioMapping.getTestScenario().getTestSuites();
	// Iterator<TestSuite> testSuiteItr = testSuite.iterator();
	// while (testSuiteItr.hasNext()) {
	// TestSuite testSuiteRecords = testSuiteItr.next();
	// String suiteName = testSuiteRecords.getTestSuiteId().getSuiteName();
	// int testSuiteId =
	// this.testSuiteDao.getTestSuiteDetailsOnlyByName(suiteName);
	// if (testSuiteId == 0)
	// this.testSuiteDao.insertTestSuiteDetails(testSuiteRecords);
	// testSuiteMap.put(testSuiteId, testSuiteRecords);
	//
	// List<TestCase> testCase =
	// testSuiteRecords.getTestSuiteId().getTestCases();
	// Iterator<TestCase> testCaseItr = testCase.iterator();
	// while (testCaseItr.hasNext()) {
	// TestCase testCaseRecords = testCaseItr.next();
	// String testCaseName = testCaseRecords.getCaseName();
	// int testCaseId =
	// this.testCaseDao.getTestCaseDetailsOnlyByName(testCaseName);
	// if (testCaseId == 0)
	// this.testCaseDao.insertTestCaseDetails(testCaseRecords);
	// testCaseMap.put(testCaseId, testCaseRecords);
	// System.out.println("testCaseMap:---------" + testCaseMap);
	//
	// List<TestStep> testStep = testCaseRecords.getTestSteps();
	// Iterator<TestStep> testStepItr = testStep.iterator();
	// while (testStepItr.hasNext()) {
	// TestStep TestStepRecords = testStepItr.next();
	// String testStepName = TestStepRecords.getTestStepId().getStepName();
	// int testStepId = this.testStepDao.getTestStepOnlByName(testStepName);
	// if (testStepId == 0)
	// this.testStepDao.insertTestStepDetails(TestStepRecords);
	// testStepMap.put(testStepId, TestStepRecords);
	// System.out.println("testStepMap:---------" + testStepMap);
	//
	// Actions actions = TestStepRecords.getActions();
	// String actionName = actions.getActionName();
	// int actionId = this.actionsDao.getActionIdByActionName(actionName);
	// if (actionId == 0)
	// this.actionsDao.insertActionsDetails(actions);
	// actionMap.put(actionId, actions);
	// System.out.println("actionMap:---------" + actionMap);
	//
	// Runner runner = TestStepRecords.getRunner();
	// String runnerName = runner.getRunnerName();
	// int runnerId = this.runnerDao.getRunnerIdOnlyByName(runnerName);
	// if (runnerId == 0)
	// this.runnerDao.insertRunnerDetails(runner);
	// runnerMap.put(runnerId, runner);
	// System.out.println("runnerMap:---------" + runnerMap);
	//
	// List<ParamGroup> paramGroup =
	// TestStepRecords.getTestStepId().getParamGroups();
	// Iterator<ParamGroup> paramGroupItr = paramGroup.iterator();
	// while (paramGroupItr.hasNext()) {
	// ParamGroup paramGroupRecords = paramGroupItr.next();
	// String paramGroupName =
	// paramGroupRecords.getParamGroupId().getParamGroupName();
	// int paramGroupId =
	// this.paramGroupDao.getParamGroupIdDetailsOnlyByName(paramGroupName);
	// if (paramGroupId == 0)
	// this.paramGroupDao.insertParamGroupDetails(paramGroupRecords);
	// paramGroupMap.put(paramGroupId, paramGroupRecords);
	// System.out.println("paramGroupMap:---------" + paramGroupMap);
	//
	// }
	//
	// }
	//
	// }
	//
	// }
	// }
	// }
	// }

	/** 30-08-2013 */
	// else {
	// logger.info("TestParamData data set is updating details based on data set");
	// testParamData.setTestParamDataId(paramDataId);
	// this.testParamDataDao.updateParamData(testParamData);
	// logger.info("testParamData details were updated with id : " +
	// testParamDataId);
	// }
	// else {
	// logger.info("Currently test step is updating based on step order by value <= 0");
	// testStep.setTestStepId(stepId);
	// this.testStepDao.updateTestStep(testStep);
	// logger.info("testStep details were updated with id : " + testStepId);
	// }

	// public void insertObjectsFromExcel() throws DataAccessException {
	// Map<Integer, ObjectGroup> objectsGroupMap = new HashMap<Integer,
	// ObjectGroup>();
	// Map<Integer, Screen> screenMap = new HashMap<Integer, Screen>();
	// Map<Integer, Application> applicationMap = new HashMap<Integer,
	// Application>();
	//
	// List<Objects> objects = ReadObjectsData.getObjectsFromExcel();
	// int objectgroupid = 0;
	// Iterator<Objects> iterator = objects.iterator();
	// while (iterator.hasNext()) {
	// Objects objects2 = (Objects) iterator.next();
	// ObjectGroup objectGroup = objects2.getObjectGroup();
	// String objName = objectGroup.getObjectGroupName();
	// if (objName != null) {
	// int objGroupId =
	// this.objectGroupDao.getObjectGroupWithObjectsByName(objName);
	// objectsGroupMap.put(objGroupId, objectGroup);
	// }
	// }
	//
	// Iterator<Map.Entry<Integer, ObjectGroup>> iterator1 =
	// objectsGroupMap.entrySet().iterator();
	// int screenId = 0;
	// while (iterator1.hasNext()) {
	// Entry<Integer, ObjectGroup> screen = iterator1.next();
	// ObjectGroup screenName = (ObjectGroup) screen.getValue();
	// if (screenName != null) {
	// screenId =
	// this.screenDao.getScreenIdByScreenName(screenName.getScreen().getScreenName());
	// screenMap.put(screenId, ((ObjectGroup) screen.getValue()).getScreen());
	// }
	// }
	//
	// Iterator<Map.Entry<Integer, Screen>> iterator2 =
	// screenMap.entrySet().iterator();
	// String appName1 = null;
	// int appId = 0;
	// while (iterator2.hasNext()) {
	// Entry<Integer, Screen> application = iterator2.next();
	// String appName = application.getValue().toString();
	// int appIndex = appName.lastIndexOf(":");
	// int endIndex = appName.indexOf(",", appIndex);
	// appName1 = appName.substring(appIndex + 1, endIndex);
	// if (!appName1.equals(null)) {
	// appId = this.applicationDao.getAppIdByAppName(appName1);
	// applicationMap.put(appId, ((Screen)
	// application.getValue()).getApplication());
	// if (appId == 0) {
	// applicationMap.get(appId).setAppName(appName1);
	// applicationMap.get(appId).setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
	// applicationMap.get(appId).setCreatedDateTime(new Date());
	// applicationMap.get(appId).setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
	// applicationMap.get(appId).setUpdatedDateTime(new Date());
	// this.applicationDao.insertApplicationDetails(applicationMap.get(appId));
	// }
	// }
	// }
	//
	// Iterator<Map.Entry<Integer, Screen>> iterator3 =
	// screenMap.entrySet().iterator();
	// while (iterator3.hasNext()) {
	// if (screenId == 0 && appId != 0) {
	// screenMap.get(screenId).setAppId(new BigDecimal(appId));
	// screenMap.get(screenId).setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
	// screenMap.get(screenId).setCreatedDateTime(new Date());
	// screenMap.get(screenId).setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
	// screenMap.get(screenId).setUpdatedDateTime(new Date());
	// this.screenDao.insertScreenDetails(screenMap.get(screenId));
	// }
	// }
	//
	// Iterator<Map.Entry<Integer, ObjectGroup>> iterator4 =
	// objectsGroupMap.entrySet().iterator();
	// while (iterator4.hasNext()) {
	// if (objectgroupid == 0 && appId != 0 && screenId != 0) {
	// objectsGroupMap.get(objectgroupid).setAppId(new BigDecimal(appId));
	// objectsGroupMap.get(objectgroupid).setScreenId(new BigDecimal(screenId));
	// objectsGroupMap.get(objectgroupid).setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
	// objectsGroupMap.get(objectgroupid).setCreatedDateTime(new Date());
	// objectsGroupMap.get(objectgroupid).setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
	// objectsGroupMap.get(objectgroupid).setUpdatedDateTime(new Date());
	// this.objectGroupDao.insertObjectGroupDetails(objectsGroupMap.get(objectgroupid));
	// }
	// }
	// }
	
}
