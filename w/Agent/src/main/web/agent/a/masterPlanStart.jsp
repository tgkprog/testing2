<%@
    page import="com.exilant.tfw.agent.lanes.AgentCache"
    %><%
String typ = request.getParameter("typ");
System.out.println("typ ="+typ);
AgentCache aCache = AgentCache.getInstance();
aCache.clear();
%>