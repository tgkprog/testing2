package org.s2n.ddt.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.pojo.Feature;
import org.s2n.ddt.pojo.Functional;
import org.s2n.ddt.pojo.output.AgentRunResult;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.pojo.output.TaskResult;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlanResult;
import org.s2n.ddt.pojo.output.TestScenarioResult;
import org.s2n.ddt.pojo.output.TestSuiteResult;

public class ReportHelper {
	private static final Logger logger = Logger.getLogger(ReportHelper.class);

	private static String velocityResPath = UtlConf.getProperty("velocityResource.path");

	public static void ddd(RunResult rr) {
		try {
			logger.info("inside planEnd core after velocity make index");

			File root = rr.getReportFilePath();
			File mainI = new File(root, "index.html");
			String runName = rr.getRunName();
			List<AgentRunResult> agentList = new ArrayList<AgentRunResult>();

			agentList.addAll(rr.getAgentRunResultList());
			// agentList.add(runRslt.getAgentRunResult("RunResult2"));
			PrintStream ps = new PrintStream(new FileOutputStream(mainI));
			ps.println("<h3>Exilant : DDT Report </h3>");
			for (int i = 0; i < agentList.size(); i++) {

				AgentRunResult agentRunResult = (AgentRunResult) agentList.get(i);
				File rpt = agentRunResult.getReportFilePath();
				String s = agentRunResult.getAgentName() + " " + agentRunResult.getTestPlan().getTestPlanRunName();
				s = s.replace("/", " ");
				s = s.replace("_", " ");
				s = s.replace(".", " ");
				ps.println((i + 1) + " <a href=\"" + rpt.getName() + "\" >" + s + "</a><br>");

			}
			ps.println("<br>End of index");
			ps.close();
		} catch (FileNotFoundException e) {
			logger.log(Level.WARN, "final rpt err :" + e, e);
		}
	}

	public static void getDetails(TaskResult tr) throws Exception {
		logger.info("inside planEnd 1");

		// String runName = arr.getRunName();
		// List<AgentRunResult> agentList = arr.getAgentRunResultList();

		// agentList.addAll(arr.getAgentList());
		// agentList.add(runRslt.getAgentRunResult("RunResult2"));

		// for (int i = 0; i < agentList.size(); i++) {
		int counter = 0;
		TestPlanResult testPlanRslt = tr.getTestPlanResult();
		logger.info("a folder " + " " + tr.getReportFilePath() + ";");
		List<Functional> functionalList = testPlanRslt.getFunctionalList();
		for (Functional functional : functionalList) {
			List<Feature> features = functional.getFeatures();
			for (Feature feature : features) {
				List<TestScenarioResult> testScenarios = feature.getTestScenariosResult();
				for (TestScenarioResult testScenario : testScenarios) {
					List<TestSuiteResult> testSuites = testScenario.getSuiteResultsList();
					htmlReport(testSuites, testScenario, feature, functional, tr);
				}

			}
		}

		// List<TestScenarioResult> testScenarioRslt =
		// testPlanRslt.getTestScenarioResults();
		// Iterator<TestScenarioResult> iterator = testScenarioRslt.iterator();
		// while (iterator.hasNext()) {
		// TestScenarioResult testScenarioResult = (TestScenarioResult)
		// iterator.next();
		// List<TestSuiteResult> suiteResults =
		// testScenarioResult.getSuiteResultsList();
		// // Iterator<TestSuiteResult> iterator2 =
		// // suiteResults.iterator();
		// // while (iterator2.hasNext()) {
		// // TestSuiteResult testSuiteResult = (TestSuiteResult)
		// // iterator2.next();
		// Functional functionalList = testPlanRslt.getFunctionalList().get(0);
		// htmlReport(suiteResults, agentRunResult, functionalList);
		// counter++;
		// logger.info("counter=>>> " + suiteResults.size());
		// // }
	}

	// }
	// }

	public static void htmlReport(List<TestSuiteResult> suiteResults, TestScenarioResult testScenario, Feature feature, Functional functionalList,
			TaskResult tr) throws Exception {

		java.util.Date date = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy_MM_dd");

		functionalList
				.setPercentagePassCount(Math.round((float) ((100 * ((double) functionalList.getPassCount() / (double) functionalList.getTotalCount()) * 100) / 100)));
		functionalList
				.setPercentageFailCount(Math.round((float) ((100 * ((double) functionalList.getFailCount() / (double) functionalList.getTotalCount()) * 100) / 100)));
		functionalList
				.setPercentageSkipCount(Math.round((float) ((100 * ((double) functionalList.getSkipCount() / (double) functionalList.getTotalCount()) * 100) / 100)));
		functionalList.setPercentageNotExecuted(Math.round((float) ((100 * ((double) functionalList.getNotExecutedCount() / (double) functionalList
				.getTotalCount()) * 100) / 100)));

		Iterator<TestSuiteResult> iterator2 = suiteResults.iterator();
		Iterator<TestSuiteResult> iterator3 = suiteResults.iterator();
		while (iterator2.hasNext()) {
			TestSuiteResult testSuiteResult = (TestSuiteResult) iterator2.next();

			List<TestCaseResult> resultList = testSuiteResult.getCaseResultsList();

			testSuiteResult.setExecuted(testSuiteResult.getTotalCount() - testSuiteResult.getNotExecuted());
			testSuiteResult.setPercentagePassCount(Math.round((float) ((100 * ((double) testSuiteResult.getPassCount() / (double) testSuiteResult
					.getTotalCount()) * 100) / 100)));
			testSuiteResult.setPercentageFailCount(Math.round((float) ((100 * ((double) testSuiteResult.getFailCount() / (double) testSuiteResult
					.getTotalCount()) * 100) / 100)));
			testSuiteResult.setPercentageSkipCount(Math.round((float) ((100 * ((double) testSuiteResult.getSkipCount() / (double) testSuiteResult
					.getTotalCount()) * 100) / 100)));
			testSuiteResult.setPercentageNotExecuted(Math.round((float) ((100 * ((double) testSuiteResult.getNotExecuted() / (double) testSuiteResult
					.getTotalCount()) * 100) / 100)));

			System.out.println("resultList >>" + suiteResults.size());
			logger.info("resultList >>" + tr.getAgentName());

		}

		VelocityEngine ve = new VelocityEngine();

		ve.setProperty("resource.loader", "file");
		Properties p = new Properties();
		p.setProperty("file.resource.loader.path", UtlConf.getProperty("velocityResource.path"));
		ve.init(p);

		// Template template = ve.getTemplate("HtmlTemplate.vm");
		Template template = ve.getTemplate("HTMLdocument1.vm");
		Template template1 = ve.getTemplate("TestCaseTemplate.vm");
		// File file = new
		// File("/Users/ritusahu/Desktop/july_15/tc1/TestHtmlReport.html");
		File root = tr.getReportFilePath().getParentFile();
		File file = new File(root, tr.getAgentName() + "Report.html");
		logger.info("In Report Helper the agent Name ==" + tr.getAgentName());
		VelocityContext context = new VelocityContext();

		context.put("SuitRslt", suiteResults);
		context.put("Date", dateFormat.format(date));

		context.put("functionalList", functionalList);

		context.put("agentName", tr);

		logger.info("test case isze() ;;;; " + suiteResults.get(0).getTimeDuration() + functionalList.getFunctionalName());

		Writer writer = new StringWriter();

		template.merge(context, writer);

		logger.info("calling vm");
		FileWriter fw = new FileWriter(file);

		BufferedWriter out = new BufferedWriter(fw);

		out.write(writer.toString());

		out.close();

		logger.info(" vm done :" + tr.getReportFilePath());

		// functionalList.getSkipCount()
		while (iterator3.hasNext()) {
			logger.info("inside iterator2");
			TestSuiteResult testSuiteResult = (TestSuiteResult) iterator3.next();
			// File file1 = new
			// File("/Users/ritusahu/Desktop/july_15/tc1/"+testSuiteResult.getTestSuitName()+"_"+agentName.getAgentname()+".html");
			File file1 = new File(root, testSuiteResult.getSuiteName() + "_" + tr.getAgentName() + ".html");
			logger.info("Name of TestCase file --" + file1.getName());
			VelocityContext context1 = new VelocityContext();
			context1.put("SuitRslt", testSuiteResult.getCaseResultsList());
			context1.put("functionalList", functionalList);
			context1.put("testSuiteResult", testSuiteResult);
			Writer writer1 = new StringWriter();
			template1.merge(context1, writer1);
			FileWriter fw1 = new FileWriter(file1);
			BufferedWriter out1 = new BufferedWriter(fw1);
			out1.write(writer1.toString());
			out1.close();
			logger.info("file1 >>>" + file1);

		}

	}

	public static String getVelocityResPath() {
		return velocityResPath;
	}

	public static void setVelocityResPath(String velocityResPath) {
		ReportHelper.velocityResPath = velocityResPath;
	}

}
