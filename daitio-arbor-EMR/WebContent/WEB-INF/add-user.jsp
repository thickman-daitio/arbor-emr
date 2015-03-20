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
		<form action="/daitio-arbor-health/adduser.html" method="post">
		  <section id="main-content">
          <section class="wrapper">
          
          	<div class="col-md-6 col-sm-6 mb">
	          	<div class="content-panel">
	                  	  	  <h4><i class="fa fa-angle-right"></i>Add User</h4>
	                  	  	  <hr>
					<div class="panel">
							<div class="form-group">
								<label class="col-sm-4 control-label ">First Name</label>
								<input class="form-control" type="text" id="txtFirstName" name="firstName" />
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label">Last Name</label> 
								<input class="form-control" type="text" id="txtLastName" name="lastName" />
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label">Username</label>
								<input class="form-control" type="text" id="txtUsername"	name="username" />
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label">Temporary Password</label> 
								<input class="form-control" type="text" id="txtPassword" name="password" />
							</div>				
							
							<div class="form-group">
								<label class="error-text col-sm-5">${error }</span><br><br></label>
							</div>
							<br/>
							
							<div class="form-group">
								<div>
									<input type="submit" class="btn btn-primary btn-sm" name="action" value="Submit" />									
								</div>
							</div>
					</div>
				</div>
			</div>
			
			<div class="col-md-6 col-sm-6 mb">
	          	<div class="content-panel">
	                  	  	  <h4><i class="fa fa-angle-right"></i>All Users</h4>
	                  	  	  <hr>
					<div class="panel">
						<p>
							${userList }
						</p>
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