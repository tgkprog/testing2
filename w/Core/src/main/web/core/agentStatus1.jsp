<%@page import="com.exilant.tfw.bean.UtlConf"%>
<%@ page import="java.util.Date"%><%@ 
page import="org.apache.log4j.*"%><%@ 
page import="com.exilant.tfw.util.*"%><%@ 
page import="com.exilant.tfw.pojo.output.AgentDetails"%><%@ 
page import="java.util.*"%><%@ 
page import="com.exilant.tfw.core.Agents,com.exilant.tfw.util.http.*,java.io.*"%><%@
include file="/common/header.jsp"%><%
	String p1 = "/data/tfw/res/velocity/resources";
	com.exilant.tfw.test.ReportHelper.setVelocityResPath(p1);
	out.print("Set velocity path to " + p1);
	Agents.init();
	Map<String, AgentDetails> agentsList = Agents.getAgents();
	
	com.exilant.tfw.bean.UtlConf.initialize();
	out.println("<br>Called UtlConf.initialize()<br>");
	com.exilant.tfw.core.Agents.init();
		out.println("Called Agents.init()<br>");

	if (agentsList.size() == 0) {
		out.println("<br><b> No Agents are Registered </b><br>");
	} else {
		out.println("<br><b> List of active Agents Registered </b><br>\n<br>");
		Iterator<AgentDetails> agenItr = agentsList.values().iterator();

		String coreDomain = UtlConf.getProperty("coreip", "localhost")+":"+UtlConf.getProperty("coreport", "8080");
		String coreUrl = "http://" + coreDomain + "/core/PlanRunFeedbackFromAgents.jsp";
		
		int options = 0;
		HttpData hDat = new HttpData(null, options, null);
		hDat.setTimeoutMilli(5000);
		out.println("Agents re init with core Url : " + coreUrl + "<br>");
		
		while (agenItr.hasNext()) {
			AgentDetails a = agenItr.next();
			
			out.println("<h3>" + a.strA() + "</h3>");

			if (a.getStatus()) {
			
				String urlAgentTo = "http://" + a.getIp() + ":" + a.getPort() + "/agent/coreSet.jsp";
				hDat.setUrl(urlAgentTo);
				out.println(" \n<b>urlAgentTo </b>:" + urlAgentTo);
				Object rtn = NetSend.sendObjects("a",hDat,(Serializable) coreUrl);
				out.println("<br>Core Url set :" + rtn);
				urlAgentTo = "http://" + a.getIp() + ":" + a.getPort() + "/agent/";
				out.println(" <a href=\"" + urlAgentTo + "\">Active</a> ");
			}else{
				out.println(" In-Active ");
			}
			
			out.println(" <hr><br> ");
		}
	}
%>
<br>
<a href=. />
Back
</a>
