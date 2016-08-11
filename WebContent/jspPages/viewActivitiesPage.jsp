<%@page import="Beans.ActivityBean"%>
<%@ page session="false" %>
<%@page import="Beans.PropertiesBean"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
 <%@ page import="java.util.*" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>View Client Activities</title>

<link href="css/wrapperClassStyle.css" rel="stylesheet" type="text/css" />
<link href="css/allPagesCSS.css" rel="stylesheet" type="text/css" />

<jsp:include page="/wrapperPage/MainHeader.jsp"></jsp:include>
<jsp:include page="/wrapperPage/navigation.jsp"></jsp:include>

</head>

<body>


<%!String userName;%>

<%userName=(String)request.getSession(false).getAttribute("userName"); %>

<h3><%=userName %>'s Activities In Mbank</h3>
<hr>
<div class="mainStyle">
<div class="scrollIt">
<table >

<tr>
<th><p>Activity Id</p></th>
<th><p>Client Id</p></th>
<th><p>Amount</p></th>
<th><p>Activity Date</p></th>
<th><p>Commission</p></th>
<th><p>Description</p></th>
</tr>


<%
@SuppressWarnings("unchecked")
List<ActivityBean> activityList = (List<ActivityBean>)request.getAttribute("activityList"); 

for(int i=0;i<activityList.size();i++)
{
%>

<tr>

<td><%= activityList.get(i).getActivity_id() %> </td>
<td><%= activityList.get(i).getClient_id() %> </td>
<td><%= activityList.get(i).getAmount() %> </td>
<td><%= activityList.get(i).getActivity_date() %> </td>
<td><%= activityList.get(i).getCommission() %> </td>
<td><%= activityList.get(i).getDescription() %> </td>

</tr>
<%
}
%>
</table>
</div>
</div>
<img alt="backgroundPhoto" src="images/stop.jpg">
	<jsp:include page="/wrapperPage/footer.html"></jsp:include>
</body>
</html>