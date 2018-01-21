<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
     <%@ page import="java.util.Map" %> 
      <%@ page import="java.util.HashMap" %> 
       <%@ page import="java.util.LinkedHashMap" %> 
     <%@ page import="org.apache.log4j.Logger" %> 
  <%@ page import="org.apache.log4j.PropertyConfigurator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<script>
$(function() {
$( "#datepicker" ).datepicker();
});
</script>



<script language="javascript" src="js/RegValidationForm.js"></script>
<title>Registration Form</title>
</head>
<body>
			<%!
			static Logger log = Logger.getLogger("formregistration.jsp");
			%>
			<%
			PropertyConfigurator.configure("/Users/Manoj/Desktop/apache-tomcat-7.0.37/conf/log4j.properties");
			log.info("calling formregistration.jsp $$$$$$$$$$$$ ");
			log.info(request.getContextPath());
			System.out.println("request getAttribute of messages"+request.getAttribute("messages"));
			if(request.getAttribute("messages") != null) {
				System.out.println(request.getAttribute("messages"));
			}
			Map<String,String> errorMap= new HashMap<String,String>();
			String errorMsg = null;
			if(session.getAttribute("error") != null) {
				%> <table>
				<%
			System.out.println("session getAttribute of error"+session.getAttribute("error"));
			errorMap = (HashMap<String,String>) session.getAttribute("error");
			session.removeAttribute("error");
			Iterator<String> iterator = errorMap.values().iterator();
			while(iterator.hasNext()) {
				errorMsg = (String)iterator.next();
				%>
				 <tr><%=errorMsg%></tr><br>
				<%
				System.out.println("error msg"+errorMsg);
				//out.println(errorMsg);
			}%> </table> <%
			}
				
			%>
<h2 align="center">Registration Form</h2>
<hr>
<!-- onSubmit="return fnValidateForm()" -->
<form name="registerForm" onSubmit="return fnValidateForm()" action="welcomepage.jsp" method=post>

<table border="3" cellspacing="3" bordercolor="orange"
	bgcolor="lightyellow" align="center">
	<tr>
		<td>Enter your Name</td>
		<td><input type="text" name="userName" /></td>
	</tr>
	
	<tr>
		<td>Enter Password</td>
		<td><input type="password" name="userPwd" /></td>
	</tr>
	<tr>
		<td>Re-Enter password</td>
		<td><input type="password" name="cPwd" /></td>
	</tr>
	<tr>
		<td>Select Sex</td>
		<td><input type="radio" name="sex" value="m" >Male <input
			type="radio" name="sex" value="f">Female</td>
	</tr>
	<tr>
	<td>Scroll Bar</td>
	<td>
	<div STYLE=" height: 90px; width: 100px; font-size: 12px; overflow: auto;">
	<table bgcolor="green">
	<tr><td bgcolor="#dadada">www.hioxindia.com</td></tr>
	<tr><td >maths.hscripts.com</td></tr>
	<tr><td bgcolor="#dadada">www.hscripts.com</td></tr>
	<tr><td>free php scripts</td></tr>
	<tr><td bgcolor="#dadada">www.hiox4u.com</td></tr>
	</table>
	</div>
	<td>
	</tr>
	<tr>
	<td>
	Login Image
	</td>
	<td>
	<img src="../resources/login.png"/>
	</td>
	</tr>
	<tr>
		<td>Select your Qualification</td>
		<td><select name="qual">
			<option>----Select----</option>
			<option value="bca">BCA</option>
			<option value="be">BE</option>
			<option value="mca">MCA</option>
			<option value="mtech">M.Tech</option>
			<option value="btech">B.Tech</option>
		</select></td>
	</tr>
	<tr>
		<td>Enter your Address</td>
		<td><textarea name="addrs" rows="5" cols="40"></textarea></td>
	</tr>
	<tr>
		<td>Select your living Country</td>
		<td><select name="country">
			<option>Select Country</option>
			<option value="india">INDIA</option>
			<option value="jp">JAPAN</option>
			<option value="nz">NEWZEALAND</option>
			<option value="malasia">MALASIA</option>
			<option value="pak">PAKISTAN</option>
		</select></td>
	</tr>
	<tr> 
		<td>Select your Area of Interest</td>
		<td><input type="checkbox" name="cb1" value="sports">
		Sports <input type="checkbox" name="cb2" value="politics">
		Indian Politics <input type="checkbox" name="cb3" value="movies">
		Movies <input type="checkbox" name="cb4" value="gossip">
		Gossip</td>
	</tr>

	<tr>
		<td colspan="2">Have you read Terms and Conditions <input
			type="radio" name="terms" value="a" onclick="fnEnableReg()">Accept
		<input type="radio" name="terms" value="d" onclick="fnDisableReg()">Decline

		</td>
	</tr>
	<tr>
		<td align="center" colspan="2"><input type="reset"> | <input
			type="submit" value="Register" name="regBtn" disabled></td>
	</tr>


  



</table>

<img src="../resources/login.png"  width="1000" height="1000"/> 
</form>
</body>

</html>