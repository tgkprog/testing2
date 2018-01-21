package org.s2n.ddt.report.detail;

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

import org.s2n.ddt.pojo.Functional;
import org.s2n.ddt.pojo.output.AgentRunResult;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlanResult;
import org.s2n.ddt.pojo.output.TestScenarioResult;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResult;

public class DetailedReportHelper {
	
	private static final Logger logger = Logger.getLogger(DetailedReportHelper.class);
	
	private static String velocityResPath = "/data/ddt/jbossCore/server/node1/deploy/ROOT.war/reports/";
	
	

	public static void ddd(RunResult rr) {
		try {
			logger.info("inside planEnd core after velocity make index");
			
			File root = rr.getReportFilePath();
			File mainI = new File(root, "detail.html");
			//String runName = rr.getRunName();
			List<AgentRunResult> agentList = new ArrayList<AgentRunResult>();

			agentList.addAll(rr.getAgentRunResultList());
			// agentList.add(runRslt.getAgentRunResult("RunResult2"));
			PrintStream ps = new PrintStream(new FileOutputStream(mainI));
			ps.println("<h3>Exilant : DDT Detailed Report </h3>");
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

	public static void getDetails(RunResult arr) throws Exception {
		logger.info("inside planEnd 1");

		//String runName = arr.getRunName();
		List<AgentRunResult> agentList = arr.getAgentRunResultList();

		// agentList.addAll(arr.getAgentList());
		// agentList.add(runRslt.getAgentRunResult("RunResult2"));

		for (int i = 0; i < agentList.size(); i++) {
			//int counter = 0;
			AgentRunResult agentRunResult = (AgentRunResult) agentList.get(i);
	
			TestPlanResult testPlanRslt = agentRunResult.getPlanResult();
			logger.info("a folder " + (i + 1) + " " + agentRunResult.getReportFilePath() + ";");
			
			List<TestScenarioResult> testScenarioRslt = testPlanRslt.getTestScenarioResults();
			Iterator<TestScenarioResult> iterator = testScenarioRslt.iterator();
			
			while (iterator.hasNext()) {
				TestScenarioResult testScenarioResult = (TestScenarioResult) iterator.next();
				List<TestSuiteResult> suiteResults = testScenarioResult.getSuiteResultsList();
				// Iterator<TestSuiteResult> iterator2 =
				// suiteResults.iterator();
				// while (iterator2.hasNext()) {
				// TestSuiteResult testSuiteResult = (TestSuiteResult)
				// iterator2.next();
				Functional functionalList = testPlanRslt.getFunctionalList().get(0);
				htmlReport(suiteResults, agentRunResult, functionalList);

				//counter++;
				logger.info("counter=>>> " + suiteResults.size());
				// }
			}
		}
	}

	public static void htmlReport(List<TestSuiteResult> suiteResults, AgentRunResult agentRR, Functional functionalList) throws Exception {
		
		//java.util.Date date = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy_MM_dd");

		Iterator<TestSuiteResult> iterator2 = suiteResults.iterator();
		//Iterator<TestSuiteResult> iterator3 = suiteResults.iterator();
		
		while (iterator2.hasNext()) {
			TestSuiteResult testSuiteResult = (TestSuiteResult) iterator2.next();
			
			List<TestCaseResult> resultList = testSuiteResult.getCaseResultsList();
			Iterator<TestCaseResult> iter = resultList.iterator(); 
			
			   while(iter.hasNext()) {
				   
					File file = new File("/data/ddt/TestReport"+dateFormat+".html");
					BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				   
				   TestCaseResult testcaseResult = (TestCaseResult) iter.next();
				   
				   List<TestStepResult> stepResult =testcaseResult.getTestStepResultsList();
				   
				   
				   bw.write("<html>" +"\n" +
							"<head>" +"\n" +
							"<title>Automation Test Execution Detailed Report</title>" +"</head> <body>");
				   
				   bw.write("<table name='Detailed Report' border = 5 > <tr><th> TestStep No</th> "+" \n " + "<th> TestStepName </th>" +"\n"+ "<th> Description </th>"+
				    " <th>UserDefinedMessage</th> <th> TestStepResult </th> <th> Duration</th> </tr>");
				   
				   
				    for(int i =0; i< stepResult.size(); i++){
				    	logger.info("inside step " +stepResult.size()+"  ***    " +stepResult );		
				    	bw.write("<tr><td>" + (i+1) + "</td><td>" +stepResult.get(i).getTestStep().getTestStepId().getStepName()+
				    			 "</td><td>" +stepResult.get(i).getComment() + "</td><td>"+stepResult.get(i).getDetailMsgs() +"</td><td>" + stepResult.get(i).getResult() +
				    			 "</td><td>"+ stepResult.get(i).getTimeTaken() +"</td> </tr>");
				    			    
				    	logger.info("Step detailed Msg : "+stepResult.get(i).getDetailMsgs());
				    	System.out.println("STEPSSSSS " +stepResult);
				    	
				    }
				    bw.write("</table></body></html>");
				    bw.close();
			   
			   }

			}
	
		}


	
	public static String getVelocityResPath() {
		return velocityResPath;
	}

	public static void setVelocityResPath(String velocityResPath) {
		DetailedReportHelper.velocityResPath = velocityResPath;
	}

}
