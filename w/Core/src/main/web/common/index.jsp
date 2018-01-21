<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%><%@ 
page import="java.util.Date,com.exilant.tfw.agent.AgentThread, java.util.List,java.util.concurrent.ThreadPoolExecutor,com.exilant.tfw.util.threads.TfwPools"
%><%@
page import ="com.exilant.tfw.bean.UtlConf,com.exilant.tfw.util.LangUtils,java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.TimeUnit"
%><%!
private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("com.exilant.tfwWeb.common.poolEnd_jsp");

%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>Tfw Thread pool info</title>
</head>
<body>

 <%@include file="/common/header.jsp" %>

<br>

<br><h4>Tfw Common</h4> 

 Versions of Tfw components : <a href=/common/versions.jsp>Tfw Version</a>  <br>
 
 Pool end tool <a href=/common/poolEnd.jsp?r=13>End pools</a> , use ful for VM running as agent or other killable job pools<br>
 
All pools read only  <a href=/common/threads.jsp?r=13>Pools info</a><br>

Jvm version, memory and other information <a href=/common/info.jsp>JVM Info</a>  <br>

View main.properties <a href=/common/mainprops.jsp>view.properties</a>  <br>

Reload main.properties <a href=/common/mainpropsre.jsp>reload.properties</a>  <br>

 <%@include file="/common/footer.jsp" %>
</body>
</html>
