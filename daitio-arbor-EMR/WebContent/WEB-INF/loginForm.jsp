<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>


<link rel="stylesheet" type="text/css" href="/daitio-arbor-EMR/CSS/login.css" />

</head>

<body>
	<h3>EMR - Application</h3>
	

	<form:errors path="user1.*" />
	
	<form action="/daitio-arbor-EMR/myPage.html" method="post">
	<table>
		<tr><td class="demo">Username :</td>  <td>     <input type="text" name="username" />   </td> </tr>
		<tr><td class="demo">Password :  		</td>  <td>       <input type="text" name="password" />  </td> </tr>
		
		<tr><td><input type="submit" value="Login" class="btn btn-primary demo" /></td></tr>
	</table>
	</form>
 
</body>
</html>
