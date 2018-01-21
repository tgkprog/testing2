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
	page import = "org.apache.log4j.Logger,java.util.*" 
	%>
	
	<%!
	
	private static final Logger logger = Logger.getLogger("com.exilant.tfw.core.web.agt.quick_jsp");
	
	static void proc(java.io.Writer wrt, String path) {
		com.exilant.tfw.core.Agents.proc(wrt, path);
		
	}//m	
	%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agents' Status</title>
</head>
</html>

<body>
	<%@include file="/common/header.jsp"%>
	


<a href=quick.jsp?a=t>Tasks</a> | <a href=quick.jsp?a=q>Quick</a> | <a href=quick.jsp?a=tc>Clear Tasks</a> | <a href=quick.jsp?a=qc>Clear Quick</a>

<br>
<%
	
	
		String action = request.getParameter("a");
		if(action == null || action.equals("q")){
			proc(out, "/agent/a/quick.jsp");
		}else if (action.equals("qc")){
			proc(out, "/agent/a/quickclr.jsp");
		}else if (action.equals("t")){
			proc(out, "/agent/a/tasks.jsp");
		}else if (action.equals("tc")){
			proc(out, "/agent/a/tasksclr.jsp");
		}
	%>


<a href=quick.jsp?a=t>Tasks</a> | <a href=quick.jsp?a=q>Quick</a> | <a href=quick.jsp?a=tc>Clear Tasks</a> | <a href=quick.jsp?a=qc>Clear Quick</a>
<br> Quick - See status of lanes and clones, start and end, wont show clones that are yet to start due to ramp up.
<br> Task - See status of lanes and clones and current task.
<br>
Earlier we were clearing this history in agents automatically. Now you need to do it manually (this history is in memory and is lost if agent stopped).
<br>