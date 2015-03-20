<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Success</title>
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
							<td><h2>03</h2></td><td><h2>02</h2></td><td><h2>15</h2></td>
						</tr>
						<tr>
							<td class="red-alert">Urgent</td><td class="amber-alert">At Risk</td><td class="green-alert">Stable</td>
						</tr>
					</table>
					</div>
            	  
					<div class="panel">
						<p>
							${patientList }
						</p>
					</div>
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
				</table>
			</div>
          </section>
      </section>
      <!--main content end-->
</body>
</html>