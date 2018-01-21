<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GRX : Execute a plan</title>
</head>
<body>
 <%@include file="/common/header.jsp" %>
<form action="executePlan.jsp" method="post">
Enter the File Path : <input type="text" id="xlsPath" name="plan" value=""><br>
 <input type="submit" name ="Submit"/>
</form>
</body>
</html>