<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

<!-- <link href="<c:url value='/WEB-INF/CSS/login.css' />" rel="stylesheet">
-->

<style>
@import url(http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700);
.demo, body { 
  font-family: "Roboto";
  margin: 10px;
  font-size: 12px;
}

.ripple {
  background: rgba(0,0,0,0.5);
}

@btn-primary: #4285f4;
@btn-primary-text: #FFFFFF;


.btn {
  display: inline-block;
  padding: 10px;
  
  cursor: pointer;
  border-radius: 4px;
  text-transform: uppercase;
  text-decoration: none;
  
  transition: box-shadow 0.28s cubic-bezier(0.4, 0, 0.2, 1);
  
  box-shadow: 0 3px 8px 0 rgba(0, 0, 0, 0.19), 0 6px 13px 0 rgba(0, 0, 0, 0.24);
  

  &:active {
    box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.23), 3px 8px 20px 0 rgba(0, 0, 0, 0.18), -3px 8px 20px 0 rgba(0, 0, 0, 0.18);
  }

  &.btn-primary {
    background: @btn-primary;
    color: @btn-primary-text;
  }
}

</style>

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
