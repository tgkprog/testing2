<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%><%@ 
page import="java.util.Date,com.exilant.tfw.agent.AgentThread,java.lang.reflect.*"
%><%@
page import = "java.lang.management.OperatingSystemMXBean,java.lang.management.ManagementFactory"
%>
<%@include file="/common/header.jsp" %>

		
		<br>Versions:		<br>
This jsp Version 1.6.10<%
out.print("<br>Utils " + com.exilant.tfw.util.LangUtils.getVersion() + " lang utils");
out.print("<br>Common " + com.exilant.tfw.common.CoreRunner.getVersion());
out.print("<br>Data " + com.exilant.tfw.pojo.output.TestStepResult.getVersion());
out.print("<br>Agent " + com.exilant.tfw.agent.AgentThread.getVersion());
out.print("<br>Core " + com.exilant.tfw.core.RunResults.getVersion());

out.print("<br>Http " + com.exilant.tfw.runner.http.HttpRunner.getVersion());
%>
 <br>Selenium Runner (if its in class path) : <%
try{
	Class cc = Class.forName("com.exilant.tfw.runner.selenium.ActionScript");
	Method mm = cc.getDeclaredMethod("getVersion()", null);
	out.print(mm.invoke(null));
}catch(Throwable e){
	out.print("Error getting Selenium Runner version - may not in path or version which does not have getVersion function");
}


%>

				
				
<%@include file="/common/footer.jsp" %>
