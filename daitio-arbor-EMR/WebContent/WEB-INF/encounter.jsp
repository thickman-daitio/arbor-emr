<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add User</title>
</head>
<body>
	<form action="/daitio-arbor-EMR/encounter.html" method="post">
		<p>
			<label>Patient Data: </label>
			<input type="text" id="comments" name="comments"/><br>						
			<input type="submit" value="Submit" /><br />
			<h2>Patient Comments: ${record.comments }</h2>
		</p>
	</form>
	
	<!-- 
	<form action="/daitio-arbor-EMR/displayencounters.html" method="post">
	<ul id='allEncounters'>
		<li>${encounter.commentshtml}</li>
	</ul>
	
	<input type="submit" value="Display All Encounters" /><br />
	
	</form>
	 -->
</body>
</html>