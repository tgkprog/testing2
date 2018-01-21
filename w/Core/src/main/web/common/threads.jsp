<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%><%@ 
page import="java.util.Date,com.exilant.tfw.agent.AgentThread, java.util.List,java.util.concurrent.ThreadPoolExecutor,com.exilant.tfw.util.threads.TfwPools"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>Tfw Thread pool info</title>
</head>
<body>

Agent (internal id : <%= com.exilant.tfw.agent.Agent2.getAgentRunnerId() %> This is important only if you have more than one agent in your core agent set, else 1 is fine)
<br>
 Because the states of tasks and threads may change dynamically during computation, the values are only an approximation.  Pool "_d" is the default, will usually have 0 tasks. 'pool_smryRpt' is made by Core. 
<br><h4>Tfw Thread pool info</h4> <% 
try {
			List<String> pools = TfwPools.getPoolNames();
			for(String pool : pools){
				out.println("<br>Pool :<b>" + pool + "</b>");
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
				
%><br>
Because the states of tasks and threads may change dynamically during computation, the values are only an approximation.

<br><a href=./threads.jsp?r=1>Reload this page</a>

 <%@include file="/common/footer.jsp" %>
 
 </body>
</html>
