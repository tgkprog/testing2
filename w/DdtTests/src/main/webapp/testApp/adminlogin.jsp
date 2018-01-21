<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ 
page import="org.apache.log4j.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login Screen</title>
</head>
<body>
	<%!static Logger log = Logger.getLogger("/TfwTests/src/main/webapp/testApp/adminlogin.jsp");%>
	<%
		PropertyConfigurator.configure("/Applications/apache-tomcat-7.0.37/conf/log4j.properties");
		log.info("calling adminlogin.jsp ");
		String username = null, password = null;

		username = request.getParameter("un");
		password = request.getParameter("pw");

		if (username != null) {
			if (!username.equals("Rashmi")) {
				out.println("Invalid Credentials.Try Again<br>");
				session.removeAttribute("login");
				username = null;
			} else {
				session.setAttribute("login", username);

			}
		}

		if (username == null) {
	%>

	<form name="LoginForm" action="data/index.html" method="post">
		<p>
			<b>Please enter your username <input type="text" name="un" />
			</b> <br> <b>Please enter your password <input type="password"
				name="pw" />
			</b> <br> <input type="submit" value="submit">
		</p>

	</form>

	<%
		} else {
	%>
	<jsp:forward page="data/index.html"></jsp:forward>
	<%
		}
	%>

</body>
</html>