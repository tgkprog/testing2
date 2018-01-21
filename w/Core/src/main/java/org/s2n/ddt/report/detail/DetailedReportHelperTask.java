package org.s2n.ddt.report.detail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.pojo.Functional;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.pojo.output.TaskResult;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlanResult;
import org.s2n.ddt.pojo.output.TestScenarioResult;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResult;

public class DetailedReportHelperTask implements Runnable {

	private static final Logger logger = Logger
			.getLogger(DetailedReportHelperTask.class);
	private TaskResult tr;
	private RunResult rr;

	public DetailedReportHelperTask() {

	}

	public DetailedReportHelperTask(TaskResult tr, RunResult rr) {
		this.tr = tr;
		this.rr = rr;
	}

	public void run() {
		try {
			makeReport(tr, rr);
		} catch (Exception e) {
			logger.warn("Detail report run :" + e, e);
		}
	}

	// public static File getTaskReportDir(String planName, Date startDate,
	// String laneName, int laneClone, int laneRepeat, String taskName,
	// BigDecimal taskId, int taskRepeat) {
	// java.util.Date startDate = new java.util.Date();
	public static File getTaskReportDir(String planName, Date startDate,
			String laneName, int laneClone, int laneRepeat, String taskName,
			int taskId, int taskRepeat) {
		// java.util.Date startDate = new java.util.Date();
		SimpleDateFormat sdf = org.s2n.ddt.util.LangUtils
				.getSdfForFormat("yy_MM_dd_HH_mm");
		String datePart = sdf.format(startDate);
		//
		String reportsRootDir = UtlConf.getProperty("reports.home") + "/r/";
		// File rptDir = new File(reportsRootDir, "/" + planName + "/" +
		// datePart + "/" + laneName + "/c" + laneClone + "/r" + laneRepeat +
		// "/taskName_"
		// + taskId + "_" + taskRepeat);

		File rptDir = new File(reportsRootDir, "d/" + planName + "/" + datePart
				+ "/" + laneName + "/clone" + laneClone + "/r" + laneRepeat
				+ "/t_" + taskId + "_" + taskRepeat);
		logger.info("Directory location for the detailed report :[" + rptDir
				+ "]");
		// File file = new File(rptDir, "DetailedReport" + + ".html");
		return rptDir;
	}

	public static File getTaskReportDetailFile(TaskResult tr, RunResult rr) {
		Date dat = rr.getStartDate();
		SimpleDateFormat sdf = org.s2n.ddt.util.LangUtils
				.getSdfForFormat("yy_MM_dd_HH_mm");
		String datePart = sdf.format(dat);

		if (tr.getTaskName() == null || tr.getTaskName().length() == 0) {
			tr.setTaskName("Task1");
		}
		// tr.setReputationNameOfTask("DDT_DetailReport");//?
		// File rptFile = new File(tr.getTaskDetailReportDir(),
		// tr.getTaskName()+ "_" + datePart + "_" + tr.getReputationNameOfTask()
		// + ".html");

		tr.setReputationNameOfTask("DDT_DetailReport");// ?
		// File rptFile = new File(tr.getTaskDetailReportDir(),
		// tr.getTaskName()+ "_" + datePart + "_" + tr.getReputationNameOfTask()
		// + ".html");
		File rptFile = new File(tr.getTaskDetailReportDir(), tr.getTaskName()
				+ "_" + tr.getReputationNameOfTask() + ".html");
		// For / testing>>>>>>> .r683X
		logger.info("Detail Report : " + rptFile.getAbsolutePath());
		return rptFile;
	}

	public void makeReport(TaskResult tr, RunResult rr) throws Exception {
		BufferedWriter bw = null;

		logger.info("Getting Results from Task result");
		// String runnerName = tr.getRunnerName();
		try {
			TestPlanResult testPlanResult = tr.getTestPlanResult();

			List<TestScenarioResult> testScenarioResults = testPlanResult
					.getTestScenarioResults();
			File file = getTaskReportDetailFile(tr, rr);
			System.out.println("File : " + file);
			// bw = new BufferedWriter(new FileWriter(file ));
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), Charset.forName("UTF-8")));

			bw.write("<!DOCTYPE html>");
			bw.write("<html>"
					+ "\n"
					+ "<head>"
					+ "\n"
					+ "<meta content=\"text/html; charset=UTF-8\" http-equiv=\"content-type\"/>"
					+ "\n"
					+ "<title>Automation Test Execution Detailed Report</title>"
					+ "</head><body>");

			bw.write("<h4> DDT DETAILED REPORT </h4>");

			bw.write("<p> Lane Name : " + tr.getLaneName());
			bw.write("</p><p> Lane No: " + tr.getLaneNo()
					+ "</p><p> Lane clone id : " + tr.getLaneCloneId());
			bw.write(", lane repeat : " + tr.getLaneRepeatId() + "</p>");
			bw.write("<p> LoggedIn User: " + rr.getUserId() + "  Start Time: "
					+ tr.getStartTime() + "  End Time: " + tr.getEndTime()
					+ "</p>");
			// bw.write("</p><p> Lane No: " + tr.getLaneNo() +
			// "</p><p> Lane clone id : " + tr.getLaneCloneId());
			// bw.write(", lane repeat : " + tr.getLaneRepeatId() + "</p>");

			bw.write("<p>Task Id : " + tr.getTaskId());
			if (tr.getTestSuiteFilePath() != null) {
				// if loaded from data base keep this null
				bw.write(", task plan info :" + tr.getTestSuiteFilePath());
			}
			bw.write(", task repeat id :" + tr.getTaskRepeatId()
					+ tr.getTaskName() + "</p>");

			bw.write("<p>Task Id : " + tr.getTaskId());
			if (tr.getTestSuiteFilePath() != null) {
				// if loaded from data base keep this null
				bw.write(", task plan info :" + tr.getTestSuiteFilePath());
			}
			// bw.write(", task repeat id :" + tr.getTaskRepeatId() +
			// tr.getTaskName() + "</p>");

			for (TestScenarioResult testScenarioResult : testScenarioResults) {
				List<TestSuiteResult> suiteResultsList = testScenarioResult
						.getSuiteResultsList();
				Functional functionalList = testPlanResult.getFunctionalList()
						.get(0);
				htmlReport(suiteResultsList, testPlanResult, functionalList,
						tr, bw);
			}
			bw.write("</body></html>");
		} catch (Exception e) {
			logger.warn("makeReport :" + e, e);
		} finally {
			try {
				bw.close();// should close once only
			} catch (Exception e2) {
				logger.warn("makeReport close bw :" + e2, e2);
			}

		}

	}

	public void htmlReport(List<TestSuiteResult> suiteResults,
			TestPlanResult testPlanResult, Functional functionalList,
			TaskResult tr, BufferedWriter bw) throws Exception {

		logger.info("Inside Html report");
		int testcaseno = 1;
		String runner = null, objName, stepParam, stepValue, actionName;
		actionName = "";
		stepValue = "";
		Iterator<TestSuiteResult> iterator2 = suiteResults.iterator();
		// File rptFile = tr.getTaskDetailRptFile();
		System.out.println("Getting details to generate report");
		while (iterator2.hasNext()) {
			TestSuiteResult testSuiteResult = (TestSuiteResult) iterator2
					.next();
			bw.write("<p>TesSuiteName : "
					+ testSuiteResult.getTestSuite().getTestSuiteId()
							.getSuiteName() + "</p><p> Duration : <b>"
					+ testSuiteResult.getTimeDuration() + "</b></p>");
			List<TestCaseResult> resultList = testSuiteResult
					.getCaseResultsList();
			// Iterator<TestCaseResult> iter = resultList.iterator();
			bw.write("<table border = 5  cellspacing=0 frame=box rules=all ><tr><th>Total No of TestCases </th> <th> PassCount </th>"
					+ " <th>FailCount</th> <th> SkipCount </th> </tr>");
			bw.write("<tr><td>" + testSuiteResult.getTotalCount() + "</td><td>"
					+ testSuiteResult.getPassCount() + "</td><td>"
					+ testSuiteResult.getFailCount() + "</td><td>"
					+ testSuiteResult.getSkipCount() + "</td></tr>");
			bw.write("</table>");

			for (TestCaseResult testCaseResult : resultList) {
				logger.info("TEST CASE RESULT **  " + testCaseResult);

				System.out.println("Getting testCAse result");
				/*
				 * bw.write("<table border = 5 > <tr><th> TestStep No</th> " +
				 * "\n" + "<th> Description </th>" +
				 * " <th>UserDefinedMessage</th> <th> TestStepResult </th><th>Exception </th> <th> Duration</th> </tr>"
				 * );
				 */

				List<TestStepResult> stepResult = testCaseResult
						.getTestStepResultsList();

				bw.write("<p> TestCaseNo : " + testcaseno
						+ "</p> <p> TestCaseName: <b>"
						+ testCaseResult.getTestCaseName()
						+ "</b>  -- Duration: <b>"
						+ testCaseResult.getTimeDuration() + "</b></p>");

				testcaseno++;
				if (stepResult.isEmpty()) {
					bw.write("<b>Test case is skipped</b></p>");
				} else {
					// <<<<<<< .mine
					// bw.write("\n<table border = 5  cellspacing=0 frame= box rules=all ><tr><th> TestStep No</th><th>Runner Name</th><th>Param Object Group</th><th>Action Name</th>");
					// bw.write(
					// "<th>Step Param</th><th> Param Data </th><th> TestStepResult </th><th>Detail Error  Message </th\n <th> Duration</th>  <th> Runner Comment </th></tr>\n");
					//
					// =======
					// bw.write("\n<table border = 5  cellspacing=0 frame= box rules=all ><tr><th> TestStep No</th><th>Runner Name</th><th>Param Object Group</th><th>Action Name</th>");
					// bw.write(
					// "<th>Step Param</th><th> Param Data </th><th> TestStepResult </th><th>Detail Error  Message </th> <th> Duration</th>  <th> Runner Comment </th></tr>\n");
					bw.write("\n<table border = 5  cellspacing=0 frame= box rules=all ><tr><th>No</th><th>Runner Name</th><th>Param Object Group</th><th>Action Name</th>");
					bw.write("<th>Step Param</th><th> Param Data </th><th> Result </th><th> Comment </th> <th>Detail Message </th> <th> Duration</th></tr>\n");

					logger.info("Before For : " + stepResult);
					int i = 1;
					logger.info("Step SIZEEE : " + stepResult.size());
					for (TestStepResult testStepResult : stepResult) {

						String result = null;
						String detailMsg = null;
						if (testStepResult.getResult() == true) {
							result = "PASS";
							// ?//testCaseResult.getDescription();
						} else if (testStepResult.getResult() == false) {
							result = "FAIL";

						}
						detailMsg = testStepResult.getDetailMsgs();
						TestStep tStep = testStepResult.getTestStep();
						TestCase tCase = testCaseResult.getTestCase();

						// if (tStep != null) {
						// if (tStep.getRunner() != null) {
						// runner = tStep.getRunner().getRunnerName();
						// }

						if (tStep != null) {
							if (tStep.getRunner() != null) {
								runner = tStep.getRunner().getRunnerName();
							}
							if (tStep.getActions() != null) {
								actionName = tStep.getActions().getActionName();
								// logger.log(Level.INFO, "getActionname :" +
								// tStep.getActions().getActionName());
							}

							if (tStep.getTestStepId().getTestParamDatas()
									.get(0).getParam().getObjects()
									.getObjectsId().getIndentifier() != null) {
								// logger.log(Level.INFO, "Get Objects are:"
								// +tStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier()
								// );
								objName = tStep.getTestStepId()
										.getTestParamDatas().get(0).getParam()
										.getObjects().getObjectsId()
										.getIndentifier();
								if (tStep.getTestStepId().getTestParamDatas()
										.get(0).getTestParamDataId()
										.getParamValue() != null) {
									stepValue = tStep.getTestStepId()
											.getTestParamDatas().get(0)
											.getTestParamDataId()
											.getParamValue();
								} else {
									// logger.info("GEt Step Value is Null");
									stepValue = "";
								}
							} else {
								objName = "";
							}

							if (tStep.getStepParam() != null) {
								stepParam = tStep.getStepParam();
							} else {
								stepParam = "";
							}

						} else {
							logger.info("Action obj null");
							objName = "";
							stepParam = "";
							stepValue = "";
							actionName = "";
						}
						if (runner.length() == 0 || runner == null) {
							runner = tCase.getRunner().getRunnerName();
						}
						String cmnt = testStepResult.getComment();
						if (cmnt == null) {
							cmnt = "";
						}
						if (detailMsg == null) {
							detailMsg = "";
						}

						bw.write("\n<tr><td>" + (i) + "</td><td>" + runner
								+ "</td><td>" + objName + "</td><td>"
								+ actionName + "</td><td>"
								+ UtlConf.replaceHtmlEnt(stepParam));
						bw.write("\n</td><td>"
								+ UtlConf.replaceHtmlEnt(stepValue)
								+ "</td><td>" + result + "</td><td>"
								+ UtlConf.replaceHtmlEnt(cmnt) + "</td>\n<td>"
								+ UtlConf.replaceHtmlEnt(detailMsg));
						bw.write("\n</td><td>" + +testStepResult.getTimeTaken()
								+ "</td>");

						bw.write(" </tr>");

						i++;
					}
					bw.write("</table>");
				}
			}

		}

		// System.out.println("Detailed Report done");
		logger.info("Detailed Report is done (3)");

	}
}
