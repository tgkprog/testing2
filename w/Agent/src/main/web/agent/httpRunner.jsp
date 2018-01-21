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
<title>Http Runner</title>
</head>
</html>

<body>
	<%@include file="/common/header.jsp"%>
	
	<%@ page import="com.exilant.tfw.util.LangUtils"

%><form name=f1 action=httpRunner.jsp?r=1 method=get>


Http runner library : <select name=type1><option value=1>Home Java lib</option>
<option value=0>Apache Http Components</option>
</select>

<input type=submit value=Change />
</form>
<br>

<form name=f2 action=httpRunner.jsp?r=1 method=get>



<input type=submit value='View Current' />
</form>
<%

int n = -1;
try{
	String nn = request.getParameter("type1");
	if(nn != null){
		n = LangUtils.getInt(nn, n, "Http Lib type");
	}
}catch(Exception e){
	out.println("Error parsing param :" + e);
}

try{
	if(n > -1){
		com.exilant.tfw.runner.http.HttpRunner.setHttpLib(n);
	}
	out.println("Current value :" + com.exilant.tfw.runner.http.HttpRunner.getHttpLib());
}catch(Throwable e){
	out.println("Error (http runner not installed?) :" + e);
}

%>
<br><a href=/agent>Agent home</a>
<br>Legend :
<br>0 : apache
<br>1 : home