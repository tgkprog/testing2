<%@page import="com.exilant.tfw.report.detail.DetailedReportHelper"%><%@
page	import="com.exilant.tfw.bean.UtlConf"%><%@
page import="com.exilant.tfw.report.detail.DetailedReportHelperTask"%><%@
page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
page import="java.io.*"%><%@ 
page
	import="java.util.*,com.exilant.tfw.pojo.output.*,com.exilant.tfw.pojo.input.MasterPlan,com.exilant.tfw.pojo.input.Lane"%><%@ 
page
	import="com.exilant.tfw.pojo.input.Task"%><%@ 
page
	import="com.exilant.tfw.core.Agents,com.exilant.tfw.util.http.*,java.io.*,java.nio.charset.Charset"
	import="com.exilant.tfw.core.master.*,java.math.BigInteger"
	import="com.exilant.tfw.core.RunResults,java.util.*,com.exilant.tfw.excelreader.ReadPlan,com.exilant.tfw.agent.lanes.AgentCache"%><%@
	page import = "org.apache.log4j.Logger,com.exilant.tfw.util.LangUtils" 
	%>
	
	<%!
	
	private static final Logger logger = Logger.getLogger("com.exilant.tfw.core.web.executePlan_jsp");
	%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Starting Plan</title>
</head>
</html>

<body>
	<%@include file="/common/header.jsp"%>
	<%
		String path = request.getParameter("plan");
		String detail = request.getParameter("detail");
		String summary = request.getParameter("summary");
		boolean sumE = summary != null && LangUtils.isTrue(UtlConf.getProperty("report.enableSummary", "1"), true);
		boolean mainE = detail != null && LangUtils.isTrue(UtlConf.getProperty("report.enableDetail", "1"), true);		
		//report.enableDetail=0
		//report.enableSummary
		out.println("<h3>Starting " + path + "</h3>");
		int options = 0;
		HttpData hDat = new HttpData(null, options, null);

		Iterator<AgentDetails> agenItr = Agents.getAgents().values().iterator();
		String coreDomain = "localhost:8080";
		//String coreDomain = "your local system id with port";
		//coreDomain = "10.1.0.167:8180";
		String coreUrl = "http://" + coreDomain + "/core/PlanRunFeedbackFromAgents.jsp";//url that agent should send results to.
		TestPlan plan = null;

		//coreMaster.setLaneListener(laneListener);
		String appid = "grx";
		String planName = "master1";
		BigInteger plnId = null;
		Boolean sReport, dReport;

		if (summary == null) {
			sReport = false;
			out.println("<p>This plan Summary Reports were <b>not selected</b></p>");
		} else {
			sReport = true;
		}
		if (detail == null) {
			dReport = false;
			out.println("<p>This plan Detail Reports were <b>not selected</b></p>");
		} else {
			dReport = true;
		}
		
		if (!sumE) {
			out.println("<p>Currently Summary Reports generation disabled</p>");
		} 		
		if (!mainE) {
			out.println("<p>Currently Detail Reports generation  disabled</p>");
		}		

		//coreMaster.planDo(path, appid, planName, plnId);
		com.exilant.tfw.core.master.MasterExecute coreMaster = new MasterExecute();
		MasterPlan mp = coreMaster.readMasterPlan(path, appid, planName, plnId);
		RunResult rr = RunResults.initPlan(mp);
		rr.setDetailReport(dReport);
		rr.setSummaryReport(sReport);
		out.println("<p>Master Plan :<b>" + mp.getMasterPlanName() + "</b></p>");
		Map<String, Object> rtnLst = coreMaster.planDo(path, appid, planName, plnId, rr, out, false);
		//MasterPlan mp = coreMaster.getMasterPlan();
		
		//RunResult rr = coreMaster.getrResult();
		List<Lane> lanes = mp.getLanes();
		//for(int i = 0 ; i < lanes.size()){
		//while (agenItr.hasNext()) 
		{
			AgentDetails det = agenItr.next();
			TaskResult tr = null;
			String agentRtn = null;
			for (int laneId = 0; laneId < lanes.size(); laneId++) {
			    out.println(" <hr>");
				Lane lane = lanes.get(laneId);
				AgentDetails agntDetails = com.exilant.tfw.core.Agents.getAgent(lane.getAgentName());
				if (agntDetails == null) {
					out.println("Agent not found :" + lane.getAgentName() + ", check main.properties/ agents list.");
					out.println("Skipping lane :" + lane.getLaneName() + "," +  laneId + "");
					continue;
				}
				out.println("\n<br>Agent reply lane " + (laneId + 1) + " " + lane.getLaneName() + " <a href=\"http://" + det.getIp() + ":" + det.getPort() + "/agent/\">" + det.getAgentName() + "</a> " );
				
				agentRtn = rtnLst.get(laneId + "").toString();
				
				out.println(" :<b>" + agentRtn + "</b><br>");
				if (detail == null ) {//|| agentRtn == null || agentRtn.length() == 0
					continue; //Detailed reports are disabled 
				}
				List<Task> tasks = lane.getTasks();
				for (int clone = 0; clone <= lane.getClones(); clone++) {
					if(clone > 5 && clone < (lane.getClones() - 5)){
							continue;
					}
					for (int laneRpt = 0; laneRpt <= lane.getIterations(); laneRpt++) {
						if(laneRpt > 10 && laneRpt < (lane.getIterations() - 10)){
							continue;
						}
						for (int taskId = 0; taskId < tasks.size(); taskId++) {
							Task task = tasks.get(taskId);
							// at least do the task once + any repeats
							for (int taskRpt = 0; taskRpt <= task.getRepeats(); taskRpt++) {
								if(taskRpt > 10 && taskRpt < (task.getRepeats() - 10)){
									continue;
								}
								tr = new TaskResult();
								tr.setLaneName(lane.getLaneName());
								tr.setLaneCloneId(clone);
								tr.setLaneRepeatId(laneRpt);
								tr.setTaskRepeatId(taskRpt);
								tr.setTaskName(task.getTaskName());
								tr.setTestSuiteFilePath(task.getTestPlanXlsPath());
								tr.setLaneNo(lane.getLaneNumberInPlan());
								tr.setTaskId(task.getTaskId());

								// Generating the Detailed report file link
								File nDir = DetailedReportHelperTask.getTaskReportDir(rr.getMasterReportName(), rr.getStartDate(), tr.getLaneName()
										+ tr.getLaneNo(), tr.getLaneCloneId(), tr.getLaneRepeatId(), tr.getTaskName(), (taskId + 1),
										tr.getTaskRepeatId());
								tr.setTaskDetailReportDir(nDir);
								nDir = DetailedReportHelperTask.getTaskReportDetailFile(tr, rr);
								String st = nDir.getAbsolutePath();
								int j = st.indexOf("/reports/r");
								if (j > 0) {
									st = st.substring(j);
									String n = st.replace(".html", "");
									n = st.replace("/reports/r", "");
									n = n.replace("/", " ");
									n = n.replace("_", " ");
									st = ".." + st;
									out.println("</br>Detailed Report :<a href=\"" + st + "\"> " + tr.getLaneNo() + " " + tr.getLaneName() + "  clone "
											+ tr.getLaneCloneId() + ", repeat  " + tr.getLaneRepeatId() + ", task " + (taskId + 1) + ", repeat "
											+ tr.getTaskRepeatId() + " </a>");
											
									//out.println("</br>Detailed Report :<a href=\"r.jsp?f=" + nDir.getAbsolutePath() + "\">read [" + tr.getLaneName() + tr.getLaneNo() + "  clone "
										//	+ tr.getLaneCloneId() + ", " + "r  " + tr.getLaneRepeatId() + ", task " + (taskId + 1) + ", repeat "
											//+ tr.getTaskRepeatId() + "]" + " </a>");		
				
								}
							}
						}
					}
				}
				if (summary == null || !det.getStatus() ) {
					continue;
				}
				// For Every agent
				File sumReport = new File(rr.getReportFilePath(), "/" + det.getAgentName() + "Report.html");
				out.println("<br><h3> sumReport.getAbsolutePath() :" + sumReport.getAbsolutePath() + "." + "</h3>");
				String s = sumReport.getAbsolutePath();
				int i = s.indexOf("/reports/r");
				if (i > 0) {
					s = s.substring(i);
					String n = s.replace(".html", "");
					n = n.replace("/reports/r", "");
					n = n.replace("/", " ");
					n = n.replace("_", " ");
					s = ".." + s;
					out.println("<br>" + "Summary Report:" + "<a href=\"	" + s + "\" >" + n + "</a>");
				}
			}
		}
	%>
	<br>
	<a href=. />Back
	</a>
</body>
</html>