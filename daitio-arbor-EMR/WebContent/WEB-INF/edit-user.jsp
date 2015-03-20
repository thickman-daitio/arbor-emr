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
		<form action="/daitio-arbor-health/edituser.html" method="post">
		  <section id="main-content">
          	<section class="wrapper">
			<br>

			<div class="col-lg-8 col-lg-8 mb">
				<div class="content-panel">
					<h4><i class="fa fa-angle-right"></i>Edit User</h4>
					<hr>
					<div class="panel">
						<div class="form-group">
							<label class="col-sm-4 control-label" >First Name</label>
							<input class="form-control" type="text" id="txtFirstName" name="firstName" value="${firstName }"/>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" >Last Name</label> 
							<input class="form-control" type="text" id="txtLastName" name="lastName" value="${lastName }"/>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" >Username</label>
							<input class="form-control" type="text" id="txtUsername"	name="username" disabled value="${username }"/>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" >New Temporary Password (TODO)</label> 
							<input class="form-control" type="text" id="txtPassword" name="password" value="${password }"/>
						</div>
						
						<div class="form-group">
							<label class="error-text col-sm-4">${error }</span><br><br></label>
						</div>
						<br/>
						
						<div class="form-group">
							<div>
								<input type="submit" id="btnDelete" name="action" value="Delete User" class="btn btn-primary btn-sm" />
								<input type="submit" id="btnSubmit" name="action" value="Submit" class="btn btn-primary btn-sm" />										
							</div>
						</div>
						
					</div>
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