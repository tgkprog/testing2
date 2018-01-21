<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ page import="java.io.*"%>    
<%@ page import="org.apache.log4j.Logger" %> 
  <%@ page import="org.apache.log4j.PropertyConfigurator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>File Upload</title>
</head>
<body>
<%!
			static Logger log = Logger.getLogger("fileupload.jsp");

			%> 
 
<%
  	PropertyConfigurator.configure("/Users/Manoj/Desktop/apache-tomcat-7.0.37/conf/log4j.properties");
	log.info("inside fileupload.jsp");
        String saveFile = "";
        String contentType = request.getContentType();
        if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
              DataInputStream in = new DataInputStream(request.getInputStream());
              int formDataLength = request.getContentLength();
              byte dataBytes[] = new byte[formDataLength];
              int byteRead = 0;
              int totalBytesRead = 0;
              while (totalBytesRead < formDataLength) {
                    byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
                    totalBytesRead += byteRead;
              }
              String file = new String(dataBytes);
              log.info("inside file upload");
              log.info("initial file "+file);
              saveFile = file.substring(file.indexOf("filename=\"") + 10);
            
              saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
             
              saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1, saveFile.indexOf("\""));
              int lastIndex = contentType.lastIndexOf("=");
              String boundary = contentType.substring(lastIndex + 1, contentType.length());
             
              int pos;
              pos = file.indexOf("filename=\"");
              pos = file.indexOf("\n", pos) + 1;
              pos = file.indexOf("\n", pos) + 1;
              pos = file.indexOf("\n", pos) + 1;
              
              int boundaryLocation = file.indexOf(boundary, pos) - 4;
              int startPos = ((file.substring(0, pos)).getBytes()).length;
              int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
              
              saveFile = "/Users/rashmi/Desktop/sts/" + saveFile;
              File ff = new File(saveFile);
              FileOutputStream fileOut = new FileOutputStream(ff);
              fileOut.write(dataBytes, startPos, (endPos - startPos));
  		fileOut.flush();
  		fileOut.close();
  %><Br>
<table border="2">
      <tr>
            <td><b>You have successfully upload the file by the name of:</b>
            <%
                  out.println(saveFile);
                  }
            %>
            
            </td>
      </tr>
</table>
</body>
</html>