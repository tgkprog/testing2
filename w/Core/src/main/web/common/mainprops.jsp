 <%@include file="/common/header.jsp" %>
 <br><%@ page import="java.util.*" %>

Main props at <%
out.print( new java.util.Date() +  "<br>" );

Properties props = com.exilant.tfw.bean.UtlConf.getProperties();
Enumeration ene = props.propertyNames();
while(ene.hasMoreElements()){
	String ele = (String)ene.nextElement();
	out.println("<br>" + ele + " :" + props.getProperty(ele) + "|");
} 
%>