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
		<form action="/daitio-arbor-EMR/addpatient.html" method="post">
			<div class="large-6 large columns text-center">
				<br>
				<h3>Add Patient</h3>
				<div class="panel">
					<label>First Name</label>
					<input type="text" id="txtFirstName" name="firstName" />
					<label>Last Name</label> 
					<input type="text" id="txtLastName" name="lastName" />
					<input type="submit" id="btnSubmit" name="action"
						value="Add Dummy Patient" class="small success button" />
				</div>
			</div>
			<div class="large-6 large columns text-center">
				<br>
				<h3>All Patients</h3>
				<div class="panel">
					<p>${patientList }</p>
				</div>
			</div>
		</form>
	</div>
	<script>
		
	</script>
</body>
</html>