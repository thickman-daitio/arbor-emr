<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Encounter</title>
</head>
<body>
	<jsp:include page="master.jsp" />
	<div class="row">
		<div class="large-6 large-centered columns text-center">
			<br>
			<h3>Encounter</h3>
			<div class="panel">
				<form action="/daitio-arbor-EMR/encounter.html" method="post">
					<label>Comment</label>
					<input type="text" id="comments" name="comments"/><br>
					<input type="submit" id="btnSubmit" value="Submit" class="small success button" /><br>
					<p>Patient Comments: ${record.comments }</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>