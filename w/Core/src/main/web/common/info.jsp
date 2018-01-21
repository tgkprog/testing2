<%@
page import = "java.lang.management.OperatingSystemMXBean,java.lang.management.ManagementFactory"
%>
<%@include file="/common/header.jsp" %>

<%
out.println("Java version JVM:" + System.getProperty("java.version") + ", "  + System.getProperty("java.vendor")
   + ", "  + System.getProperty("java.home")
);
Runtime r = Runtime.getRuntime();
long ch = 1024 * 1024;
out.println("<br>Total mem :" + r.totalMemory()/ch + ", Max :" + r.maxMemory()/ch);
out.println("<br>Free :" + r.freeMemory()/ch + " MB ");

out.println("<br>Available Processors :" + r.availableProcessors() );
	try{
		final OperatingSystemMXBean myOsBean = ManagementFactory.getOperatingSystemMXBean();
		double load = myOsBean.getSystemLoadAverage();	
		out.println("<br>Cpu load :" + load + "(averge across processors, for last minute, if less than 1 means could not determine. Probably it is CPU used by this JVM and not total CPU used by all processes so use OS tool to find full usage) ");		
		
		out.println("<br> OS " + myOsBean.getName() + ", Arch " + myOsBean.getArch() + ", OS version :" + myOsBean.getVersion());
		}catch(Throwable e){
			out.println("Cpu Load error :" + e);
		}
				%>
				
				 <a target=_new href=http://docs.oracle.com/javase/6/docs/api/java/lang/management/OperatingSystemMXBean.html> Reference </a> | 
				
<%@include file="/common/footer.jsp" %>
