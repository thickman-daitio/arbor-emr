<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Success</title>
</head>
<body>
<jsp:include page="master.jsp" />


    <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
                <h2>Hello, ${sessionUser.firstName }</h2>
				<table>
				<tr>
					<td><b>Username</b></td><td>${sessionUser.username }</td>
				</tr>
				<tr>
					<td><b>Password (Salt)</b></td><td>${sessionUser.password }</td>
				</tr>
				<tr>
					<td><b>First Name</b></td><td>${sessionUser.firstName }</td>
				</tr>
				<tr>
					<td><b>Last Name</b></td><td>${sessionUser.lastName }</td>
				</tr>
				</table>
          </section>
      </section>
      <!--main content end-->
</body>
</html>