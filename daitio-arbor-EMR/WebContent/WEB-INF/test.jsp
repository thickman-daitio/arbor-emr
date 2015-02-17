<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TEST</title>
</head>
<body>
	<jsp:include page="masterDash.jsp" />
	<div class="row">
		<form action="/daitio-arbor-EMR/home.html" method="post">
		<section id="main-content">
        <section class="wrapper">
		<div class="large-6 large columns text-center">
			<h2>Test: ${user.firstName }</h2>
		</div>
		
		</section>
		</section>
		</form>
	</div>
<script>

</script>
</body>
</html>