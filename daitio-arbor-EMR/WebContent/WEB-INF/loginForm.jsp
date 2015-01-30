<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Daitio Health | Welcome</title>

<link href='http://fonts.googleapis.com/css?family=Roboto:500,400italic,700italic,300,700,500italic,300italic,400'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="/daitio-arbor-EMR/CSS/foundation.css"	type="text/css" />

</head>
<body>
	<form action="/daitio-arbor-EMR/home.html" method="post">
		<nav class="top-bar" data-topbar role="navigation">
			<ul class="title-area">
				<li class="name">
					<h1>
						<a href="#">Daitio Health</a>
       
					</h1>
				</li>
			</ul>

			<section class="top-bar-section"></section>
		</nav>


		&nbsp;
		<div class="row">
			<div class="large-6 large-centered columns text-center">
				<div class="panel">
					<h3>
						<strong>Welcome.</strong>
					</h3>
					<h5>Please log in.</h5>
					<p>
						<label>Username</label> 
						<input type="text" name="username" /> 
						
						<label>Password</label>
						<input type="password" name="password" /> 
						<span class="error-text">${error}</span><br>
						
						<input id="cbRememberMe" type="checkbox"><label	for="cbRememberMe">Remember Me</label><br> 
						<input type="submit" value="Log In" class="small success button" /><br />
					</p>
				</div>
			</div>
		</div>
	</form>
</body>
</html>