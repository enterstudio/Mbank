<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@page import="Beans.DepositsBean"%>
<%@page import="Beans.ActivityBean"%>
<%@page import="Beans.PropertiesBean"%>
<%@ page import="java.util.*"%>
<%@page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>View Client Deposits</title>

<link href="css/wrapperClassStyle.css" rel="stylesheet" type="text/css" />
<link href="css/allPagesCSS.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="scripts/scripts.js"></script>

<jsp:include page="/wrapperPage/MainHeader.jsp"></jsp:include>
<jsp:include page="/wrapperPage/navigation.jsp"></jsp:include>

<% %>

</head>

<body>
<%String message = String.valueOf(request.getAttribute("message"));
	if(message.equals("null"))
	{		
		message = "Waiting For input (Here You will See Errors If Excists)";
	}
%>
<div class="mainStyle">
	<%!String userName;%>

	<%
		userName = (String) request.getSession(false).getAttribute(
				"userName");
	%>

	<h3><%=userName%>'s Deposits In Mbank
	</h3>
	<hr>
	<div class="scrollIt">
	<table class="gradienttable">

		<tr>
			<th><p>Deposit Id</p></th>
			<th><p>Client Id</p></th>
			<th><p>Balance</p></th>
			<th><p>Deposit Type</p></th>
			<th><p>Estimated Balance</p></th>
			<th><p>Opening Date</p></th>
			<th><p>Closing Date</p></th>
		</tr>


		<%
		@SuppressWarnings("unchecked")
			List<DepositsBean> depositsList = (List<DepositsBean>) request
					.getAttribute("depositList");

			for (int i = 0; i < depositsList.size(); i++) {
		%>

		<tr>
			<td><%=depositsList.get(i).getDeposit_id()%></td>
			<td><%=depositsList.get(i).getClient_id()%></td>
			<td><%=depositsList.get(i).getBalance()%></td>
			<td><%=depositsList.get(i).getDeposit_type()%></td>
			<td><%=depositsList.get(i).getEstimated_balance()%></td>
			<td><%=depositsList.get(i).getOpening_date()%></td>
			<td><%=depositsList.get(i).getClosing_date()%></td>
		</tr>
		<%
			}
		%>
	</table>
	</div>
	<form action="Controller?command=preOpenDeposit" method="post" onsubmit="return confirmPreOpenDeposit()">
		<fieldset>
			<legend>Pre Open Deposit</legend>
	<div class = "form">
	<br><br>
			Choose Deposit(By Id) 
			
			<select	name="depositId">
				<%for (int i = 0; i < depositsList.size(); i++) {%>
				<option value="<%=depositsList.get(i).getDeposit_id()%>">
					<%=depositsList.get(i).getDeposit_id()%>
				</option>
				<%
					}
				%>

			</select>
		</div>
			<br /> 
					<p id="message" class="message">Status : <%=message%></p>
			<br />
			<input type="submit" value="Pre Open" class="buttons">

		</fieldset>
	</form>
	</div>
	<img alt="background Pic" src="images/bankPic.png">
</body>
	<jsp:include page="/wrapperPage/footer.html"></jsp:include>
</html>