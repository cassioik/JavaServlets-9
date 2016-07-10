<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
	<hr>
	<%
		out.println("Your IP address is " + request.getRemoteAddr());
	%>
	<br>
	<p>Browser Version : ${header["user-agent"]}</p>
</body>
</html>