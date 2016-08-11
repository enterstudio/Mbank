<%@ page session="false"%>
<%@page import="Beans.ClientBean"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Client Details</title>

<link href="css/wrapperClassStyle.css" rel="stylesheet" type="text/css" />
<link href="css/allPagesCSS.css" rel="stylesheet" type="text/css" />

<script src="scripts/scripts.js"></script>

<jsp:include page="/wrapperPage/MainHeader.jsp"></jsp:include>
<jsp:include page="/wrapperPage/navigation.jsp"></jsp:include>

</head>

<body>

<%String message = String.valueOf(request.getAttribute("message"));
	if(message.equals("null"))
	{		
		message = "Waiting For input (Here You will See Errors If Excists)";
	}
%>


	<div class="mainStyle">
		<%
			ClientBean client = (ClientBean) request.getAttribute("clientBean");
		%>
		<h1><%=request.getSession().getAttribute("userName")%></h1>
		<h3>Our Favorite Client ;)</h3>
		<hr>
		<fieldset>
			<legend>My Details</legend>
			<table class="gradienttable">

				<tr>
					<td>ClientId</td>
					<td><%=client.getClient_id()%></td>
				</tr>
				<tr>
					<td>Client Name</td>
					<td><%=client.getClient_name()%></td>
				</tr>
				<tr>
					<td>Client Type</td>
					<td><%=client.getType().toString()%></td>
				</tr>
				<tr>
					<td>Client Address</td>
					<td><%=client.getAddress()%></td>
				</tr>
				<tr>
					<td>Client E-mail</td>
					<td><%=" " + client.getEmail()%></td>
				</tr>
				<tr>
					<td>Client Phone</td>
					<td><%=client.getPhone()%></td>
				</tr>
			</table>
		</fieldset>

		<fieldset>
			<legend>Update My Details</legend>

			<form name="updateClientDetails" onsubmit="return udateDetailsValidation()" action="Controller?command=updateClientDetails" method="post">
			<div style="text-align: left;">
				<label>Client Address</label><input type="text" name="newAddress"><br><br>
				<label>Client E-mail </label><input type="text" name="newEmail"><br><br>
				<label>Client Phone </label> <input	type="text" name="newPhone">
			</div>
			<div style="text-align:center;">
				What Do you wish to update ?  <br>
				<br> <input type="checkbox" name="addressCheckBox" class="checkBox">Address<br>
				<input type="checkbox" name="emailCheckBox" class="checkBox">Email<br> 
				<input type="checkbox" name="phoneCheckBox" class="checkBox">Phone<br> 
				<input type="checkbox" name="updateAll" class="checkBox">Update All<br> <br>
			</div>	
				<p id="message" class="message">Status : <%=message%></p>
				<input type="submit" value="Update" class="buttons" >
			</form>
		</fieldset>

	</div>
	<img alt="MbankBackgroundPic" src="images/bankingBackground12.jpg">
		<jsp:include page="/wrapperPage/footer.html"></jsp:include>
</body>
</html>