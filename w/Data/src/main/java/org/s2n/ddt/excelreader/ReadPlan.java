package org.s2n.ddt.excelreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.pojo.Actions;
import org.s2n.ddt.pojo.Application;
import org.s2n.ddt.pojo.Feature;
import org.s2n.ddt.pojo.Functional;
import org.s2n.ddt.pojo.Runner;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.TestSuiteId;
import org.s2n.ddt.pojo.def.DdtRemoteInterface2;
import org.s2n.ddt.pojo.input.Objects;
import org.s2n.ddt.pojo.input.ObjectsId;
import org.s2n.ddt.pojo.input.Param;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestParamData;
import org.s2n.ddt.pojo.input.TestParamDataId;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.input.TestStepId;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestPlanId;
import org.s2n.ddt.pojo.output.TestScenario;
import org.s2n.ddt.util.ExcelAccess;
import org.s2n.ddt.utils.DataConstants;

public class ReadPlan {
	private static final Logger logger = Logger.getLogger(ReadPlan.class);

	private static Properties mainXlsProps;
	private TestPlan testPlan = new TestPlan();
	private ExcelAccess excelAccess = new ExcelAccess();
	private int tcStartRow;
	private GetObjs getObjs = new GetObjs(excelAccess);

	// public TestPlan readPlanObj(String filePath) throws
	// InvalidFormatException, IOException {
	// ddtRemote = new ();
	// return readPlanObj(filePath, null);
	// }

	@SuppressWarnings("unused")
	public TestPlan readPlanObj(String filePath, DdtRemoteInterface2 ddtRemote) throws InvalidFormatException, IOException {
		File f = new File(filePath);
		logger.info("File Path : " + f.toString());
		String n = f.getName();
		int i = n.lastIndexOf(".");
		n = n.substring(0, i);
		testPlan.setTestPlanRunName(n);
		String sheetName = UtlConf.getProperty("xls.sheetName1", "createOrder");// System.getProperty("DDT_MAIN_PROPERTIES_FILE_TESTCASES_SHEETNAME");
		excelAccess.openWorkBook(f);
		Map<Object, Object> categoryMap = extractXlsReader();
		setTcStartRow(getTCStrtRow(excelAccess, sheetName));
		testPlan = strtReadPlanObject(ddtRemote, excelAccess, testPlan, sheetName, categoryMap);
		return testPlan;
	}

	public TestPlan readPlanDataSet(DdtRemoteInterface2 ddtRemote) throws InvalidFormatException, IOException {
		logger.info(" Calling Data set ");
		ReadDataSet readDataSet = new ReadDataSet();
		// when u start a new sheet - testCasesInCurrentSuite.clear();
		String sheetName = UtlConf.getProperty("xls.sheetName1", "createOrder");// System.getProperty("DDT_MAIN_PROPERTIES_FILE_TESTCASES_SHEETNAME");
		if (ddtRemote != null) {
			readDataSet.readDataSetChecker(testPlan, ddtRemote, excelAccess, sheetName);
		}
		return testPlan;
	}

	/**
	 * @param ddtRemote
	 * @param excelAccess
	 * @param readDataSet
	 * @param testPlan
	 * @param data
	 *            .tcStartRow
	 * @param sheetName
	 * @param categoryMap
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	private TestPlan strtReadPlanObject(DdtRemoteInterface2 ddtRemote, ExcelAccess excelAccess, TestPlan testPlan, String sheetName, Map<Object, Object> categoryMap)
			throws InvalidFormatException, IOException {
		int strCol = getTcStartRow() - 1;
		String sheetNameDataSet;
		int testCaseNameCol = 1;
		int actionColNo = 8;
		int actualResColNo = 16;
		int stepParamColNo = 13;
		int dependencyColNo = 10;
		int executeColNo = 9;
		int expectedColNo = 15;
		int paramGrpColNo = 12;
		int decColNo = 5;
		int resultColNo = 17;
		int runnerCommentColNo = 18;
		int runnerTypeColNo = 6;
		int stepTypeColNo = 7;
		int testClassficColNo = 2;
		int testCaseScenarioColNo = 3;
		int testCaseTitleColNo = 4;
		int userCommentsColNo = 11;
		int valueColNo = 14;
		int slNo = 0;
		int dateFormatNo = DataConstants.DEFAULT_VAL;
		// cols hard coded in xls unless "xlsPath.colsResolve == 1 in main.pro
		String colsResolve = UtlConf.getProperty("xlsPath.colsResolve", "0");
		if ("1".equals(colsResolve)) {
			for (int colExe = 0; colExe <= excelAccess.getTotalColumnCount(sheetName); colExe++) {
				String colNames = excelAccess.getCellValue(sheetName, strCol, colExe);
				if (!colNames.equals("")) {
					Iterator<Entry<Object, Object>> iter = categoryMap.entrySet().iterator();
					String keys = iterXlColumns(colNames, iter);
					ColumnNames columnNames = getXlsColumns(colExe, keys);
					switch (columnNames) {
					case SL_NO:
						slNo = colExe;
						break;
					case ACTION:
						actionColNo = colExe;
						break;
					case ACTUAL_RESULT:
						actualResColNo = colExe;
						break;
					case STEP_PARAM:
						stepParamColNo = colExe;
						break;
					case DEPENDENCY:
						dependencyColNo = colExe;
						break;
					case EXECUTE:
						executeColNo = colExe;
						break;
					case EXPECTED_RESULT:
						expectedColNo = colExe;
						break;
					case PARAM_GROUP_OBJECT:
						paramGrpColNo = colExe;
						break;
					case POSTIVE_OR_NEGATIVE:
						decColNo = colExe;
						break;
					case RESULT:
						resultColNo = colExe;
						break;
					case RUNNER_COMMENTS:
						runnerCommentColNo = colExe;
						break;
					case RUNNER_TYPE:
						runnerTypeColNo = colExe;
						break;
					case STEP_TYPE:
						stepTypeColNo = colExe;
						break;
					case TEST_CASE_CLASSFICATION_TAGS:
						testClassficColNo = colExe;
						break;
					case TEST_CASE_NAME:
						testCaseNameCol = colExe;
						break;
					case TEST_CASE_SCENARIO:
						testCaseScenarioColNo = colExe;
						break;
					case TEST_CASE_TITLE:
						testCaseTitleColNo = colExe;
						break;
					case USER_COMMENTS:
						userCommentsColNo = colExe;
						break;
					case VALUE:
						valueColNo = colExe;
						break;
					default:
						break;

					}
				}
			}
		}
		// System.out.println(" testCaseNameCol "+testCaseNameCol);
		List<TestCase> testCasesList = createCaseList(ddtRemote, excelAccess, testPlan, sheetName, testCaseNameCol, actionColNo, stepParamColNo, executeColNo, paramGrpColNo,
				decColNo, runnerTypeColNo, stepTypeColNo, testClassficColNo, testCaseScenarioColNo, testCaseTitleColNo, valueColNo);
		// makeMockTestCase();
		List<TestSuite> testSuitesList = new ArrayList<TestSuite>();
		TestSuite testSuite = suiteCreation(excelAccess, sheetName, testCaseScenarioColNo, testCasesList);
		testSuitesList.add(testSuite);
		TestScenario testscenario = scenarioCreation(excelAccess, sheetName, testCaseScenarioColNo, testSuitesList);
		List<TestScenario> testScenarioList = new ArrayList<TestScenario>();
		testScenarioList.add(testscenario);
		List<Feature> featureList = new ArrayList<Feature>();
		List<Functional> functionalList = new ArrayList<Functional>();
		Feature feature = featureCreation(excelAccess, sheetName, testClassficColNo, testSuitesList, testScenarioList);
		featureList.add(feature);
		Functional functional = createFunctional(excelAccess, sheetName, testCaseNameCol, slNo, decColNo, featureList);
		functionalList.add(functional);
		TestPlanId planId = planCreation(testScenarioList, functionalList);
		testPlan.setTestPlanId(planId);
		// call data set sheet here MANOJ TODO
		/* testplanList.add(testPlan); */
		return testPlan;
	}

	/**
	 * @param colExe
	 * @param keys
	 * @return
	 */
	private ColumnNames getXlsColumns(int colExe, String keys) {
		ColumnNames columnNames = null;
		try {
			columnNames = ColumnNames.valueOf(keys);
		} catch (Exception e) {
			logger.info("Readplan col name not found :" + keys + ", colExe :" + colExe);
		}
		return columnNames;
	}

	/**
	 * @param colNames
	 * @param iter
	 * @return
	 */
	private String iterXlColumns(String colNames, Iterator<Entry<Object, Object>> iter) {
		String keys = null;
		while (iter.hasNext()) {
			Entry<Object, Object> entry = iter.next();
			if (entry.getValue().equals(colNames.trim())) {
				keys = (String) entry.getKey();
				break;
			}
		}
		return keys;
	}

	/**
	 * @param ddtRemote
	 * @param excelAccess
	 * @param testPlan
	 * @param data
	 *            .tcStartRow
	 * @param sheetName
	 * @param testCaseNameCol
	 * @param actionColNo
	 * @param stepParamColNo
	 * @param executeColNo
	 * @param paramGrpColNo
	 * @param decColNo
	 * @param runnerTypeColNo
	 * @param stepTypeColNo
	 * @param testClassficColNo
	 * @param testCaseScenarioColNo
	 * @param testCaseTitleColNo
	 * @param valueColNo
	 * @param testCasesList
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	private List<TestCase> createCaseList(DdtRemoteInterface2 ddtRemote, ExcelAccess excelAccess, TestPlan testPlan, String sheetName, int testCaseNameCol, int actionColNo,
			int stepParamColNo, int executeColNo, int paramGrpColNo, int decColNo, int runnerTypeColNo, int stepTypeColNo, int testClassficColNo, int testCaseScenarioColNo,
			int testCaseTitleColNo, int valueColNo) throws InvalidFormatException, IOException {
		List<TestCase> testCasesList = new ArrayList<TestCase>();
		int tcEndRow = 1;
		int rowCtr = 0;
		boolean foundStart = false;
		boolean foundEnd = false;
		boolean foundEndSuite = false;

		while (true) {
			for (rowCtr = getTcStartRow(); rowCtr <= excelAccess.getTotalRowCount(sheetName); rowCtr++) {
				String curValue = null;
				curValue = excelAccess.getCellValue(sheetName, rowCtr, stepTypeColNo);
				logger.info(" Cur value "+ curValue);
				/* Get the value from read */
				if (!foundStart) {
					if (curValue.equals("TC_START")) {
						setTcStartRow(rowCtr + 1);
						foundStart = true;
					}
				}
				if (!foundEnd) {

					if (curValue.equals("TC_END")) {

						tcEndRow = (rowCtr - 1);
						foundEnd = true;
					}
				}
				if (!foundEndSuite) {
					if (curValue.equals("TC_EXECUTION_ENDS_HERE")) {
						foundEndSuite = true;
					}
				}
			
			}
			if (!foundEndSuite) {
				logger.info("Could not find the string TC Execution Ends Here");
				break;
			}
			if (!foundStart) {
				logger.info("Could not find TC_START and not executing this TC ");
				break;
			}
			if (!foundEnd) {
				logger.info("Could not find TC_END and not executing this TC");
				break;
			}
			String testCaseTitle;
			String runner = null;
			String execute;
			int run;
			int pos;
			String testCaseName;
			String classificationTag;
			String tcType;
			testCaseTitle = excelAccess.getCellValue(sheetName, getTcStartRow() - 1, testCaseTitleColNo);
			testCaseName = excelAccess.getCellValue(sheetName, getTcStartRow() - 1, testCaseNameCol);
			execute = excelAccess.getCellValue(sheetName, getTcStartRow() - 1, executeColNo);
			classificationTag = excelAccess.getCellValue(sheetName, getTcStartRow() - 1, testClassficColNo);
			tcType = excelAccess.getCellValue(sheetName, getTcStartRow() - 1, decColNo);

			runner = excelAccess.getCellValue(sheetName, getTcStartRow() - 1, runnerTypeColNo);
			run = runCase(execute);
			pos = caseType(tcType);

			TestCase tcase = testCaseCreation(ddtRemote, excelAccess, testPlan, sheetName, actionColNo, stepParamColNo, paramGrpColNo, runnerTypeColNo, testCaseTitleColNo,
					valueColNo, tcEndRow, testCaseTitle, runner, run, pos, testCaseName, classificationTag);
			testCasesList.add(tcase);
			
			setTcStartRow(tcEndRow + 2);
			if(tcEndRow+2 >= excelAccess.getTotalRowCount(sheetName)){
				logger.info("Reading plan execution ends here");
				break;
			}
			foundStart = false;
			foundEnd = false;
//			foundEndSuite = false;
		}
		return testCasesList;
	}

	/**
	 * @param ddtRemote
	 * @param excelAccess
	 * @param testPlan
	 * @param data
	 *            .tcStartRow
	 * @param sheetName
	 * @param actionColNo
	 * @param stepParamColNo
	 * @param paramGrpColNo
	 * @param runnerTypeColNo
	 * @param testCaseTitleColNo
	 * @param valueColNo
	 * @param tcEndRow
	 * @param suite
	 * @param testCase1
	 * @param testCaseTitle
	 * @param runner
	 * @param run
	 * @param pos
	 * @param testCaseName
	 * @param classificationTag
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	private TestCase testCaseCreation(DdtRemoteInterface2 ddtRemote, ExcelAccess excelAccess, TestPlan testPlan, String sheetName, int actionColNo, int stepParamColNo,
			int paramGrpColNo, int runnerTypeColNo, int testCaseTitleColNo, int valueColNo, int tcEndRow, String testCaseTitle, String runner, int run, int pos,
			String testCaseName, String classificationTag) throws InvalidFormatException, IOException {
		TestCase testCase1 = new TestCase();
		testCase1.setCaseName(testCaseName);
		testCase1.setDescription(testCaseTitle);
		testCase1.setClassificationTag(classificationTag);
		testCase1.setActive(run);
		testCase1.setPositive(pos);
		testCase1.setCreatedBy("abc");
		testCase1.setRunner(setRunner(runner));

		// TODO MANOJ make sure suite has plan set
		// if (ddtRemote != null) {
		// ddtRemote.startTestCase(testPlan, suite, null);
		// }
		// List<TestStep> testStepsList1 = ;
		// System.out.println("Objects  "
		// +
		// testStepsList1.get(0).getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier());
		// System.out.println("Params  " +
		// testStepsList1.get(0).getTestStepId().getTestParamDatas().get(0).getParam().getParamName());
		List<TestStep> step = testStepListCreation(ddtRemote, excelAccess, sheetName, actionColNo, stepParamColNo, paramGrpColNo, runnerTypeColNo, testCaseTitleColNo, valueColNo,
				tcEndRow, runner, testCase1);
		testCase1.setTestSteps(step);
		return testCase1;
	}

	/**
	 * @param tcType
	 * @return
	 */
	private int caseType(String tcType) {
		int pos;
		if (tcType.equalsIgnoreCase("Positive")) {
			pos = 1;
		} else {
			pos = 0;
		}
		return pos;
	}

	/**
	 * @param execute
	 * @return
	 */
	private int runCase(String execute) {
		int run;
		if (execute.equalsIgnoreCase("Y")) {
			run = 1;
		} else {
			run = 0;
		}
		return run;
	}

	/**
	 * @return
	 */
	private Map<Object, Object> extractXlsReader() {
		Map<Object, Object> categoryMap = new HashMap<Object, Object>();
		try {
			InputStream is = new FileInputStream(UtlConf.getProperty("xlsPath.readXls"));
			mainXlsProps = new Properties();
			mainXlsProps.load(is);
			for (Map.Entry<Object, Object> entry : mainXlsProps.entrySet()) {
				categoryMap.put((String) entry.getKey(), (String) entry.getValue());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryMap;
	}

	/**
	 * @param runner
	 * @return
	 */
	private Runner setRunner(String runner) {
		Runner runner1 = new Runner();
		runner1.setRunnerName(runner);
		return runner1;
	}

	/**
	 * @param data
	 *            .excelAccess
	 * @param sheetName
	 * @param testCaseNameCol
	 * @param testClassficColNo
	 * @param testCaseScenarioColNo
	 * @param slNo
	 * @param testScenarioName
	 * @return
	 */
	private TestPlanId planCreation(List<TestScenario> testScenarioList, List<Functional> functionalList) {
		TestPlanId planId = new TestPlanId();
		planId.setTestScenariosList(testScenarioList);
		planId.setFunctionals(functionalList);
		return planId;
	}

	/**
	 * @param excelAccess
	 * @param sheetName
	 * @param testCaseNameCol
	 * @param slNo
	 * @param featureList
	 * @return
	 */
	private Functional createFunctional(ExcelAccess excelAccess, String sheetName, int testCaseNameCol, int slNo, int autVersionCol, List<Feature> featureList) {
		Functional functional = new Functional();
		String testFun = excelAccess.getCellValue(sheetName, DataConstants.DEFAULT_VAL, testCaseNameCol);
		functional.setFunctionalName(testFun);
		functional.setFeatures(featureList);

		String testApp = excelAccess.getCellValue(sheetName, DataConstants.DEFAULT_VAL, slNo);
		Application application = new Application();
		application.setAppName(testApp);

		String autVersion = excelAccess.getCellValue(sheetName, DataConstants.DEFAULT_VAL, autVersionCol);
		application.setAutVersion(autVersion);
		functional.setApplication(application);
		return functional;
	}

	/**
	 * @param excelAccess
	 * @param sheetName
	 * @param testClassficColNo
	 * @param testSuitesList
	 * @param testScenarioList
	 * @return
	 */
	private Feature featureCreation(ExcelAccess excelAccess, String sheetName, int testClassficColNo, List<TestSuite> testSuitesList, List<TestScenario> testScenarioList) {
		Feature feature = new Feature();
		feature.setTestScenarios(testScenarioList);
		String testFea = excelAccess.getCellValue(sheetName, DataConstants.DEFAULT_VAL, testClassficColNo);
		feature.setFeatureName(testFea);
		feature.setTestSuites(testSuitesList);
		return feature;
	}

	/**
	 * @param testScenarioName
	 * @param testSuitesList
	 * @return
	 */
	private TestScenario scenarioCreation(ExcelAccess excelAccess, String sheetName, int testCaseScenarioColNo, List<TestSuite> testSuitesList) {
		TestScenario testscenario = new TestScenario();
		String testScenarioName = excelAccess.getCellValue(sheetName, getTcStartRow(), testCaseScenarioColNo);
		testscenario.setTestScenarioName(testScenarioName);
		testscenario.setTestSuites(testSuitesList);
		return testscenario;
	}

	/**
	 * @param excelAccess
	 * @param sheetName
	 * @param testCaseScenarioColNo
	 * @param testCasesList
	 * @param testSuite
	 */
	private TestSuite suiteCreation(ExcelAccess excelAccess, String sheetName, int testCaseScenarioColNo, List<TestCase> testCasesList) {
		TestSuite testSuite = new TestSuite();
		TestSuiteId testsuiteid = new TestSuiteId();
		String suiteName = excelAccess.getCellValue(sheetName, DataConstants.DEFAULT_VAL, testCaseScenarioColNo);
		testsuiteid.setSuiteName(suiteName);
		testsuiteid.setTestCases(testCasesList);
		testSuite.setTestSuiteId(testsuiteid);
		return testSuite;
	}

	/**
	 * @param ddtRemote
	 * @param excelAccess
	 * @param data
	 *            .testPlan
	 * @param data
	 *            .tcStartRow
	 * @param sheetName
	 * @param actionColNo
	 * @param stepParamColNo
	 * @param paramGrpColNo
	 * @param runnerTypeColNo
	 * @param testCaseTitleColNo
	 * @param valueColNo
	 * @param tcEndRow
	 * @param suite
	 * @param runner
	 * @param testCase1
	 * @param testStepsList1
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	private List<TestStep> testStepListCreation(DdtRemoteInterface2 ddtRemote, ExcelAccess excelAccess, String sheetName, int actionColNo, int stepParamColNo, int paramGrpColNo,
			int runnerTypeColNo, int testCaseTitleColNo, int valueColNo, int tcEndRow, String runner, TestCase testCase1) throws InvalidFormatException, IOException {
		List<TestStep> testStepsList1 = new ArrayList<TestStep>();
		String actionObject;
		String actionType;
		String actionParam;
		String obj;
		String runnerTstStep;
		for (int strtTCs = getTcStartRow(); strtTCs <= tcEndRow; strtTCs++) {
			actionObject = excelAccess.getCellValue(sheetName, strtTCs, paramGrpColNo);
			actionType = excelAccess.getCellValue(sheetName, strtTCs, actionColNo);
			actionParam = excelAccess.getCellValue(sheetName, strtTCs, valueColNo);

			// obj = getObjectPath(excelAccess, DataConstants.OBJECTS,
			// actionObject);
			runnerTstStep = excelAccess.getCellValue(sheetName, strtTCs, runnerTypeColNo);
			TestStep testSteps1 = stepCreation(ddtRemote, excelAccess, sheetName, stepParamColNo, testCaseTitleColNo, runner, actionType, actionParam, actionObject, runnerTstStep,
					strtTCs);
			logger.info(" testSteps object " + testSteps1.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier());
			logger.info("Params  " + testSteps1.getTestStepId().getTestParamDatas().get(0).getParam().getParamName());
			logger.info(" testSteps actions " + testSteps1.getActions().getActionName());
			logger.info("StepParam  " + testSteps1.getStepParam());
			testStepsList1.add(testSteps1);
		}
		return testStepsList1;
	}

	/**
	 * @param ddtRemote
	 * @param excelAccess
	 * @param data
	 *            .testPlan
	 * @param sheetName
	 * @param stepParamColNo
	 * @param testCaseTitleColNo
	 * @param suite
	 * @param runner
	 * @param testCase1
	 * @param actionObject
	 * @param actionType
	 * @param actionParam
	 * @param obj
	 * @param runnerTstStep
	 * @param strtTCs
	 * @return
	 */
	private TestStep stepCreation(DdtRemoteInterface2 ddtRemote, ExcelAccess excelAccess, String sheetName, int stepParamColNo, int testCaseTitleColNo, String runner,
			String actionType, String actionParam, String obj, String runnerTstStep, int strtTCs) {
		TestStep testSteps1 = testStep(excelAccess, sheetName, stepParamColNo, testCaseTitleColNo, runner, actionType, actionParam, obj, runnerTstStep, strtTCs);
		// if (ddtRemote != null) {
		// ddtRemote.testStep(testPlan, suite, testCase1, null, testSteps1,
		// null, null);// TODO
		// // Manoj
		// }
		return testSteps1;
	}

	/**
	 * @param excelAccess
	 * @param sheetName
	 * @param stepParamColNo
	 * @param testCaseTitleColNo
	 * @param runner
	 * @param actionObject
	 * @param actionType
	 * @param actionParam
	 * @param obj
	 * @param runnerTstStep
	 * @param strtTCs
	 * @return
	 */
	private TestStep testStep(ExcelAccess excelAccess, String sheetName, int stepParamColNo, int testCaseTitleColNo, String runner, String actionType, String actionParam,
			String obj, String runnerTstStep, int strtTCs) {
		TestStep testSteps1 = new TestStep();
		TestStepId teststepid = new TestStepId();
		String stpPr = excelAccess.getCellValue(sheetName, strtTCs, stepParamColNo);
		testSteps1.setStepParam(stpPr);
		Runner runnerStep = setRunner(runnerTstStep);
		testSteps1.setRunner(runnerStep);
		Objects objBtn1 = createObject(excelAccess, runner, obj);
		Param param = createParams(actionParam, objBtn1);
		List<TestParamData> testParamDataList = new ArrayList<TestParamData>();
		TestParamData testparamdatas = createTestParamData(excelAccess, sheetName, testCaseTitleColNo, param, actionParam);
		testParamDataList.add(testparamdatas);
		teststepid.setTestParamDatas(testParamDataList);
		testSteps1.setTestStepId(teststepid);
		Actions actions1 = callAction(actionType);
		testSteps1.setActions(actions1);
		return testSteps1;
	}

	/**
	 * @param runner
	 * @param actionObject
	 * @param obj
	 * @return
	 */
	private Objects createObject(ExcelAccess excelAccess, String runner, String obj) {

		Objects objBtn1 = new Objects();
		ObjectsId objIdBtn1 = new ObjectsId();
		String realPath = null;
		objIdBtn1.setObjectName(obj);
		// if (!runner.equals("SEL") ) {
		if (!((runner.equals("SEL")) || (runner.equals("SQUISH")))) {
			objIdBtn1.setIndentifier(obj);
		} else {
			try {
				realPath = getObjs.getObjectPath(excelAccess, obj);
			} catch (Exception e) {
				logger.error("Object Path identifing error");
			}
			objIdBtn1.setIndentifier(realPath);
		}
		objBtn1.setObjectsId(objIdBtn1);
		return objBtn1;
	}

	/**
	 * @param excelAccess
	 * @param sheetName
	 * @param testCaseTitleColNo
	 * @param param
	 * @return
	 */
	private TestParamData createTestParamData(ExcelAccess excelAccess, String sheetName, int testCaseTitleColNo, Param param, String actionParam) {
		TestParamData testparamdatas = new TestParamData();
		testparamdatas.setParam(param);

		TestParamDataId dataSet = new TestParamDataId();
		String testSet = excelAccess.getCellValue(sheetName, DataConstants.DEFAULT_VAL, testCaseTitleColNo);
		dataSet.setTestSet(testSet);
		dataSet.setParamValue(actionParam);
		testparamdatas.setTestParamDataId(dataSet);
		return testparamdatas;
	}

	/**
	 * @param actionParam
	 * @param objBtn1
	 * @return
	 */
	private Param createParams(String actionParam, Objects objBtn1) {
		Param param = new Param();
		param.setObjects(objBtn1);
		param.setParamName(actionParam);
		return param;
	}

	/**
	 * @param actionType
	 * @return
	 */
	private Actions callAction(String actionType) {
		Actions actions1 = new Actions();
		actions1.setActionName(actionType);
		return actions1;
	}

	private int getTCStrtRow(ExcelAccess excelAccess, String sheetName) {
		// TODO Auto-generated method stub
		int strtExe;

		for (strtExe = DataConstants.DEFAULT_VAL; strtExe < excelAccess.getTotalRowCount(sheetName); strtExe++) {

			if (excelAccess.getCellValue(sheetName, strtExe, DataConstants.DEFAULT_VAL).equals(DataConstants.SL_NO)) {

				break;
			}
		}
		return strtExe + 1;
	}

	private static String getConfigFilePath() {
		String path = System.getProperty("DDT_XLS_PROPERTIES_FILE_PATH");
		if (path == null) {
			path = UtlConf.getProperty("xlsPath.readXls");
		}
		if (path == null) {
			URL url = ReadPlan.class.getClassLoader().getResource("xlsreader.properties");
			if (url != null) {
				path = url.getFile();
			}
		}
		logger.info("path " + path);
		return path;
	}

	public int getTcStartRow() {
		return tcStartRow;
	}

	public void setTcStartRow(int tcStartRow) {
		this.tcStartRow = tcStartRow;
	}

	public TestPlan getTestPlan() {
		return this.testPlan;
	}
}