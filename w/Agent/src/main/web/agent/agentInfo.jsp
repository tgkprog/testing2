
 <%@include file="/common/header.jsp" %>
 
 <a href=/agent?r=r13>Agent</a> ,
<a href=agentInfo.jsp?r=r13>Reload</a> ,

Agent info at <% 

out.print(new java.util.Date()); %>

<br>Agent Mock Runner <%= com.exilant.tfw.agent.Agent2.isMockRunner() + "." %> | Runners :
<br>
<%  com.exilant.tfw.agent.Agent2 a2 = new com.exilant.tfw.agent.Agent2();
		out.println(a2.runnersInfo()); %>.
		<br>Lane status:<br>
<% 
out.print(com.exilant.tfw.agent.lanes.LaneTask.getHistory()); %>
<br>-------
<br><a href=agentInfo.jsp?r=r13>Reload</a>

<br>
Agent (internal id : <%= com.exilant.tfw.agent.Agent2.getAgentRunnerId() %> This is important only if you have more than one agent in your core agent set and want to differenciate, else 1 is fine)
<br>


