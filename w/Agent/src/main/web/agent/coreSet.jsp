<%@ 
page import="java.util.Date"
%><%@ page import="org.apache.log4j.*"%><%@ 
page import="com.exilant.tfw.util.*"%><%@ 
page import="com.exilant.tfw.pojo.output.AgentDetails"%><%@ 
page import="java.util.*"%><%@ 
page import="com.exilant.tfw.core.Agents,com.exilant.tfw.util.http.*,java.io.*"%><%

		try{
			
			int options  = 0;
			System.out.println("\n\n Coreset\n" );
			String typ = request.getParameter("typ");
			System.out.println("typ ="+typ);
			String objData = request.getParameter("obj1");				
			
			Object obj = Base64.decodeToObject(objData, options, null);
			
			String coreUrl = (String)obj;
			
			System.out.println("coreUrl Id =" + coreUrl);//TODO logger
			com.exilant.tfw.agent.Agent2.setCoreUrl(coreUrl);
			/* PROCESS */
			String result = "okay "+ new java.util.Date();
			
			String strToSend = Base64.encodeObject((Serializable)result, options);
			
			out.print("rsp=" + strToSend);
			System.out.println(" Coreset\n[" + coreUrl + "]\n" );
		
			
		} catch(Exception e){		
			e.printStackTrace();
			out.print("rsp=ERR:" + e);
		}
%>
