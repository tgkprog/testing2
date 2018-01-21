<%@ 
page import="java.util.Date"
%><%@ page import="org.apache.log4j.*"%><%@ 
page import="com.exilant.tfw.util.*"%><%@ 
page import="com.exilant.tfw.pojo.output.AgentDetails"%><%@ 
page import="java.util.*"%><%@ 
page import="com.exilant.tfw.core.Agents,com.exilant.tfw.util.http.*,java.io.*"%><%!

/* TODO move this to AgentDetail */
String strA(com.exilant.tfw.pojo.output.AgentDetails a){
 StringBuilder sb = new StringBuilder().append("[").append(a.getAgentName() ).append(", ip :")
 .append(a.getIp()).append(", port :").append(a.getPort()).append("]");
 
 return sb.toString();
}

void agentSetup(){
	//in real get from db
	
	com.exilant.tfw.core.Agents.getAgents().clear();

AgentDetails a1 = new AgentDetails();
a1.setAgentName("brahma");
a1.setIp("localhost");//From system information/netowrk/ Ethernet - advanced/ WINS 
//a1.setAgentName("your username");
//a1.setIp("your local system id");
a1.setPort(8180);

com.exilant.tfw.core.Agents.putAgent(a1.getAgentName(), a1);
/*
AgentDetails a2 = new AgentDetails();
a2.setAgentname("EXIMR-IM-305");//eximr-mb-071.local
a2.setIp("10.1.2.11");//From system information/netowrk/ Ethernet - advanced/ WINS , 10.1.1.140 mm,
a2.setPort(8180);

com.exilant.tfw.core.Agents.putAgent(a2.getAgentname(), a2);*/
}	

%>
 <%@include file="/common/header.jsp" %>
<%
	String p1 = "/data/tfw/res/velocity/resources";
com.exilant.tfw.test.ReportHelper.setVelocityResPath(p1);
out.print("Set velocity path to " + p1 );
agentSetup();
Map<String, AgentDetails > agentMap =  Agents.getAgents();
Collection <AgentDetails> aagentSet = agentMap.values();
Iterator<AgentDetails> agenItr =  aagentSet.iterator();
String coreDomain = "localhost:8080";//eximr-mb-071.local:8080
 //coreDomain = "eximr-im-305.local:8080";
//coreDomain = "your local system id with port";
String coreUrl =  "http://" + coreDomain + "/coreXls/PlanRunFeedbackFromAgents.jsp";//url that agent should send results to.
int options = 0;
HttpData hDat = new HttpData(null, options, null);
hDat.setTimeoutMilli(5000);
out.println("Agents re init with core Url : " + coreUrl + "<br>");
while(agenItr.hasNext()){
	AgentDetails a = agenItr.next();
	out.println("<h3>" + a.strA() + "</h3>");
	
	//send message to agent that we want to be its Core, so it will send all messages back to us

	//Agent jsp that takes above url and calls static com.exilant.tfw.Agent.Agent2.setCoreUrl(String url)
	String urlAgentTo = "http://" + a.getIp() + ":" + a.getPort() + "/agentXls/coreSet.jsp";
	hDat.setUrl(urlAgentTo);
	out.println(" \n<b>urlAgentTo </b>:" + urlAgentTo);
	Object rtn = NetSendTest.sendObj((Serializable)coreUrl, hDat, "a");
	out.println("<br>Core Url set :" + rtn);
}
%>
<br><a href=./>Back</a>