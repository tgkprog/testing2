<%@page import="com.exilant.tfw.pojo.input.MasterPlan"%>
<%@ 
page import="java.util.Date"
%><%@ page import="org.apache.log4j.*"%><%@ 
page import="com.exilant.tfw.util.*"%><%@ 
page import="com.exilant.tfw.pojo.output.AgentDetails"%><%@ 
page import="java.util.*"%><%@ 
page import="com.exilant.tfw.core.Agents,com.exilant.tfw.util.http.*,java.io.*"%><%!
private static final Logger logger = Logger.getLogger("com.exilant.tfw.web.PlanRunFeedbackFromAgents_jsp");
%><%

		try{
			
			int options  = 0;
			System.out.println("\n\n DDD\n" );
			String typ = request.getParameter("typ");
			System.out.println("typ ="+typ);
			String objData = request.getParameter("obj");				
			
			Object obj = Base64.decodeToObject(objData, options, null);
			
			if(obj instanceof com.exilant.tfw.pojo.output.AgentRunResult){
				com.exilant.tfw.pojo.output.AgentRunResult arr = (com.exilant.tfw.pojo.output.AgentRunResult)obj;
				com.exilant.tfw.core.RunResults.result(arr);
				logger.warn("e arr :" + arr.getAgentName());
			}
			if(obj instanceof MasterPlan){
				MasterPlan mPlan = (MasterPlan)obj;
				com.exilant.tfw.core.RunResults.executePlan(mPlan);
				logger.warn("Master Plan:" + mPlan.getMasterPlanName());
			}
			
			System.out.println("coreUrl Id =" + obj);//TODO logger
			
			/* PROCESS */
			String result = "okay 1 "+ new java.util.Date();
			
			String strToSend = Base64.encodeObject((Serializable)result, options);
			
			out.print("rsp=" + strToSend);
			
			//request.setAttribute("typ", rtn);
			//out.println("SENT <BR>GOT :" + rtn + "<br><br>");
			
		} catch(Throwable e){		
			logger.warn("e " + e, e);
			out.print("rsp=ERR:" + e);
		}
%>