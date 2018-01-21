<%@page import="com.exilant.tfw.agent.Agent2"%>
<%@page import="com.exilant.tfw.excelreader.ReadReplacementStrings"%>
<a href=../?d=a />Agent home</a> | <a href=../common?d=a />Common</a> 
<br>
<%
com.exilant.tfw.bean.UtlConf.initialize();
out.println("Utl Conf re init (main.properties re read)");
%>
<br>
<%
	System.setProperty("mockClass", "com.exilant.tfw.agent.PlanMock");

	//System.setProperty("runnerClassName", "com.exilant.tfw.api.ApiRunner");
	System.setProperty("TEST_CASE_XLS", "/data/tfw/config/TestApp.xls");
	System.setProperty("TFW_MAIN_PROPERTIES_FILE_PATH", "/data/tfw/config/main.properties");

	//java.lang.reflect.Method m = com.exilant.tfw.bean.UtlConf.class.getDeclaredMethod("initialize",null);
	//m.setAccessible(true);
	//m.invoke(null,null);
	com.exilant.tfw.agent.Agent2.setMockRunner(false);

	ReadReplacementStrings.setXlsPath("/data/tfw/config/replacementString.xls");
	Agent2.refreshCipher();
	out.print("ReadReplacementStrings path : /data/tfw/config/replacementString.xls");

	//com.exilant.tfw.agent.Agent2 a = new com.exilant.tfw.agent.Agent2();
%>


com.exilant.tfw.agent.Agent2

<br>
Agent 2 Mock Runner
<%=com.exilant.tfw.agent.Agent2.isMockRunner()%>
<br><br>
Agent core url <%=com.exilant.tfw.agent.Agent2.getCoreUrl().getUrl()%> (Future will change to run level so that different cores can send to same agent)


<br> <a href=../?d=c />Agent home</a>