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
	<jsp:include page="master.jsp" />
	<div class="row">
		<form action="/daitio-arbor-EMR/viewpatient.html" method="post">
			<div class="large-6 large columns text-center">
				<br>
				<h3>Patient Info</h3>
				<div class="panel">
					<label>First Name</label> 
					<input type="text" value="${firstName }" /> 
					<label>Middle Name</label> 
					<input type="text" value="${middleName }" /> 
					<label>Last Name</label> 
					<input type="text" value="${lastName }" /> 
					<label>Date of Birth</label> 
					<input type="text" value="${dateOfBirth }" /> 
					<label>Address</label> 
					<input type="text" value="${address1 }" /> 
					<label>Address</label>
					<input type="text" value="${address2 }" /> 
					<label>City</label>
					<input type="text" value="${city }" /> 
					<label>State</label>
					<input type="text" value="${state }" /> 
					<label>Zip</label>
					<input type="text" value="${zip }" /> 
					<label>Primary Phone</label> 
					<input type="text" value="${primaryPhone }" />
					<label>Secondary Phone</label> 
					<input type="text" value="${secondaryPhone }" /> 
					<label>Email</label> 
					<input type="text" value="${email }" /> 
					<label>Height</label>
					<input type="text" value="${height }" /> 
					<label>Insurance Type</label> 
					<input type="text" id="txtLastName" value="${insuranceType }" />
					<input type="submit" id="btnDelete" name="action" value="Delete Patient" class="small alert button" />
				</div>
			</div>
			<div class="large-6 large columns text-center">
				<br>
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
			<div class="large-6 large columns text-center">
				<br>
				<h3>Weight</h3>
				<div class="panel">
					<h5>Weight Check-In</h5>
					<label>Date</label>
					<input type="text" value="${today }" name="date"/>
					<label>Weight</label>
					<input type="text" id="txtWeight" name="weight" />
					<input type="submit" id="btnSubmitWeight" name="action"	value="Submit Weight" class="small success button"></input> 

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