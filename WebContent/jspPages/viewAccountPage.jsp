
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    <%@page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>View Account Page</title>

<link href="css/wrapperClassStyle.css" rel="stylesheet" type="text/css" />
<link href="css/allPagesCSS.css" rel="stylesheet" type="text/css" />

<jsp:include page="/wrapperPage/MainHeader.jsp"></jsp:include>
<jsp:include page="/wrapperPage/navigation.jsp"></jsp:include>
</head>
<body>
<h1>View <%=request.getSession(false).getAttribute("userName") %>'s Account</h1>
<hr>

<div class="mainStyle">
<%double balance = (Double)request.getAttribute("balance");
  balance = Math.round(balance);%>

<table class="gradienttable">

<tr>
	<td>ClientId</td> <td><%=request.getAttribute("clientId") %></td>
</tr>
<tr>
 	<td>AccountId</td> <td><%=request.getAttribute("accountId") %></td>
</tr>
<tr>	
	<td>Balance</td> <td><%=balance %></td>
</tr>
<tr>
	<td>Credit Limit</td> <td><%=request.getAttribute("creditLimit") %></td>
</tr>
<tr>	
	<td>Comment </td> <td><%=request.getAttribute("comment") %></td>
</tr>
</table>
</div>

<img alt="viewAccountPhoto" src="images/viewAccountPage.jpg">

	<jsp:include page="/wrapperPage/footer.html"></jsp:include>
</body>
</html>