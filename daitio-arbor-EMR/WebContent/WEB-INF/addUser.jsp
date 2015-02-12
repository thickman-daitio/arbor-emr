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
		<form action="/daitio-arbor-EMR/adduser.html" method="post">
		      <section id="main-content">
          <section class="wrapper">
		<div class="large-6 large columns text-center">
		
			<br>
			<h3>Add User</h3>
			<div class="panel">
					<label>First Name</label>
					<input type="text" id="txtFirstName" name="firstName" />
					<label>Last Name</label> 
					<input type="text" id="txtLastName" name="lastName" />
					<label>Username</label>
					<input type="text" id="txtUsername"	name="username" />
					<label>Temporary Password</label> 
					<input type="text" id="txtPassword" name="password" />
					<span class="error-text">${error }</span><br><br>
					<input type="submit" id="btnSubmit" name="action" value="Submit" class="small success button" />									
				
			</div>
		</div>
		<div class="large-6 large columns text-center">
			<br>
			<h3>All Users</h3>
			<div class="panel">
				<p>
					${userList }
				</p>
			</div>
		</div>
		</section>
		</section>
		</form>
	</div>
<script>

</script>
</body>
</html>