<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Success</title>
</head>
<body>
<h2>Hello, ${user.firstName }</h2>
<table>
<tr>
	<td><b>Username</b></td><td>${user.username }</td>
</tr>
<tr>
	<td><b>Password (Salt)</b></td><td>${user.password }</td>
</tr>
<tr>
	<td><b>First Name</b></td><td>${user.firstName }</td>
</tr>
<tr>
	<td><b>Last Name</b></td><td>${user.lastName }</td>
</tr>
</table>
</body>
</html>