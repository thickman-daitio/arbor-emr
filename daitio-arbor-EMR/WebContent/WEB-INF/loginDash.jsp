<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>DAITIO | Arbor-EMR</title>

    <!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="/daitio-arbor-EMR/assets/css/bootstrap.css"	type="text/css" />
    <!--external css-->
    <link rel="stylesheet" href="/daitio-arbor-EMR/assets/font-awesome/css/font-awesome.css"	type="text/css" />
        
    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/daitio-arbor-EMR/assets/css/style.css"	type="text/css" />
    <link rel="stylesheet" href="/daitio-arbor-EMR/assets/css/style-responsive.css"	type="text/css" />

  </head>

  <body>

      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->

	  <div id="login-page">
	  	<div class="container">
	  	
				<form action="/daitio-arbor-EMR/home.html"  class="form-login" method="post">
		        <h2 class="form-login-heading">DAITIO | Health</h2>
		        <div class="login-wrap">
						<input type="text" name="username" class="form-control" placeholder="User ID" autofocus /> 
		            <br>
		            <input type="password" name="password" class="form-control" placeholder="Password" /> <br>
					<span class="error-text">${error }</span><br>
		            <label class="checkbox">
		                <span class="pull-right">
		                    <a data-toggle="modal" href="login.html#myModal"> Forgot Password?</a>
		
		                </span>
		            </label>
		            <button class="btn btn-theme btn-block" type="submit">LOG IN</button>	
		        </div>
		      </form>	  	
	  	
	  	</div>
	  </div>

    <!-- js placed at the end of the document so the pages load faster -->
	<script language="javascript" type="text/javascript" src="/daitio-arbor-EMR/assets/js/jquery.js"></script>
	<script language="javascript" type="text/javascript" src="/daitio-arbor-EMR/assets/js/bootstrap.min.js"></script>

    <!--BACKSTRETCH-->
    <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
	<script language="javascript" type="text/javascript" src="/daitio-arbor-EMR/assets/js/jquery.backstretch.min.js"></script>
    <script>
        $.backstretch("assets/img/Daitio.jpg", {speed: 500});
    </script>


  </body>
</html>
