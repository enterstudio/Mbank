
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Money Transfers</title>

<link href="css/wrapperClassStyle.css" rel="stylesheet" type="text/css" />
<link href="css/allPagesCSS.css" rel="stylesheet" type="text/css" />

<jsp:include page="/wrapperPage/MainHeader.jsp"></jsp:include>
<jsp:include page="/wrapperPage/navigation.jsp"></jsp:include>
</head>
<body>

	<%
		String message = String.valueOf(request.getAttribute("message"));
		if (message.equals("null")) {
			message = "Waiting For input (Here You will See Errors If Excists)";
		}
	%>


	<%!int accountId;
	double accountBalance;
	String UserName;
	double sumOfAction;%>
	<%
		accountId = (Integer) request.getAttribute("accountId");
		accountBalance = (Double) request.getAttribute("accountBalance");
		UserName = (String) request.getSession(false).getAttribute(
				"userName");

		accountBalance = Math.round(accountBalance);
	%>


	<p id="message" class="message">
		<b>Status : <%=message%></b>
	</p>
	<hr>
	<div class="mainStyle">
		<h4>
			Account Id :
			<%=accountId%></h4>
		<br>
		<h4>
			Account Balance :
			<%=accountBalance%></h4>
		<br>

	</div>
	<img alt="100 Years Of Making money" src="images/bankingBack2.jpg ">
	<jsp:include page="/wrapperPage/footer.html"></jsp:include>
</body>
</html>