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

 <%@include file="/common/header.jsp" %> | <a href=./poolEnd.jsp?r=1>Reload this page</a>
Agent (internal id : <%= com.exilant.tfw.agent.Agent2.getAgentRunnerId() %> This is important only if you have more than one agent in your core agent set, else 1 is fine)
<br>

<br><h4>Tfw Thread pool View and End </h4> 
<form action =poolEnd.jsp?nd=1 method=post name=f1>
<% 

List<String> pools = TfwPools.getPoolNames();
try {
			
			for(String pool : pools){
				out.println("<b>" + pool + "</b>" );
				if(pool.startsWith("agentLanes") || pool.startsWith("agent_") || pool.startsWith("core_") || pool.startsWith("other_")){
					out.println("<input type=checkbox name=\"pool-" + pool + "\" />" );
				}
				ThreadPoolExecutor exe = TfwPools.getPool(pool);
				int activeCnt = exe.getActiveCount();
				long taskCnt = exe.getCompletedTaskCount();
				int maxPoolSize = exe.getMaximumPoolSize();
				int largestPoolSize = exe.getLargestPoolSize();
				StringBuilder sb = new StringBuilder();
				sb.append("\n<br>Active Cnt :").append(activeCnt);
				sb.append("\n<br>Completed task Cnt :").append(taskCnt);
				sb.append("\n<br>max Pool Size :").append(maxPoolSize);
				sb.append("\n<br>Reached largestPoolSize :").append(largestPoolSize);
				sb.append("\n<br>Current pool size :").append(exe.getPoolSize());
				out.print( sb.toString());
				out.println("<br>---<br>");
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			out.print( e.toString());
		}
		
		
				
%>
<input type=hidden name=end1 value=1>

<input type=submit name=bsubmit value="End checked pool(s)">
</form>
<br>
Because the states of tasks and threads may change dynamically during computation, the values are only an approximation.

<br><a href=./poolEnd.jsp?r=1>Reload this page</a>

<%
String sEnd = request.getParameter("end1");
if("1".equals(sEnd)){
%><br>
<b>Ending Pools Result:</b>
<br>
<%
	StringBuilder msg = new StringBuilder();
for(String poolName : pools){
	String nm = "pool-" + poolName;
	String s = request.getParameter(nm);
	//out.println(nm + ":" + s + "<br>");
	boolean hardStop = true;

	if("on".equals(s)){
		out.println("<br>Stopping:" + poolName + "<br>");
		try {

			// TODO set flag to shut down in agent2
			ThreadPoolExecutor tpe = TfwPools.getPool(poolName);
			if(tpe == null){
				msg.append(" Not found " + poolName + "<br>");
				continue;
			}
			tpe.shutdown();
			if (hardStop) {
				int tim = 2000;
				String sTim = UtlConf.getProperty("POOL_SHUTDOWN_TIMEOUT", tim + "");
				tim = LangUtils.getInt(sTim, tim, "timeout for shutdown of master pool : " + poolName);
				// TODO put this in a task with time out
				if (!tpe.awaitTermination(tim, TimeUnit.MILLISECONDS)) {
					msg.append("Shutdown now<br>");
					tpe.shutdownNow();
				}
				List<String> rt = com.exilant.tfw.util.threads.ThrdsStop.stopThatMatch(poolName, tpe);
				for (String ss : rt) {
					msg.append(ss + "<br>");
				}
			}
		} catch (Throwable e) {
			logger.warn("shutdown pool : " + poolName + ", " + e, e);
		}
		String s1 = com.exilant.tfw.util.threads.TfwPools.remove(poolName);
		msg.append(s1 + "<br>");

	}
}
out.print(msg.toString());
}
%>

 <%@include file="/common/footer.jsp" %>
</body>
</html>
