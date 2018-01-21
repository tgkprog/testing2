<%@ 
page import="java.util.Date"%><%@ 
page import="org.apache.log4j.*"%><%@ 
page import="com.exilant.tfw.util.SerUtils"%><%@ 
page import="com.exilant.tfw.util.Base64"%><%@ 
page import="java.util.*"%><%@ 
page import="com.exilant.tfw.util.http.*"%><%@ 
page import="com.exilant.tfw.util.SerUtils"%><%@ 
page import="com.exilant.tfw.util.Base64"%><%@ 
page import="java.net.*"%><%@ 
page import="java.io.*"%><%@ 
page import="com.exilant.tfw.agent.*"%><%@ 
page import="com.exilant.tfw.pojo.*" %><%@
page import="com.exilant.tfw.pojo.input.*" %><%@
page  import="com.exilant.tfw.pojo.output.*" %><%!

private static Logger logger = Logger.getLogger("com.exilant.tfw.agent.web.agent.CoreUrl");
%><%
		
		try{
			
			int options  = 0;
			
			String typ = request.getParameter("typ");
			System.out.println("typ ="+typ);
			String objData = request.getParameter("obj");				
			
			Object obj = Base64.decodeToObject(objData, options, null);
			//System.out.println("obj =" + obj);
						
			
			//Object result = process(typ, obj);
			
			//System.out.println("typ :" +  typ + "; obj :" + obj);
			//System.out.println("result :" +  result + ";");
			String coreUrl = (String)obj;
			logger.info("coreUrl : " + coreUrl);
			//System.out.println(m.get("TestPlan").toString());
			
			com.exilant.tfw.agent.Agent2.setCoreUrl(coreUrl);
			String result = "okay core url set :"+ new java.util.Date() + ", " + coreUrl;
			
			String strToSend = Base64.encodeObject((Serializable)result, options);
			
			out.print("rsp=" + strToSend);
			
			//request.setAttribute("typ", rtn);
			//out.println("SENT <BR>GOT :" + rtn + "<br><br>");
			
		} catch(Exception e){		
			e.printStackTrace();
			out.print("ERR=" + e);
		}
%>