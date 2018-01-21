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
			static Logger log = Logger.getLogger("selectfiletoupload.jsp");
			%>
			<%
			PropertyConfigurator.configure("/Users/Manoj/Desktop/apache-tomcat-7.0.37/conf/log4j.properties");
			log.info("calling selectfiletoupload.jsp +++++++++ ");
			log.info(request.getContextPath());
			%>
<FORM ENCTYPE="multipart/form-data" ACTION="fileupload.jsp" METHOD=POST>
<br>
<br>
<br>
<center>
<table border="0" bgcolor=#ccFDDEE>
      <tr>
            <center>
            <td colspan="2" align="center"><B>UPLOAD THE FILE</B>
            <center>
            </td>
      </tr>
      <tr>
            <td colspan="2" align="center"></td>
      </tr>
      <tr>
            <td><b>Choose the file To Upload:</b></td>
            <td><INPUT NAME="file" TYPE="file"></td>
      </tr>
      <tr>
            <td colspan="2" align="center"></td>
      </tr>
      <tr>
            <td colspan="2" align="center"><input type="submit"
                  value="Send File"></td>
      </tr>
      <table>
            </center>
            </FORM>
</body>
</html>