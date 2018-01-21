<img alt="TFW logo" src=/common/imgs/tfwBanner.png >
		<h1> Testing Framework Automation </h1>
		
<br>
<%

String home = (String)session.getAttribute("homePagePath");
if(home == null){
	home = "/core";
}
out.println("<a href=\"" + home + "\"> home </a> | ");
%>


<a href=/common/>common</a> 
<br>