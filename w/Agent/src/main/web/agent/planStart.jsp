<%@ 
page import="java.util.Date"
%><%@ page import="org.apache.log4j.*"%><%@ 
page import="com.exilant.tfw.util.*"%><%@ 
page import="com.exilant.tfw.pojo.output.AgentDetails"%><%@ 
page import="java.util.*,com.exilant.tfw.agent.*"%><%@ 
page import="com.exilant.tfw.core.Agents,com.exilant.tfw.util.http.*,java.io.*,com.exilant.tfw.pojo.input.Lane,com.exilant.tfw.pojo.output.RunResult"%><%

		try{
			
			int options  = 0;
			System.out.println("\n\n DDD\n" );
			String typ = request.getParameter("typ");
			System.out.println("typ ="+typ);
			
			String objData = request.getParameter("obj1");				
			Object obj = Base64.decodeToObject(objData, options, null);
			Lane lane = (Lane)obj;
			
			objData = request.getParameter("obj2");				
			obj = Base64.decodeToObject(objData, options, null);
			RunResult rResult = (RunResult)obj;

			/*AgentThread ath = new AgentThread(planRunName.get("runName"), null,planRunName.get("xlsPath")); //TODO real id 
			AgentThread.start(ath);
			System.out.println("planRunName >>>> :" + planRunName.toString());//TODO logger*/
			com.exilant.tfw.agent.lanes.LaneProcess lp =  new com.exilant.tfw.agent.lanes.LaneProcess();
			lp.process(lane, true,null, rResult);
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = LangUtils.getSdfForFormat("y M d HH:mm");
			String time = sdf.format(dt);
			/* PROCESS */
			String result = "Plan received and " + com.exilant.tfw.common.PConsts.getAgentAcceptStr() + " with run name :" + rResult.getRunName() + " at "+ time;
			
			String strToSend = Base64.encodeObject((Serializable)result, options);
			
			out.print("rsp=" + strToSend + "");
			
			//request.setAttribute("typ", rtn);
			//out.println("SENT <BR>GOT :" + rtn + "<br><br>");
			
		} catch(Exception e){		
			e.printStackTrace();
			out.print("rsp=ERR:" + e);
		}
%>