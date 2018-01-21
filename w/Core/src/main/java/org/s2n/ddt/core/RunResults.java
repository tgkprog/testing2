package org.s2n.ddt.core;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.pojo.input.MasterPlan;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.pojo.output.TaskResult;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestPlanResult;
import org.s2n.ddt.pojo.output.TestScenarioResult;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResult;
import org.s2n.ddt.report.detail.DetailedReportHelperTask;
import org.s2n.ddt.test.Reports;
import org.s2n.ddt.util.LangUtils;
import org.s2n.ddt.util.http.HttpData;
import org.s2n.ddt.util.threads.PoolOptions;
import org.s2n.ddt.util.threads.DdtPools;

public class RunResults implements Runnable {
	private static final Logger logger = Logger.getLogger(RunResults.class);

	public static final String RESULTS_RPT_PROCESS = "core_results";
	public static final String REPORT_VELOCITY = "core_velocity_rpt";
	private static Map<String, RunResult> results = new HashMap<String, RunResult>();
	private static String urlTo = "http://localhost:8080/core/hlp/planEndFromAgent.jsp";
	private static int options = 0;
	private static HttpData hDat = new HttpData(urlTo, options, null);
	private static File pa = null;


	private TaskResult tr;
	private RunResult runResult;
	
	static {

		try {
			String sMain = UtlConf.getProperty("core.main.pool.size", "1");
			int coreMainPoolSize = LangUtils.getInt(sMain, 1, "coreMainPoolSize");
			sMain = UtlConf.getProperty("core.velocity.pool.size", "1");
			int coreVelocityPoolSize = LangUtils.getInt(sMain, 1, "coreVelocityPoolSize");	
			
			PoolOptions op = new PoolOptions();
			op.setCoreThreads(coreMainPoolSize);
			op.setMaxThreads(coreMainPoolSize);
			DdtPools.initPool(RESULTS_RPT_PROCESS, op);

			op = new PoolOptions();
			op.setCoreThreads(coreVelocityPoolSize);
			op.setMaxThreads(coreVelocityPoolSize);
			DdtPools.initPool(REPORT_VELOCITY, op);
		} catch (Exception e) {
			logger.warn("Core results init :" + e, e);
		}
	}
	
	public RunResults(TaskResult tr, RunResult runResult){
		this.tr = tr;
		this.runResult = runResult;
	}

	public static RunResult initPlan(MasterPlan mPlan) {
		RunResult runR = new RunResult();

		Date dt = new Date(); // testPlanResult.getDateStarted();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
		final String dts = sdf.format(dt);
		StringBuilder reportsRootDir = new StringBuilder().append(UtlConf.getProperty("reports.home"));
		if (!reportsRootDir.toString().endsWith("/")) {
			reportsRootDir.append("/");
		}
		reportsRootDir.append("r/");
		File root = new File(reportsRootDir + mPlan.getMasterPlanName() + "/" + dts + "/");// lane1/clone1/rep1/task1/rep1
		pa = root;
		root.mkdirs();
		runR.setReportFilePath(root);
		final String runName = mPlan.getMasterPlanName() + "_" + dts;
		runR.setRunName(runName);
		runR.setMasterReportName(mPlan.getMasterPlanName());
		// mPlan.setMasterPlanName(runName);
		runR.setUserId(UtlConf.getProperty("user.name", "Anonymous"));
		results.put(runName, runR);
		runR.setStartDate(dt);
		return runR;

	}

	public static RunResult initiatePlan(File xlsPath, String planName) {
		RunResult runR = new RunResult();

		Date dt = new Date(); // testPlanResult.getDateStarted();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
		// testPlan.setTestPlanRunName("Plan_GRX");
		// String relativePathToRes = "/reports/res/";
		final String dts = sdf.format(dt);
		String reportsRootDir = "~/ddt/reports/r/";
		reportsRootDir = UtlConf.getProperty("reports.home", reportsRootDir);
		File root = new File(reportsRootDir, planName + "/" + dts);
		pa = root;
		root.mkdirs();
		runR.setReportFilePath(root);
		// testPlan.setTestDebugInfoFile(root);
		final String runName = planName + "_" + dts;
		runR.setRunName(runName);
		// testPlan.setTestPlanRunName(runName);
		results.put(runName, runR);
		return runR;
	}

	public static RunResult executePlan(TestPlan testPlan) {
		RunResult runR = new RunResult();

		Date dt = new Date(); // testPlanResult.getDateStarted();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
		// testPlan.setTestPlanRunName("Plan_GRX");
		// String relativePathToRes = "/reports/res/";
		final String dts = sdf.format(dt);
		String reportsRootDir = UtlConf.getProperty("reports.home") + "/r/";
		reportsRootDir = UtlConf.getProperty("reports.home", reportsRootDir);
		File root = new File(reportsRootDir + testPlan.getTestPlanRunName() + "/" + dts);
		pa = root;
		root.mkdirs();
		runR.setReportFilePath(root);
		testPlan.setTestDebugInfoFile(root);
		final String runName = testPlan.getTestPlanRunName() + "_" + dts;
		runR.setRunName(runName);
		testPlan.setTestPlanRunName(runName);
		results.put(runName, runR);
		return runR;
	}


	// /tt
	public static void result(TaskResult tr, RunResult runResult) {
		try {
			RunResults rpt = new RunResults(tr, runResult);
			DdtPools.offer(RESULTS_RPT_PROCESS, rpt);
		} catch (Throwable e) {
			logger.warn("rslt start " + e, e);
		}
	}

	public void run() {
		try {
			Date dt = runResult.getStartDate(); // testPlanResult.getDateStarted();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
			// String format = sdf.format(dt);
			logger.info("result 991 :" + tr);
			String runName = "unknown";
			if (runResult != null) {
				runName = runResult.getRunName();

			}
			logger.info("result runName 11:" + runName);
			// RunResult runResult = results.get(runName);
			// TODO see if all results have come and if do remove RunResult
			// TODO keep track of totals and status of master plan execution
			if (runResult == null) {
				logger.info("result main not found creating palce holder");
			}
			final String agentName = tr.getAgentName();
			// String testPlanResult = null;
			// TestPlan testPlan = tr.getTestPlan();

			// testPlan.setTestPlanRunName("Plan_GRX");
			// String relativePathToRes = "/reports/res/";
			// File root = runResult.getReportFilePath();
			// TODO new field for root or renmae //new
			// File("/data/ddt/jbossCore/server/node1/deploy/ROOT.war/reports/"
			// + testPlan.getTestPlanRunName() + "/" + sdf.format(dt) );

			File f2 = new File(pa, agentName + "Report.html");

			logger.info("ag " + tr.getAgentName() + ", f2 " + f2.getAbsolutePath() + "|");
			// agentRunResult2.setPlanResult(testPlanResult);
			tr.setReportFilePath(f2);
			// planName, Date startDate, String laneName, int laneClone, int
			// laneRepeat, String taskName, int taskId, int taskRepeat
			if (tr.getTaskId() == null) {
				tr.setTaskId(new BigDecimal("1"));
			}

			String laneName = tr.getLaneName() + tr.getLaneNo();
			File nDir = DetailedReportHelperTask.getTaskReportDir(runResult.getMasterReportName(), dt, laneName, tr.getLaneCloneId(),
					tr.getLaneRepeatId(), tr.getTaskName(), tr.getTaskId().intValue(), tr.getTaskRepeatId());
			nDir.mkdirs(); // Creating the directory for the detailed report
			tr.setTaskDetailReportDir(nDir);

			resultsPreDb(tr);// prepare
			String rptSmryEnbl = UtlConf.getProperty("report.enableSummary", "1");
			boolean reportsEnabledSmry = LangUtils.isTrue(rptSmryEnbl, true);
			if (runResult.getSummaryReport() && reportsEnabledSmry) {
				//start summary in own pool so its fewer/ single threaded as velocity seems to take a lot of CPU
				Reports rpt = new Reports();
				try {
					rpt.init(tr);
					DdtPools.offer(REPORT_VELOCITY, rpt);//

				} catch (Exception e) {
					logger.warn("rslt rsummary " + e, e);
				}
			}
			String rptDetEnbl = UtlConf.getProperty("report.enableDetail", "1");
			boolean reportsEnabledDet = LangUtils.isTrue(rptDetEnbl, true);
			if (runResult.getDetailReport() && reportsEnabledDet) {
				DetailedReportHelperTask detRpt = new DetailedReportHelperTask(tr, runResult);
				detRpt.run();
			}
			// TODO INDEX
			// File fI = new File(root, "index.html");
			// Object rtn = NetSend.sendObj((Serializable) runResult, hDat,
			// "planEnd");
			// }
		} catch (Throwable e) {
			logger.warn("run rslt " + e, e);
		}
	}

	public static void resultsPreDb(TaskResult tr) {
		double suiteDuration, caseDuration;
		TestPlanResult testPlanResult = tr.getTestPlanResult();

		List<TestScenarioResult> testScenarioResults = testPlanResult.getTestScenarioResults();
		for (TestScenarioResult te : testScenarioResults) {
			List<TestSuiteResult> suiteResults = te.getSuiteResultsList();
			for (TestSuiteResult suiteResult : suiteResults) {
				suiteDuration = 0d;
				for (TestCaseResult caseResult : suiteResult.getCaseResultsList()) {
					caseDuration = 0d;
					for (TestStepResult stepResult : caseResult.getTestStepResultsList()) {
						caseDuration += stepResult.getTimeTaken();
					}
					suiteDuration += caseDuration;
					caseResult.setTimeDuration(caseDuration);
				}
				suiteResult.setTimeDuration(suiteDuration);
			}
		}
	}

	public static void saveToDb() {
		// TODO save results to DB
	}

	public static void setCoreUrl(String s) {
		hDat.setUrl(s);
	}

	public static final String getVersion() {
		return "1.6.10";
	}

}
