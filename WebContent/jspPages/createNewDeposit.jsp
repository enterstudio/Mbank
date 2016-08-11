
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Create New Deposit</title>

<link href="css/wrapperClassStyle.css" rel="stylesheet" type="text/css" />
<link href="css/allPagesCSS.css" rel="stylesheet" type="text/css" />

<script src="scripts/scripts.js"></script>

<jsp:include page="/wrapperPage/MainHeader.jsp"></jsp:include>
<jsp:include page="/wrapperPage/navigation.jsp"></jsp:include>


</head>
<body>
	<h1>
		Create New Deposit For
		<%=request.getSession(false).getAttribute("userName")%>
	</h1>
	<hr>

	<div class="mainStyle">

		<form name="createNewDeposit" action="Controller?command=makeDeposit" method="post" onsubmit="return InputAndCommisionCreateNewDeposit()">
			<fieldset>
				<legend>Please Fill Field</legend>
				<label>Amount Of Deposit :</label> <input type="text"
					name="amountOfDeposit" /> 
					<br><br>
					<h2>Closing Date </h2> <label>Day
					:</label> <select name="day">
					<%
						for (int i = 1; i < 32; i++) {
					%>
					<option value="<%=i%>"><%=i%></option>
					<%
						}
					%>
				</select><br><br> <label>Month : </label> <select name="month">
					<%
						for (int i = 1; i < 13; i++) {
					%>
					<option value="<%=i%>"><%=i%></option>
					<%
						}
					%>
				</select> <br><br><label>Year : </label> <select name="year">
					<%
						for (int i = 2015; i < 2051; i++) {
					%>
					<option value="<%=i%>"><%=i%></option>
					<%
						}
					%>
				</select> <br>
				<br> <input type="hidden" name="jspId" value="createDeposit">
				<input type="submit" value="MakeDeposit" class="buttons">

			</fieldset>
		</form>



	</div>
	<img src="images/BankingBacround2.jpg" alt="Banking Photo Background">
	<jsp:include page="/wrapperPage/footer.html"></jsp:include>
</body>
</html>