<%@ 
page import="java.util.Date"
%><%@ page import="org.apache.log4j.*"%><%@ 
page import="com.exilant.tfw.util.*"%><%@ 
page import="com.exilant.tfw.pojo.output.AgentDetails"%><%@ 
page import="java.util.*"%><%@ 
page import="com.exilant.tfw.core.Agents,com.exilant.tfw.util.http.*,java.io.*"%><%!
private static final Logger logger = Logger.getLogger("com.exilant.tfw.web.planEndFromAgent_jsp");
%><%

		try {
			logger.info("inside planEnd");
			/*File root = rr.getReportFilePath();
			File mainI = new File(root, "index.html");
			String runName = rr.getRunName();
			List<AgentRunResult> agentList = new ArrayList<AgentRunResult>();

			agentList.addAll(rr.getAgentList());
			// agentList.add(runRslt.getAgentRunResult("RunResult2"));
			PrintStream ps = new PrintStream (new FileOutputStream(mainI));
			ps.println("<h3>Exilant : TFW Report </h3>");
			for (int i = 0; i < agentList.size(); i++) {
				
				AgentRunResult agentRunResult = (AgentRunResult) agentList.get(i);
				File rpt = agentRunResult.getReportFilePath();
				String s = agentRunResult.getAgentname() + " " + agentRunResult.getTestPlan().getTestPlanRunName();
				s = s.replace("/", " " );
				s = s.replace("_", " " );
				s = s.replace(".", " " );
				//ps.println((i + 1) + " <a href=\"" + rpt.getName() + "\" >" + s + "</a><br>");
				
			}
			ps.println("<br>End of index");
			ps.close();*/
			String result = "okay "+ new java.util.Date();
			int options = 0;
			String strToSend = Base64.encodeObject((Serializable)result, options);
			
			out.print("rsp=" + strToSend);		} catch (Exception e) {
			logger.log(Level.WARN, "final rpt err :" + e, e);
		}

%>