<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%><%@ 
page import="java.util.Date,com.exilant.tfw.agent.AgentThread,java.lang.reflect.*"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>Agent</title>
</head>

<body>
<%@include file="/common/header.jsp" %>
Agent (internal id : <%= com.exilant.tfw.agent.Agent2.getAgentRunnerId() %> This is important only if you have more than one agent in your core agent set, else 1 is fine)
<br>



<br><a href=./?r=1>Reload this page</a>

<br><a href=a/agentSelfInit.jsp> Agent init </a> Reloads main.properties for this JVM.

		<br>
		
		
This jsp Version 1.6.9

| <a href=/common/?r=13>Common</a> | Memory - <a href=/common/info.jsp>common info</a> | <a href=/agent/agentInfo.jsp?l> Agent info </a> | <a href=/common/threads.jsp?r=1>Thread pool info</a>
| <a href=/common/poolEnd.jsp>End a pool</a>

<br><br>
<a href=httpRunner.jsp title="Http Runner options if its installed">Http Runner options</a>
</body>
</html>
<%
session.setAttribute("homePagePath", "/agent");
session.setAttribute("homePageName", "Agent");
%>