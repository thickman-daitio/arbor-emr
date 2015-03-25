<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Patient</title>
</head>
<body>
	<jsp:include page="master-doctor.jsp" />
	<div class="row">
		<form action="/daitio-arbor-health/addpatient.html" method="post">
		  <section id="main-content">
	          	<section class="wrapper">
				<div class="col-lg-6 col-lg-6 mb">
					<div class="content-panel">
						<h4>Add Patient</h4>
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
							<br/>
							
							<div class="form-group">
								<div>
									<label class="col-sm-5"></label>
									<input type="submit" id="btnSubmit" name="action" value="Add Dummy Patient" class="btn btn-primary btn-sm" />			
								</div>
							</div>
							
						</div>
					</div>
				</div>
			
				<div class="col-md-6 col-sm-6 mb">
					<div class="content-panel">
						<h4>All Patients</h4>
						<hr>
			          	<table id="weightTable" style="width: 100%"
							class="table table-striped table-advance table-hover">
							<tr>
								<th>First Name</th>
								<th>Last Name</th>
								<th></th>
							</tr>
							<c:forEach items="${patientList }" var="list">
								<tr>
									<td>${list.firstName }</td> 
									<td>${list.lastName }</td>
									<td><a href="viewpatient.html?id=${list.id }">View Patient</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>			
			</section>
			</section>		
		</form>
	</div>

</body>
</html>