
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    <%@page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Withdraw From Account</title>

<link href="css/wrapperClassStyle.css" rel="stylesheet" type="text/css" />
<link href="css/allPagesCSS.css" rel="stylesheet" type="text/css" />

<script src="scripts/scripts.js"></script>

<%String realUserName =(String) request.getSession(false).getAttribute("userName");
	String realPassword = (String) request.getSession(false).getAttribute("password");
%>

<jsp:include page="/wrapperPage/MainHeader.jsp"></jsp:include>
<jsp:include page="/wrapperPage/navigation.jsp"></jsp:include>
<jsp:include page="/wrapperPage/accountDetails.jsp"></jsp:include>
</head>
<body>
<%String message = String.valueOf(request.getAttribute("message"));
	if(message.equals("null"))
	{		
		message = "Waiting For input (Here You will See Errors If Excists)";
	}
%>

<h1>Withdraw From <%=request.getSession(false).getAttribute("userName") %>'s Account</h1>

<div class="mainStyle">

<form name="withdrawFromAccount" onsubmit="return InputAndCommisionWithdraw()" action="Controller?command=transactionHappend" method="post">
<fieldset>
<legend>Withdraw From Account</legend>
<label>Enter Sum Of Action :</label><br>
<input type="text" name="sumOfAction"  /><br><br>
<br>
<h4>Extra validation for Transaction</h4>
<label>User Name : </label> <input type ="text" name="userNameVal" /> <br>
<label>Password  : </label> <input type ="password" name="passwordVal" /> <br>
<br><br>

<input type="hidden" name="jspId" value="withdraw">
	<p id="message" class="message">Status : <%=message%></p>
<input type="submit"  value="Withdraw" class="buttons"  >


</fieldset>
</form>



</div>

<img alt="depositGif" src="images/deposit&withdraw.gif">

	<jsp:include page="/wrapperPage/footer.html"></jsp:include>
</body>
</html>