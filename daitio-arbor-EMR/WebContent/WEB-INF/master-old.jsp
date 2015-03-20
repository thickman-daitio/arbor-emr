<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Daitio Health</title>

<link
	href='http://fonts.googleapis.com/css?family=Roboto:500,400italic,700italic,300,700,500italic,300italic,400'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="/daitio-arbor-health/css/foundation.css"
	type="text/css" />
<link rel="stylesheet" href="/daitio-arbor-health/css/master.css"
	type="text/css" />

</head>
<body>
<nav class="top-bar" data-topbar role="navigation">
  <ul class="title-area">
    <li class="name">
      <h1><a href="#">Daitio Health</a></h1>
    </li>
     <!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
    <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
  </ul>

  <section class="top-bar-section">
    <!-- Right Nav Section -->
    <ul class="right">
      <li class="has-dropdown">
        <a href="#">Account</a>
        <ul class="dropdown">
          <li class="active"><a href="#">Change Password</a></li>
          <li class="active"><a href="login.html">Log Out</a></li>
        </ul>
      </li>
    </ul>

    <!-- Left Nav Section -->
    <ul class="left">
      <li class="active"><a href="home.html">Home</a></li>
      <li class="active"><a href="adduser.html">Users</a></li>
      <li class="active"><a href="addpatient.html">Patients</a></li>
    </ul>
  </section>
</nav>
	
<script language="javascript" type="text/javascript" src="/daitio-arbor-health/js/vendor/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/daitio-arbor-health/js/foundation.min.js"></script>
<script language="javascript" type="text/javascript" src="/daitio-arbor-health/js/vendor/modernizr.js" ></script>
<script language="javascript" type="text/javascript" src="/daitio-arbor-health/js/vendor/jquery.dataTables.js" ></script>
<script>
      $(document).foundation();
      $(document).ready(function(){

      });
</script>
</body>
</html>