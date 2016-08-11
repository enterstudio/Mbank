<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ page session="false"%>
<%@page import="Beans.PropertiesBean"%>
<%@ page import="java.util.*"%>
<%@page import="Util.UtilSite"%>
<%@page import="Beans.ClientBean"%>
<%@page import="Beans.AccountBean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link href="../css/wrapperClassStyle.css" rel="stylesheet"
	type="text/css" />


<!-- =============================================================================================== -->
<!-- =============================================================================================== -->
<!-- JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY -->
<!-- =============================================================================================== -->
<!-- =============================================================================================== -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#flip").click(function() {
			$("#panel").toggle(1500);
		});
	});
</script>
<script>
	$(document).ready(function() {
		$("#flip2").click(function() {
			$("#panel2").toggle(1500);
		});
	});
</script>


<script>
	$(document).ready(function() {
		$('#pageLoad').hide(1000).show(1000);

	})
</script>

<style>
#panel, #flip {
	padding: 5px;
	text-align: center;
	background-color: #808080;
	border: solid 1px #808080;
}

#panel {
	padding: 50px;
	display: none;
}

#panel2, #flip2 {
	padding: 5px;
	text-align: center;
	background-color: #000040;
	border: solid 1px #000040;
}

#panel2 {
	padding: 50px;
	display: none;
}
</style>

<!-- =============================================================================================== -->
<!-- =============================================================================================== -->
<!-- JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY -->
<!-- =============================================================================================== -->
<!-- =============================================================================================== -->



</head>

<body>

	<div id="pageLoad">
		<%
			String message = String.valueOf(request.getAttribute("message"));
			if (message.equals("null")) {
				message = "";
			}
		%>

		<%
			String LastVisit = (String) request.getSession(false).getAttribute(
					"lastVisited");
		%>
		<div id="header">
			<a href="Controller?command=home"><img id="headerPic"
				src="images/homepage.png"></a> <a href="Controller?command=logout"><img
				id="headerLogout" src="images/logout.jpg"></a>
			<h1>"Mbank Online Banking"</h1>
			<h4>
				Last Visit :<%=LastVisit%></h4>


			<!-- =============================================================================================== -->
			<!-- =============================================================================================== -->
			<!-- JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY -->
			<!-- =============================================================================================== -->
			<!-- =============================================================================================== -->
			<div id="flip">
				Press Here To Show/Hide Our Full System Properties<br>
				(Remember Mbank Provides Pure Transparency)
			</div>
			<div id="panel">
				<table class="jqueryTable">

					<tr>
						<th><p>Properties Key</p></th>
						<th><p>Properties Value</p></th>
					</tr>


					<%
						@SuppressWarnings("unchecked")
						List<PropertiesBean> propList = (List<PropertiesBean>) request
								.getSession(false).getAttribute("propList");

						for (int i = 0; i < propList.size(); i++) {
					%>

					<tr>
						<td><%=propList.get(i).getProp_key()%></td>
						<td><%=propList.get(i).getValue()%></td>
					</tr>
					<%
						}
					%>
				</table>

			</div>
			<br>
			<br>
			<div id="flip2">Press To Show/Hide Your Account</div>
			<div id="panel2">
				<%
					ClientBean client = (ClientBean) request.getSession(false)
							.getAttribute("clientBean");
					AccountBean account = UtilSite
							.viewAccountDetailsWithClientId(client.getClient_id());
					double balance = account.getBalance();
					balance = Math.round(balance);
				%>
				Client Id : <br>
				<label id="accountDet"><%=account.getClient_id()%></label><br>
				Account Id : <br>
				<label id="accountDet"><%=account.getAccount_id()%></label><br>
				Account Balance : <br>
				<label id="accountDet"><%=balance%></label><br> Credit Limit :<br>
				<label id="accountDet"><%=account.getCredit_limit()%></label><br>
				Comment : <br>
				<label id="accountDet"><%=account.getComment()%></label><br>

			</div>

			<!-- =============================================================================================== -->
			<!-- =============================================================================================== -->
			<!-- JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY JQUERY -->
			<!-- =============================================================================================== -->
			<!-- =============================================================================================== -->

			<h4 style="color: red"><%=message %></h4>
		</div>


	</div>
</body>
</html>