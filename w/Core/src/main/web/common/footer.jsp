<br>
<%
{
String home1 = (String)session.getAttribute("homePagePath");
String homeName1 = (String)session.getAttribute("homePageName");
if(home1 == null){
	home1 = "/core";
	homeName1 = "core";
}
out.println("<a href=\"" + home1 + "\"> " + homeName1 + " home </a> | ");
}
%>


<a href=/common/>common</a> | <a href=/common/info.jsp>info</a>  | <a href=/common/poolEnd.jsp?r=13>End pools</a>
<br> <a href=/agent/?r=13>Agent</a>  if installed on this tomcat