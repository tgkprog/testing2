package org.s2n.ddt.excelreader;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.s2n.ddt.pojo.Feature;
import org.s2n.ddt.pojo.Functional;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.def.DdtRemoteInterface2;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestScenario;
import org.s2n.ddt.util.ExcelAccess;

public class ReadDataSet {
	private static final Logger logger = Logger.getLogger(ReadDataSet.class);

	// public void readDataSetChecker(TestPlan plan, DdtRemoteInterface2
	// ddtRemote, ExcelAccess excelAccess, String currSht) {
	// String sheetName = "_" + currSht;
	// }

	public void readDataSetChecker(TestPlan plan, DdtRemoteInterface2 ddtRemote, ExcelAccess excelAccess, String currSht) throws IOException {
		// String sheetName = UtlConf.getProperty("xls.sheetName1",
		// "_createOrder");//
		// System.getProperty("DDT_MAIN_PROPERTIES_FILE_TESTCASES_SHEETNAME");
		GetObjs getObjs = new GetObjs(excelAccess);
		String sheetName = "_" + currSht;
		if (excelAccess.isSheetExists(sheetName)) {
			for (int strtSuiteCnt = 5; strtSuiteCnt < excelAccess.getTotalRowCount(sheetName); strtSuiteCnt++) {
				String tstCol;
				tstCol = excelAccess.getCellValue(sheetName, strtSuiteCnt, 0);
				if (tstCol.equals("TestSuite")) {
					logger.log(Level.INFO, " TestSuite name " + tstCol);
					String tstSuiteName;
					tstSuiteName = excelAccess.getCellValue(sheetName, strtSuiteCnt, 1);
					String tstCaseCol;
					for (int strtCnt = strtSuiteCnt + 1; strtCnt < excelAccess.getTotalRowCount(sheetName); strtCnt++) {
						tstCaseCol = excelAccess.getCellValue(sheetName, strtCnt, 0);
						if (tstCaseCol.equals("testCase")) {

							int stepCntr = strtCnt + 1;
							String tstCaseName;
							tstCaseName = excelAccess.getCellValue(sheetName, strtCnt, 2);
							logger.log(Level.INFO, " testCaseName " + tstCaseName);
							List<Functional> feaList = plan.getTestPlanId().getFunctionals();
							Iterator<Functional> iter1 = feaList.iterator();
							// For Functional
							while (iter1.hasNext()) {
								iterFunction(plan, ddtRemote, excelAccess, sheetName, stepCntr, tstCaseName, iter1, getObjs, tstSuiteName);
							}
						}
					}
				}
			}
		} else {
			logger.log(Level.INFO, "Sheetname does not exist");
		}
	}

	/**
	 * @param plan
	 * @param ddtRemote
	 * @param excelAccess
	 * @param sheetName
	 * @param stepCntr
	 * @param tstCaseName
	 * @param iter1
	 */
	private void iterFunction(TestPlan plan, DdtRemoteInterface2 ddtRemote, ExcelAccess excelAccess, String sheetName, int stepCntr, String tstCaseName,
			Iterator<Functional> iter1, GetObjs getObjs, String tstSuiteName) {
		Functional functional = (Functional) iter1.next();
		List<Feature> featureList = functional.getFeatures();
		Iterator<Feature> iter2 = featureList.iterator();
		logger.info("For Functional ++++");
		// For Features
		while (iter2.hasNext()) {
			iterFeature(plan, ddtRemote, excelAccess, sheetName, stepCntr, tstCaseName, iter2, getObjs, tstSuiteName);
		}
	}

	/**
	 * @param plan
	 * @param ddtRemote
	 * @param excelAccess
	 * @param sheetName
	 * @param stepCntr
	 * @param tstCaseName
	 * @param iter2
	 */
	private void iterFeature(TestPlan plan, DdtRemoteInterface2 ddtRemote, ExcelAccess excelAccess, String sheetName, int stepCntr, String tstCaseName,
			Iterator<Feature> iter2, GetObjs getObjs, String tstSuiteName) {
		Feature feature = iter2.next();
		List<TestScenario> testScList = feature.getTestScenarios();
		Iterator<TestScenario> iter3 = testScList.iterator();
		logger.info("For Features ++++");
		// For TestScenario
		while (iter3.hasNext()) {
			iterScenario(plan, ddtRemote, excelAccess, sheetName, stepCntr, tstCaseName, iter3, getObjs, tstSuiteName);
		}
	}

	/**
	 * @param plan
	 * @param ddtRemote
	 * @param excelAccess
	 * @param sheetName
	 * @param stepCntr
	 * @param tstCaseName
	 * @param iter3
	 */
	private void iterScenario(TestPlan plan, DdtRemoteInterface2 ddtRemote, ExcelAccess excelAccess, String sheetName, int stepCntr, String tstCaseName,
			Iterator<TestScenario> iter3, GetObjs getObjs, String tstSuiteName) {
		TestScenario testScenario = (TestScenario) iter3.next();

		List<TestSuite> suiteList = testScenario.getTestSuites();
		Iterator<TestSuite> iter4 = suiteList.iterator();
		logger.info("For TestScenario ++++");
		// For TestSuite
		while (iter4.hasNext()) {
			iterSuite(plan, ddtRemote, excelAccess, sheetName, stepCntr, tstCaseName, iter4, getObjs, tstSuiteName);
		}
	}

	/**
	 * @param plan
	 * @param ddtRemote
	 * @param excelAccess
	 * @param sheetName
	 * @param stepCntr
	 * @param tstCaseName
	 * @param iter4
	 */
	private void iterSuite(TestPlan plan, DdtRemoteInterface2 ddtRemote, ExcelAccess excelAccess, String sheetName, int stepCntr, String tstCaseName,
			Iterator<TestSuite> iter4, GetObjs getObjs, String tstSuiteName) {
		
		TestSuite suite1 = iter4.next();
		
		try {
			TestSuite suite = (TestSuite) suite1.clone();
			int caseCnt = suite.getTestSuiteId().getTestCases().size();
			int currToTest = 0;
			int tstCaseCntr = 0;
			logger.info("For TestSuite ++++");
			// For TestCase
			if (suite.getTestSuiteId().getSuiteName().equalsIgnoreCase(tstSuiteName)) {
				ddtRemote.startTestSuite(plan, suite, null);
				logger.info("Suite size " + suite.getTestSuiteId().getTestCases().size());
				for (; tstCaseCntr < caseCnt; tstCaseCntr++) {
					iterCase(plan, ddtRemote, excelAccess, sheetName, stepCntr, tstCaseName, suite, currToTest, tstCaseCntr, getObjs);
				}
				ddtRemote.endTestSuite(plan, suite, null);
			}
		} catch (CloneNotSupportedException e) {
			logger.error("Error while cloning TestSuite   "+e);
		}

	}

	/**
	 * @param plan
	 * @param ddtRemote
	 * @param excelAccess
	 * @param sheetName
	 * @param stepCntr
	 * @param tstCaseName
	 * @param suite
	 * @param currToTest
	 * @param tstCaseCntr
	 */
	private void iterCase(TestPlan plan, DdtRemoteInterface2 ddtRemote, ExcelAccess excelAccess, String sheetName, int stepCntr, String tstCaseName,
			TestSuite suite, int currToTest, int tstCaseCntr, GetObjs getObjs) {

		TestCase testCase = suite.getTestSuiteId().getTestCases().get(tstCaseCntr);
		try {
			TestCase tcase = (TestCase) testCase.clone();

			if (tstCaseName.equalsIgnoreCase(tcase.getCaseName())) {

				logger.info("tcase.getTestSteps().size() ==" + tcase.getTestSteps().size());
				logger.info("tcase.getTestSteps().size() ==" + tcase.getCaseName());
				logger.info("For TestCase ++++");
				String tstStep;
				tstStep = excelAccess.getCellValue(sheetName, stepCntr, 0);
				if (tstStep.equals("steps")) {
					int dataSetCntr = stepCntr + 2;
					logger.info("dataSetCntr   before loop" + dataSetCntr);
					for (int dataSetRowCntr = dataSetCntr; dataSetRowCntr <= excelAccess.getTotalRowCount(sheetName); dataSetRowCntr++) {
						if (ddtRemote != null) {
							ddtRemote.startTestCase(plan, suite, null, tcase);
						}
						String dataSet;
						logger.log(Level.INFO, "dataSetRowCntr in side loop    " + dataSetRowCntr);
						dataSet = excelAccess.getCellValue(sheetName, dataSetRowCntr, 0);
						logger.info("dataSet  " + dataSet);
						for (int tstStepCtr = 0; tstStepCtr < tcase.getTestSteps().size(); tstStepCtr++) {
							iterSteps(plan, ddtRemote, excelAccess, sheetName, stepCntr, suite, currToTest, tstCaseCntr, tcase, dataSetRowCntr,
									tstStepCtr, getObjs);
						}
						logger.log(Level.INFO, "dataSetRowCntr in side loop aftr step    " + dataSetRowCntr);
						ddtRemote.endTestCase(plan, suite, tcase, null, null);
						if (!dataSet.equalsIgnoreCase("dataset")) {
							logger.info("dataSetRowCntr in break  " + dataSetRowCntr);
							break;
						}
					}
				}
			}
		} catch (CloneNotSupportedException cEx) {
			logger.error(" Error while cloning the testCase");
		}

	}

	/**
	 * @param plan
	 * @param ddtRemote
	 * @param excelAccess
	 * @param sheetName
	 * @param stepCntr
	 * @param suite
	 * @param currToTest
	 * @param tstCaseCntr
	 * @param tcase
	 * @param dataSetRowCntr
	 * @param tstStepCtr
	 */
	private void iterSteps(TestPlan plan, DdtRemoteInterface2 ddtRemote, ExcelAccess excelAccess, String sheetName, int stepCntr, TestSuite suite,
			int currToTest, int tstCaseCntr, TestCase tcase, int dataSetRowCntr, int tstStepCtr, GetObjs getObjs) {
		String stepParam;
		String paramGroupObject;
		String value;
		logger.info("For TestSteps ++++");
		try {
			TestStep testStep = tcase.getTestSteps().get(tstStepCtr);
			TestStep step = (TestStep) testStep.clone();
			for (int colStrt = 1; colStrt < excelAccess.getTotalColumnCount(sheetName); colStrt += 4) {
				// int stepNo =
				// Integer.parseInt(excelAccess.getCellValue(sheetName,
				// stepCntr, colStrt));
				int stepNo = (int) excelAccess.getCellNumericValue(sheetName, stepCntr, colStrt);
				logger.info(" Step No  " + stepNo);
				logger.log(Level.INFO, "Case :" + tstCaseCntr + ", tst step :" + tstStepCtr + ", currToTest " + currToTest);
				if (stepNo != 0 && stepNo == tstStepCtr + 1) {
					logger.log(Level.INFO, "stepNo     " + stepNo + "  tstStepCtr  " + (tstStepCtr + 1));
					logger.log(Level.INFO, " TestStepNo  " + stepNo);
					stepParam = excelAccess.getCellValue(sheetName, dataSetRowCntr, colStrt);
					paramGroupObject = excelAccess.getCellValue(sheetName, dataSetRowCntr, colStrt + 1);
					value = excelAccess.getCellValue(sheetName, dataSetRowCntr, colStrt + 2);
					if (value.length() == 0) {
						break;
					}

					step.setStepParam(stepParam);
					String realPath = null;
					step.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().setObjectName(paramGroupObject);
					if (!tcase.getRunner().getRunnerName().equals("SEL")) {
						step.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().setIndentifier(paramGroupObject);
					} else {
						try {
							realPath = getObjs.getObjectPath(excelAccess, paramGroupObject);
						} catch (Exception e) {
							logger.error("Object Path identifing error");
						}
						step.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().setIndentifier(realPath);
					}
					step.getTestStepId().getTestParamDatas().get(0).getParam().setParamName(value);
					logger.log(Level.INFO, " stepParam " + stepParam + " paramGroupObject  " + paramGroupObject + " value  " + value + "  ");

				}
			}
			if (ddtRemote != null) {
				ddtRemote.testStep(plan, suite, tcase, null, step, null, null);
			}
		}catch(CloneNotSupportedException cEx){
			logger.error(" Error while cloning testStep ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
