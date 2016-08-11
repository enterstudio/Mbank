<%@page import="Util.UtilSite"%>
<%@page import="Beans.ClientBean"%>
<%@page import="Beans.AccountBean"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
	<link href="../css/wrapperClassStyle.css" rel="stylesheet" type="text/css" />
	


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>	
	
<script>
$(document).ready(function(){
	$('#accountDetails').toggle(1000).toggle(1500);
	
})
</script>	
	
</head>
<body>
<div id="accountDetails">
<%
ClientBean client =(ClientBean) request.getSession(false).getAttribute("clientBean");
AccountBean account = UtilSite.viewAccountDetailsWithClientId(client.getClient_id()); 
double balance = account.getBalance();
balance = Math.round(balance);
%>
<div id="details">
Client Id : <br><label id="accountDet"><%=account.getClient_id() %></label><br>
Account Id : <br><label id="accountDet"><%=account.getAccount_id() %></label><br>
Account Balance : <br><label id="accountDet"><%=balance %></label><br>
Credit Limit :<br><label id="accountDet"><%=account.getCredit_limit() %></label><br>
Comment : <br><label id="accountDet"><%=account.getComment() %></label><br>

</div>

</div>
</body>
</html>