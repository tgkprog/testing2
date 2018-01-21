<%@page import="com.exilant.tfw.report.detail.DetailedReportHelper"%><%@
page
	import="com.exilant.tfw.bean.UtlConf"%><%@
page
	import="com.exilant.tfw.report.detail.DetailedReportHelperTask"%><%@
page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
page import="java.io.*"%><%@ 
page
	import="java.util.*,com.exilant.tfw.pojo.output.*"%><%@ 
page
	import="com.exilant.tfw.core.Agents,com.exilant.tfw.util.http.*,java.io.*,java.nio.charset.Charset"
	import="com.exilant.tfw.core.master.*,java.math.BigInteger,com.exilant.tfw.pojo.input.*"
	import="com.exilant.tfw.core.RunResults,java.util.*,com.exilant.tfw.excelreader.ReadPlan,com.exilant.tfw.agent.lanes.AgentCache"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
				
				
				File nDir = new File(request.getParameter("f"));
				BufferedReader bro = null;
										try {
				bro = new BufferedReader(new InputStreamReader(new FileInputStream(nDir), Charset.forName("UTF-8")));
				String line = null;
				while ((line =bro.readLine()) != null) {
					out.println(line);
				}
			} catch (Exception e) {
				// TODO logger
				e.printStackTrace();
			}
%>