<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Patient</title>
</head>
<body>
	<jsp:include page="master.jsp" />
	<div class="row">
		<form action="/daitio-arbor-EMR/viewpatient.html" method="post">
		<div class="large-6 large columns text-center">		
			<br>
			<h3>View Patient</h3>
			<div class="panel">
				<label>First Name</label>
				<input type="text" id="txtFirstName" name="firstName" value="${firstName }"/>
				<label>Middle Name</label> 
				<input type="text" id="txtMiddleName" name="lastName" value="${middleName }"/>		
				<label>Last Name</label> 
				<input type="text" id="txtLastName" name="lastName" value="${lastName }"/>		
				<label>Date of Birth</label> 
				<input type="text" id="txtLastName" name="lastName" value="${dateOfBirth }"/>		
				<label>Address</label> 
				<input type="text" id="txtLastName" name="lastName" value="${address1 }"/>		
				<label>Address</label> 
				<input type="text" id="txtLastName" name="lastName" value="${address2 }"/>		
				<label>City</label> 
				<input type="text" id="txtLastName" name="lastName" value="${city }"/>		
				<label>State</label> 
				<input type="text" id="txtLastName" name="lastName" value="${state }"/>		
				<label>Zip</label> 
				<input type="text" id="txtLastName" name="lastName" value="${zip }"/>		
				<label>Primary Phone</label> 
				<input type="text" id="txtLastName" name="lastName" value="${primaryPhone }"/>		
				<label>Secondary Phone</label> 
				<input type="text" id="txtLastName" name="lastName" value="${secondaryPhone }"/>		
				<label>Email</label> 
				<input type="text" id="txtLastName" name="lastName" value="${email }"/>		
				<label>Height</label> 
				<input type="text" id="txtLastName" name="lastName" value="${height }"/>
				<label>Insurance Type</label> 
				<input type="text" id="txtLastName" name="lastName" value="${insuranceType }"/>	
				<input type="submit" id="btnDelete" name="action" value="Delete Patient" class="small alert button" />								
			</div>
		</div>
		<div class="large-6 large columns text-center">
			<br>
			<h3>Test</h3>
			<div class="panel">
			</div>
		</div>
		</form>
	</div>
<script>

</script>
</body>
</html>