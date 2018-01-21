<%@ 
page import="java.util.Date"
%><%@ page import="org.apache.log4j.*"%><%@ 
page import="com.exilant.tfw.util.*"%><%@ 
page import="com.exilant.tfw.pojo.output.AgentDetails"%><%@ 
page import="java.util.*"%><%@ 
page import="com.exilant.tfw.pojo.def.*"%>
<%@include file="header.jsp"%><%!

/* TODO move this to AgentDetail */
String proc(com.exilant.tfw.pojo.def.TestResultTmp r){
 StringBuilder sb = new StringBuilder().append("[");
 
 switch r.getStage(){
 	case STAGE.STEP:
 	
 	break;
 	case STAGE.PLAN:
 	
 	break;
 	
 	default:
 	
 	
 
   }
 
 return sb.toString();
}



%><%
	
		try{
			
			String typ = request.getParameter("typ");
			int options = 0;//Base64.GZIP;
							
			/*byte dataBytes[] = Base64.decode(objData, Base64.GZIP);
			Object obj = SerUtils.retriveObject(dataBytes);//cast`
			*/
			//Object decodeToObject(  String encodedObject, int options, final ClassLoader loader )
			String objData = request.getParameter("obj");	
			Object obj = Base64.decodeToObject(objData, options, null);
						
			
			TestResultTmp r = (TestResultTmp)obj;
			String msg = "Core recd status " + proc(r);
			Serializable result = (Serializable)msg;
			
			System.out.println("typ :" +  typ + "; obj :" + obj);
			System.out.println("result :" +  result + ";");
			//byte replyData[] = SerUtils.getObjectSerialized(result);
			String reply = null;
			//reply = new String(Base64.encodeBytes(replyData, Base64.GZIP));
			reply = Base64.encodeObject(result, options);
			out.print("rsp=" + reply);
		} catch(Exception e){		
			e.printStackTrace();
			out.print("ERR=" + e);
		}

%>