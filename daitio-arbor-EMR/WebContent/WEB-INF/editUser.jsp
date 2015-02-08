<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit User</title>
</head>
<body>
	<jsp:include page="master.jsp" />
	<div class="row">
		<form action="/daitio-arbor-EMR/edituser.html" method="post">
		<div class="large-6 large-centered columns text-center">
		
			<br>
			<h3>Edit User</h3>
			<div class="panel">
					<label>First Name</label>
					<input type="text" id="txtFirstName" name="firstName" value="${firstName }"/>
					<label>Last Name</label> 
					<input type="text" id="txtLastName" name="lastName" value="${lastName }"/>
					<label>Username</label>
					<input type="text" id="txtUsername"	name="username" disabled value="${username }"/>
					<label>New Temporary Password (TODO)</label> 
					<input type="text" id="txtPassword" name="password" value="${password }"/>
					<span class="error-text">${error }</span><br><br>		
					<input type="submit" id="btnDelete" name="action" value="Delete User" class="small alert button wide" />
					<input type="submit" id="btnSubmit" name="action" value="Submit" class="small success button wide" />										
			</div>
		</div>
		</form>
	</div>
<script>

</script>
</body>
</html>