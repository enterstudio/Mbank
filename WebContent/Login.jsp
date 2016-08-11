<%@ page session="false" %>
<!DOCTYPE html>

<%HttpSession session = request.getSession(false);
  if(session!=null)
  {
		String nextPage="/homePage.jsp";
		getServletContext().getRequestDispatcher(nextPage).forward(request, response);
  }
%>
<html lang="en">
	  <head>
		    <meta charset="utf-8">
		    <meta http-equiv="X-UA-Compatible" content="IE=edge">
		    <meta name="viewport" content="width=device-width, initial-scale=1">  <!--scale to screen-->
		    
		    <title>Mbank Login Page </title>
		
		    <link href="css/bootstrap.min.css" rel="stylesheet"> 
			<link href="css/myLogin.css" rel="stylesheet" > 

	</head>
	<body>
		<div class="header">
		<h1>MBank</h1>
		</div>
		<br>
		<div class="formLogo"></div>
		<br>
	
		<div class="loginDiv">
			<form action="LoginServlet" id="loginForm" method="post" > 
				<div class="usernameDiv">
					<label for="inputUsername">User Name : </label>
					<input type="text" name="username" class="form-control" id="inputUsername" placeholder="clients user name">
				</div>
				<br>
				<div class="passwordDiv">
					<label for="inputPassword">Password : </label>
					<input type="password" name="password" class="form-control" id="inputPassword" placeholder="clients password">
				</div>
				
				<div class="checkbox">
					<label><input type="checkbox">Remember me</label>
				</div>
				<button type="submit" class="btn btn-primary">Login</button>    
			</form>
		</div>
		<div class = "handkshake"></div>
	</body>
</html>



		
		
		
		
