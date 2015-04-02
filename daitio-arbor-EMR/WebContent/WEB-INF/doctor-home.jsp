<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DAITIO | ARBOR HEALTH</title>
</head>
<body>
<jsp:include page="master-doctor.jsp" />


    <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
          
            <div class="col-md-6 col-sm-6 mb">
	          	<div class="content-panel">
	          		<h4>Patient Status</h4>
            	  	<hr>
					<div class="panel" style="margin:0 auto; width:300px">
					<table cellpadding="10" style="text-align: center;">
						<tr>
							<td><h2>${bad }</h2></td><td><h2>${neutral }</h2></td><td><h2>${good }</h2></td>
						</tr>
						<tr>
							<td class="red-alert">Urgent</td><td class="amber-alert">At Risk</td><td class="green-alert">Stable</td>
						</tr>
					</table>
					<br />
					<br />
					</div>
            	  
					<table id="weightTable" style="width: 100%"
						class="table table-striped table-advance table-hover">
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Status</th>
							<th></th>
						</tr>
						<c:forEach items="${patientList }" var="list">
							<tr>
								<td>${list.firstName }</td> 
								<td>${list.lastName }</td>
								<td>${list.status }</td>
								<td><a href="viewpatient.html?id=${list.id }">View Patient</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
				
          
          	<div style="display:none">
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
				<tr>
					<td><b>Role</b></td><td>${sessionUser.role }</td>
				</tr>
				</table>
			</div>
          </section>
      </section>
      <!--main content end-->
</body>
</html>