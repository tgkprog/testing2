<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
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
			static Logger log = Logger.getLogger("testImage.jsp");
			%>
			<%
			PropertyConfigurator.configure("/Users/Manoj/Desktop/apache-tomcat-7.0.37/conf/log4j.properties");
			log.info("calling testImage.jsp***************");
			log.info(request.getContextPath());
			%>
<form name="testimage" action="selectfiletoupload.jsp" method="post">

<img src="../resources/nature.png" width="800" height="800"/>
<br><br>

<input type="submit" value="Upload a File"> 
</form>

</body>
</html>
