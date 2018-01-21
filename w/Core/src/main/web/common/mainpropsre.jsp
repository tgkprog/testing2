 <%@include file="/common/header.jsp" %>
 <br>
 Reload Main props at <%

com.exilant.tfw.utils.DBUtils.initialize();
out.print( new java.util.Date() +  "<br>" +com.exilant.tfw.bean.UtlConf.getProperties());
%>