<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="org.apache.log4j.Logger" %> 
  <%@ page import="org.apache.log4j.PropertyConfigurator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>DatePickerTabout</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<script>
$(function() {
$( "#datepicker" ).datepicker();
});
</script>

 <style>
.idle { color : gray} 
.invalid { color :red} 
.working { color :orange} 
.okay { color :green} 
</style>

<script language="javascript">
function startAni(){
	

		document.getElementById("s1").className = "working"
	
	setTimeout("nextStep()", 1000);
}


function nextStep(){
	s = parseInt(document.datetabForm.t1.value);
	
	if((s > 1 && s < 10) || s == "1"){
		cl = "okay";
	}else{
		cl = "invalid";
	}
	document.getElementById("s1").className = cl
	
	}

function bigImg(x)
{
x.style.height="64px";
x.style.width="64px";
}

function normalImg(x)
{
x.style.height="32px";
x.style.width="32px";
}

function toggle(button)
{
  if(document.getElementById("1").value=="OFF"){
   document.getElementById("1").value="ON";}

  else if(document.getElementById("1").value=="ON"){
   document.getElementById("1").value="OFF";}
}

</script>
</head>
<body>
<%!
			static Logger log = Logger.getLogger("datepickermouseovertabout.jsp");
			%>
			<%
			PropertyConfigurator.configure("/Users/Manoj/Desktop/apache-tomcat-7.0.37/conf/log4j.properties");
			log.info("calling datepickermouseovertabout.jsp");
			%>
<form name="datetabForm" action="toggleanddragdropandslider.jsp" method="post"> 

<H3><U>Test Tabout with below textboxes</U></H3>
<h4>The integer value 1 to 9 are valid</h4>
<a class="pickNumber">
<span class="idle" id=s1> [ -1 -]</span></a>
<input type=text onblur="startAni()" name=t1 >
 t2 <input type=text onblur="startAni()" name=t2>
 
<H3><U>Date Picker Testing</U></H3>
<table>
<tr>
<td>Date</td>
<td> <input type="text" id="datepicker" /></td>
</tr>
</table>

<H3> <U>Test OnMouseOver ond OnMouseOut</U> </H3>

<img onmouseover="bigImg(this)" onmouseout="normalImg(this)" border="0" src="../resources/login.png"  width="32" height="32" > 

<H3> <U> Test Toogling </U> </H3>
<p>
<input type="button" id="1" value="ON" style="color:blue"
       onclick="toggle(this);"> 
</p>

<input type="submit" value="ToggleDragDropSlider"> 
</form>

</body>
</html>