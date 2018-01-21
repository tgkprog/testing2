<%@ 
page import="java.util.Date"
%><%@ page import="org.apache.log4j.*"%><%@ 
page import="com.exilant.tfw.util.SerUtils"%><%@ 
page import="com.exilant.tfw.util.Base64"%><%@ 
page import="java.net.*"%><%@ 
page import="com.exilant.tfw.util.http.*"%><%@ 
page import="java.util.*"%><%@ 
page import="java.io.*"%>
<%@page import="com.exilant.tfw.pojo.*" %>
<%@page import="com.exilant.tfw.pojo.input.*" %>
<%@page import="com.exilant.tfw.pojo.output.*" %>
<%@page import="java.math.BigDecimal" %>


<%!

private static Logger logger = Logger.getLogger("com.exilant.tfw.webJsps.serUtl.TestStepSend");

private static int timeout = 1000*30; //30 sec



 Object process(String typ, Object o){
	StringBuilder result = new StringBuilder().append("Ritu ");
	result.append(new java.util.Date().toString());
	Map map = (Map)o;
	Object s = map.get(1);
	System.out.println("Object s ="+s.toString());
	return result.toString();
}


%>send <%
	try{
	int timeOut = 120000;
			
	TestPlan tp = new TestPlan();
		
	TestPlanId tpId = new TestPlanId();
	String agentName = "agent1";
	String runnername = "RunnerSelenium1";
	
	double c = System.currentTimeMillis();
	tpId.setTestplanid(new BigDecimal(c));
	SecureRandom rnd = new SecureRandom();
	String runId = agentName + runnername + System.currentTimeMillis() + "-" + rnd.nextInt(1000);
	tp.setTestplanid(tpId);
	java.util.Map dataToSend = new java.util.HashMap();
	dataToSend.put("TestPlan", tp);
	dataToSend.put("when", new java.util.Date());
	dataToSend.put("1", "2m6");
	
	String urlTo = "http://localhost:8180/tfwAgent/TestAgentGet.jsp";
	HttpData hDat = new HttpData(urlTo, Base64.GZIP, null);
	Object rtn = NetSendTest.sendObj((Serializable)dataToSend, hDat, "1");
	
	//request.setAttribute("typ", rtn);
	out.println("SENT <BR>GOT :" + rtn + "<br><br>");
	
	
		
	
		} catch(Exception e){
	out.print("errr=" + e);	
		
	e.printStackTrace();
	logger.fatal(" err r " + e, e);
		}
%>
<br>DONE