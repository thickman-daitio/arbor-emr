<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add User</title>
</head>
<body>
	<jsp:include page="master.jsp" />
	<div class="row">
		<div class="large-6 large-centered columns text-center">
			<br>
			<h3>Add User</h3>
			<div class="panel">
				<form action="/daitio-arbor-EMR/adduser.html" method="post">
					<p>
						<label>Username</label>
						<input type="text" id="txtUsername"	name="username" /><br> 
						<label>Password</label> 
						<input type="text" id="txtPassword" name="password" /><br> 
						<label>First Name</label>
						<input type="text" id="txtFirstName" name="firstName" /><br>
						<label>Last Name</label> 
						<input type="text" id="txtLastName" name="lastName" /><br>
						<input type="submit" id="btnSubmit" value="Submit" class="button success" /><br>
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>