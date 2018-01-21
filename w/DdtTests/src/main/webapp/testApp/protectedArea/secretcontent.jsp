<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@page import="java.util.*" %>
    <%@ page import="org.apache.log4j.Logger" %> 
  <%@ page import="org.apache.log4j.PropertyConfigurator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body>
<%!
			static Logger log = Logger.getLogger("secretcontent.jsp");
			%>	

<% 
PropertyConfigurator.configure("/Users/rashmi/Documents/apache-tomcat-7.0.37/conf/log4j.properties");
log.info("calling secretcontent.jsp%%%%%%%%%%%%%%%%%%%%% ");
String username =  (String) session.getAttribute("login");
String path = request.getContextPath()+"/adminlogin.jsp";
if(username == null){
  response.sendRedirect(path);
}

%>				
	<p>secret content:<%=username %></p>
	<a href=<%=request.getContextPath()%>/forms/formregistration.jsp>Form Registration</a>
	<%session.removeAttribute("login"); %>
	</body>
</html>