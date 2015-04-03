<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Patient</title>
</head>
<body>
	<jsp:include page="master-doctor.jsp" />
	<div class="row">
		<form action="/daitio-arbor-health/viewpatient.html" method="post">
			<section id="main-content"> <section class="wrapper">

			<div class="col-md-6 col-md-6 mb">
				<div class="content-panel">
					<h3>${firstName } ${lastName }</h3>
					<div class="panel">
						<div class="form-group">
							<label class="col-sm-4 control-label" >Status</label>
							${status }
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" >Height</label>
							${height } in.
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" >Weight</label>
							${currweight } lbs.
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" >BMI</label>
							${bmi } (${bmiRange })
						</div>
					</div>
					<h4></h4>
					<br><br>
					${apitest }
					<br><br>
					<input type="submit" id="btnDelete" name="action"
						value="Delete Patient" class="btn btn-danger" />
					<input type="submit" id="btnSchedule" name="action"
						value="Schedule Appointment" class="btn btn-success" />
					<br>
					&nbsp;
				</div>
			</div>

			<div class="col-md-6 col-md-6 mb">
				<div class="content-panel">
					<h5>Log Weight</h5>
					<table style="width: 100%"
						class="table table-striped table-advance table-hover">
						<tr>
							<th>Date</th>
							<td><input type="text" value="${today }" name="date" /></td>
						</tr>
						<tr>
							<th>Weight</th>
							<td><input type="text" id="txtWeight" name="weight" /></td>
						</tr>
						<tr>
							<th></th>
							<td><input type="submit" id="btnSubmitWeight" name="action"
								value="Submit Weight" class="btn btn-success"></input></td>
						</tr>
					</table>
				</div>
				<div class="content-panel">
					<!-- <h3>Weight</h3> -->

					<h5>Weight History</h5>
					<div id="my_chart" style=""></div>
					<table id="weightTable" style="width: 100%"
						class="table table-striped table-advance table-hover">
						<tr>
							<th>Date</th>
							<th>Weight</th>
						</tr>
						<c:forEach items="${weightList }" var="list">
							<tr>
								<td>${list.strDate }</td>
								<td>${list.strWeight }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<!-- 		<div class="col-md-6 col-sm-6 mb" style="display: none">
				<div class="content-panel">
					<h3>Patient Info</h3>
					<div class="panel">
						<table style="width: 100%"
							class="table table-striped table-advance table-hover">

							<tr>
								<th>First Name</th>
								<td><input type="text" value="${firstName }" /></td>
							</tr>
							<tr>
								<th>Middle Name</th>
								<td><input type="text" value="${middleName }" /></td>
							</tr>
							<tr>
								<th>Last Name</th>
								<td><input type="text" value="${lastName }" /></td>
							</tr>
							<tr>
								<th>Date of Birth</th>
								<td><input type="text" value="${dateOfBirth }" /></td>
							</tr>
							<tr>
								<th>Address</th>
								<td><input type="text" value="${address1 }" /></td>
							</tr>
							<tr>
								<th>Address</th>
								<td><input type="text" value="${address2 }" /></td>
							</tr>
							<tr>
								<th>City</th>
								<td><input type="text" value="${city }" /></td>
							</tr>
							<tr>
								<th>State</th>
								<td><input type="text" value="${state }" /></td>
							</tr>
							<tr>
								<th>Zip</th>
								<td><input type="text" value="${zip }" /></td>
							</tr>
							<tr>
								<th>Primary Phone</th>
								<td><input type="text" value="${primaryPhone }" /></td>
							</tr>
							<tr>
								<th>Secondary Phone</th>
								<td><input type="text" value="${secondaryPhone }" /></td>
							</tr>
							<tr>
								<th>Email</th>
								<td><input type="text" value="${email }" /></td>
							</tr>
							<tr>
								<th>Height</th>
								<td><input type="text" value="${height }" /></td>
							</tr>
							<tr>
								<th>Insurance Type</th>
								<td><input type="text" id="txtLastName"
									value="${insuranceType }" /></td>
							</tr>
							<tr>
								<th></th>
								<td><input type="submit" id="btnDelete" name="action"
									value="Delete Patient" class="btn btn-danger" /></td>
							</tr>

						</table>

					</div>
				</div>
			</div>
			<div class="col-md-6 col-sm-6 mb" style="display: none">
				<div class="content-panel">
					<h3>Emergency Contact</h3>
					<div class="panel">
						<table style="width: 100%"
							class="table table-striped table-advance table-hover">
							<tr>
								<th>First Name</th>
								<td>${ecFirstName}</td>
							</tr>
							<tr>
								<th>Last Name</th>
								<td>${ecLastName }</td>
							</tr>
							<tr>
								<th>Primary Phone</th>
								<td>${ecPrimaryPhone }</td>
							</tr>
							<tr>
								<th>Secondary Phone</th>
								<td>${ecSecondaryPhone }</td>
							</tr>
							<tr>
								<th>Email</th>
								<td>${ecEmail }</td>
							</tr>
							<tr>
								<th>Relationship</th>
								<td>${ecRelationshipToPatient }</td>
							</tr>
							<tr>
								<th>Address</th>
								<td>${ecAddress1 }</td>
							</tr>
							<tr>
								<th></th>
								<td>${ecAddress2 }</td>
							</tr>
							<tr>
								<th>City</th>
								<td>${ecCity }</td>
							</tr>
							<tr>
								<th>State</th>
								<td>${ecState }</td>
							</tr>
							<tr>
								<th>Zip</th>
								<td>${ecZip }</td>
							</tr>

						</table>
					</div>
				</div>
			</div>
			--> </section> </section>
		</form>
	</div>
	<script type="text/javascript"
		src="https://www.google.com/jsapi?autoload={
            'modules':[{
              'name':'visualization',
              'version':'1',
              'packages':['corechart']
            }]
          }"></script>


	<script>
		google.load("visualization", "1", {
			packages : [ "curve_chart" ]
		});
		google.setOnLoadCallback(drawChart());

		function drawChart() {
			var data = new google.visualization.DataTable();
			data.addColumn("string", "Date");
			data.addColumn("number", "Weight");

			var table = document.getElementById("weightTable");
			for (var i = 1, row; row = table.rows[i]; i++) {
				data.addRow([ row.cells[0].innerHTML,
						parseInt(row.cells[1].innerHTML) ]);
			}

			new google.visualization.LineChart(document
					.getElementById('my_chart')).draw(data, null);
		}
	</script>
</body>
</html>