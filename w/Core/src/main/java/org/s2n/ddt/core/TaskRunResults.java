package org.s2n.ddt.core;

/*  can remove this ?  ***/

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.pojo.input.MasterPlan;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.pojo.output.TaskResult;
import org.s2n.ddt.test.Reports;
import org.s2n.ddt.util.http.HttpData;

public class TaskRunResults {
	private static final Logger logger = Logger.getLogger(TaskRunResults.class);
	private static Map<String, RunResult> results = new HashMap<String, RunResult>();
	private static String urlTo = "http://localhost:8080/core/hlp/planEndFromAgent.jsp";
	private static int options = 0;
	private static HttpData hDat = new HttpData(urlTo, options, null);
	private static File pa = null;

	public static void executePlan(MasterPlan mPlan) {
		RunResult runR = new RunResult();

		Date dt = new Date(); // testPlanResult.getDateStarted();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");

		final String dts = sdf.format(dt);
		File root = new File(UtlConf.getProperty("reports.home") + "/r/" + mPlan.getMasterPlanName() + "/" + dts + "/lane1/clone1/rep1/task1/rep1");
		pa = root;
		root.mkdirs();
		runR.setReportFilePath(root);
		final String runName = mPlan.getMasterPlanName() + "_" + dts;
		runR.setRunName(runName);
		mPlan.setMasterPlanName(runName);
		results.put(runName, runR);

	}

	public static void result(TaskResult taskResult) {
		try {
			logger.info("result ");
			final String runName = taskResult.getTestPlanResult().getTestPlanRunName();
			logger.info("result runName 11:" + runName);
			Date dt = new Date(); // testPlanResult.getDateStarted();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");

			// RunResult runResult = results.get(runName);
			// if(runResult == null){
			// logger.info("result main not found creating palce holder");
			// }
			File nDir = org.s2n.ddt.report.detail.DetailedReportHelperTask.getTaskReportDir(taskResult.getTestPlan().getTestPlanRunName(), dt, "test1", 1, 1, "test2", 1, 1);
			nDir.mkdirs(); // Creating the directory for the detailed report
			taskResult.setTaskDetailReportDir(nDir);

			Reports re1 = new Reports();
			re1.detailPlan(taskResult);
		} catch (Throwable e) {
			logger.warn("rslt " + e, e);
		}
	}

	public static void setCoreUrl(String s) {
		hDat.setUrl(s);
	}

	public static final String getVersion() {
		return "1";
	}
}
