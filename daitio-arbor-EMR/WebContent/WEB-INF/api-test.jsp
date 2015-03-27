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
				<h4>API!</h4>
				<hr>
	
				<form action="/daitio-arbor-health/api-test.html" method="post">
					<p>
						${result }
					</p>
					<input type="submit" id="btnSubmit" name="action"
						value="Do API Call" class="btn btn-danger" />
				</form>
			</div>
		</div>

		</section> 
	</section>
	<!--main content end-->
</body>
</html>