<%@page import="com.exilant.tfw.pojo.def.PlanMock"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	page import="java.io.*"%><%@ 
	page import="java.util.*,com.exilant.tfw.pojo.output.*" %><%@ 
page import="com.exilant.tfw.core.Agents,com.exilant.tfw.util.http.*,java.io.*,com.exilant.tfw.Agent.*"%>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Starting Plan</title>
</head>
</html>

<body>
<%@include file="/common/header.jsp" %>
<%
	String plan1 = request.getParameter("plan");
out.println("<h1>Starting " + plan1 + "</h1>");
int options = 0;
HttpData hDat = new HttpData(null, options, null);


Map<String, AgentDetails > agentMap =  Agents.getAgents();
Collection <AgentDetails> aagentSet = agentMap.values();
Iterator<AgentDetails> agenItr =  aagentSet.iterator();
String coreDomain = "localhost:8080";
//String coreDomain = "your local system id with port";
//coreDomain = "10.1.0.167:8180";
String coreUrl =  "http://" +  coreDomain + "/coreMaster/PlanRunFeedbackFromAgents.jsp";//url that agent should send results to.


PlanMock pm = new PlanMock();
pm.makeMockTestCase1();
TestPlan plan = pm.createPlan("PlanA");
RunResult runR = com.exilant.tfw.core.RunResults.executePlan(plan);
String runName = "" + runR.getRunName();
String xlsPath = request.getParameter("plan");

Map<String, String> parameterMap = new HashMap<String,String>();
parameterMap.put("runName",runName);
parameterMap.put("xlsPath",xlsPath);

while(agenItr.hasNext()){
	AgentDetails a = agenItr.next();
	out.println("<br><br><h1>" + a.strA() + "</h1>");
	
	//send message to agent that we want to be its Core, so it will send all messages back to us
//	String xlsPath =  request.getParameter("plan");
	request.setAttribute("xlsPath", xlsPath);
	//Agent jsp that takes above url and calls static com.exilant.tfw.Agent.Agent2.setCoreUrl(String url)
	String urlAgentTo = "http://" + a.getIp() + ":" + a.getPort() + "/agent/planStart.jsp";
	hDat.setUrl(urlAgentTo);
	out.println(" \n<br>Calling agent:" + urlAgentTo);
	Object rtn = NetSendTest.sendObj((Serializable)parameterMap, hDat, "a");
	out.println("<br>Agent reply:<b>" + rtn + "</b>");
	File root = runR.getReportFilePath();
	File f = new File(root, a.getAgentName() + "Report.html");
	String s = f.getAbsolutePath();
	int i = s.indexOf("/reports/r");
	if(i > 0){
		s = s.substring(i);
		String n = s.replace(".html", "");
		 n = s.replace("/reports/r", "");
		 n = n.replace("/", " ");
		 n = n.replace("_", " ");
		 s = ".." + s;
		out.println("<a href=\"	" + s + "\" >" + n + "</a>");
	}
	///data/tfw/jbossAgent/server/default/deploy/ROOT.war/agent/planStart.jsp
}
%>
<br><a href=./>Back</a>