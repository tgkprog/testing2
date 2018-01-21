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
<title>Welcome Page</title>
</head>
<body>
			<%!
			static Logger log = Logger.getLogger("welcomepage.jsp");
			%>
			<%
			log.info("calling welcomepage.jsp +++++++++ ");
			log.info(request.getContextPath());
			String userName = null;
			String password = null;
			String rePassword = null;
			String sex = null;
			String qualification = null;
			String address = null;
			String country = null;
			String areaOfInterest1 = null;
			String areaOfInterest2 = null;
			String areaOfInterest3 = null;
			String areaOfInterest4 = null;
			
			Map<String, String> messages = new LinkedHashMap<String, String>();
			String spl="1234567890`~!@#$%^&*()_+=-\";:/.,<>?";
			
			userName = request.getParameter("userName");
			System.out.println("user name"+userName);
			if (userName == null || userName.trim().isEmpty()) {
				System.out.println("username null");
			    messages.put("Username", "Please enter Name");
			} else {
				for(int i=0; i<spl.length(); i++){
					if(userName.indexOf(spl.charAt(i))>=0){
						messages.put("invalidusername", "Sorry Name cannot have special chars or numbers ");
					}
				}
			}
			
			
			password = request.getParameter("userPwd");
			System.out.println("user pwd"+password);
			if (password == null || password.trim().isEmpty()) {
			    messages.put("password", "Please enter Password");
			}
			
			rePassword = request.getParameter("cPwd");
			System.out.println("re pwd"+rePassword);
			if (rePassword == null || rePassword.trim().isEmpty()) {
			    messages.put("rePassword", "Please enter Password for confirmation");
			}
			
			if(password != null && rePassword != null && password.length()==rePassword.length()){
				for(int i=0; i<password.length(); i++){
					if(password.charAt(i)!=rePassword.charAt(i)){
						messages.put("pwdmismatch", "password mismatch");
						
					}
				}
			}else{
				messages.put("pwdlenmismatch", "password length mismatch");
			}
			
			sex = request.getParameter("sex");
			System.out.println("user sex"+sex);
			if (sex == null || sex.trim().isEmpty()) {
			    messages.put("sex", "Please enter sex");
			}
			
			qualification = request.getParameter("qual");
			System.out.println("user qual"+qualification);
			if (qualification == null || qualification.equals("----Select----") ) {
			    messages.put("qualification", "Please enter qualification");
			}
			
			address = request.getParameter("addrs");
			System.out.println("user address"+address);
			if (address == null || address.trim().isEmpty()) {
			    messages.put("address", "Please enter address");
			}
			
			country = request.getParameter("country");
			System.out.println("user country"+country);
			if (country == null || country.equals("Select Country")) {
			    messages.put("country", "Please enter country");
			}
			
			areaOfInterest1 = request.getParameter("cb1");
			areaOfInterest2 = request.getParameter("cb2");
			areaOfInterest3 = request.getParameter("cb3");
			areaOfInterest4 = request.getParameter("cb4");
			if (areaOfInterest1 == null && areaOfInterest2 == null && areaOfInterest3 == null && areaOfInterest4 == null) {
				 messages.put("areaOfInterest", "Please enter an area of interest");
			}
			System.out.println("areaOfInterest"+areaOfInterest1+areaOfInterest2+areaOfInterest3+areaOfInterest4);
			if(!messages.isEmpty()) {
				System.out.println("messages is not empty redirect to formregistration");
				response.sendRedirect("formregistration.jsp");
			}
			
			session.setAttribute("error", messages);
			
			%>
<form name="welcomeForm" action="datepickermouseovertabout.jsp" method="post"> 

 <H2><U>Your Registration Details</U></H2>
 <table border="3" cellspacing="3" bordercolor="orange"
	bgcolor="lightblue" align="center">
	<tr>
		<td>Name</td>
		<td><%=request.getParameter("userName")%>
		</td>
	</tr>
	<tr>
		<td>Password</td>
		<td><%=request.getParameter("userPwd")%></td>
	</tr>
	<tr>
		<td>Re-Entered password</td>
		<td><%=request.getParameter("cPwd")%></td>
	</tr>
	<tr>
		<td>Sex</td>
		<td><%=request.getParameter("sex")%></td>
	</tr>
	
	
	<tr>
		<td>Qualification</td>
		<td><%=request.getParameter("qual")%></td>
	</tr>
	<tr>
		<td>Address</td>
		<td><%=request.getParameter("addrs")%></td>
	</tr>
	<tr>
		<td>Country</td>
		<td><%=request.getParameter("country")%></td>
	</tr>
	<tr>
		<td>Area of Interest</td>
		<td><% if(request.getParameter("cb1") != null) {
		out.println(request.getParameter("cb1"));
	} if(request.getParameter("cb2") != null) {
		out.println(request.getParameter("cb2"));
	} if(request.getParameter("cb3") != null) {
		out.println(request.getParameter("cb3"));
	} if(request.getParameter("cb4") != null) {
		out.println(request.getParameter("cb4"));
	}%></td>
	</tr>

</table>

<br><br>

<input type="submit" value="DatePickerTabout"> 

 </form>
 

</body>
</html>
