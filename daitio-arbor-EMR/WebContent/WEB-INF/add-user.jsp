<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add User</title>
</head>
<body>
	<jsp:include page="master-doctor.jsp" />
	<div class="row">
		<form action="/daitio-arbor-health/adduser.html" method="post">
			<section id="main-content"> <section class="wrapper">

			<div class="col-md-6 col-sm-6 mb">
				<div class="content-panel">
					<h4>Add User</h4>
					<hr>
					<div class="panel">
						<div class="form-group">
							<label class="col-sm-4 control-label">First Name</label> <input
								class="form-control" type="text" id="txtFirstName"
								name="firstName" />
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">Last Name</label> <input
								class="form-control" type="text" id="txtLastName"
								name="lastName" />
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">Username</label> <input
								class="form-control" type="text" id="txtUsername"
								name="username" />
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">Temporary Password</label>
							<input class="form-control" type="text" id="txtPassword"
								name="password" />
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">Role</label> 
							<select name="role">
								<option value="PATIENT">Patient</option>
								<option value="DOCTOR">Doctor</option>
							</select>
						</div>

						<div class="form-group">
							<label class="error-text col-sm-5">${error }</span><br> <br></label>
						</div>
						<br />

						<div class="form-group">
							<div>
								<input type="submit" class="btn btn-primary btn-sm"
									name="action" value="Submit" />
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-6 col-sm-6 mb">
				<div class="content-panel">
					<h4>All Users</h4>
					<hr>
					<div class="panel">
						<table id="weightTable" style="width: 100%"
							class="table table-striped table-advance table-hover">
							<tr>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Role</th>
								<th></th>
							</tr>
							<c:forEach items="${userList }" var="list">
								<tr>
									<td>${list.firstName }</td>
									<td>${list.lastName }</td>
									<td>${list.role }</td>
									<td><a href="edituser.html?id=${list.id }">View
											Patient</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>

			</section> </section>
		</form>
	</div>
	<script>
		
	</script>
</body>
</html>