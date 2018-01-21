<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
     <%@ page import="org.apache.log4j.Logger" %> 
  <%@ page import="org.apache.log4j.PropertyConfigurator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<title>Toggle And DragDrop</title>
<style id="jsbin-css">
div {font-size: 14px; margin: 20px;}
button {
    font-size: 0.857em;
    padding-bottom: 0.167em;
    border: none;
    border-radius: 0.500em;
    height: 1.750em;
    width: 4.250em;
    background-color: #EB7E10;
    color: #FFF;
    cursor: pointer;
}
button.selected {
    background-color: #F2F2F2;
    border: 0.083em solid #CDCDCD;
    color: #666;
}
button.disabled {
    background-color: #FFF;
    border: 0.083em solid #F2F2F2;
    color: #D7D7D7;
}
</style>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<style>
#draggable { width: 100px; height: 100px; padding: 0.5em; float: left; margin: 10px 10px 10px 0; }
#droppable { width: 150px; height: 150px; padding: 0.5em; float: left; margin: 10px; }
</style>
<script>
$(function() {
$( "#draggable" ).draggable();
$( "#droppable" ).droppable({
drop: function( event, ui ) {
$( this )
.addClass( "ui-state-highlight" )
.find( "p" )
.html( "Dropped!" );
}
});
});
</script>


<script>
$(function() {
$( "#slider" ).slider();
});
</script>

</head>
<body>
			<%!
			static Logger log = Logger.getLogger("toggleanddragdropandslider.jsp");
			%>
			<%
			PropertyConfigurator.configure("/Users/Manoj/Desktop/apache-tomcat-7.0.37/conf/log4j.properties");
			log.info("calling toggleanddragdropandslider.jsp +++++++++ ");
			log.info(request.getContextPath());
			%>
<form name="testToggleDropDownSlider" action="testImage.jsp" method="post">
<H1><U>Toogle Buttons</U></H1>
<div id="buttons">
    <button id="yes" data-answer="true">Yes</button>
    <button id="no" data-answer="false">No</button>
  </div>
<script>
// Bind events to your buttons
var bindButtons = function(){

    $('#buttons').on('click', 'button', function( e ){

        e.preventDefault();

        $(this)
            .addClass('selected')
            .removeClass('disabled')
        .siblings()
            .removeClass('selected')
            .addClass('disabled');
    });

};

// Init binding
bindButtons();

// Get your answer
var answer = $('#buttons .selected').data('answer');
</script>
<H1><U> Drag And Drop </U> </H1>
<div id="draggable" class="ui-widget-content">
<p>Drag me to my target</p>
</div>
<div id="droppable" class="ui-widget-header">
<p>Drop here</p>
</div>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<H1><U> Slider </U> </H1>

<div id="slider"></div>
<br>
<br>
<br>

<input type="submit" value="Test Image"> 


</form>
</body>
</html>