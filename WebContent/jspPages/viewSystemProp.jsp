<%@ page session="false" %>
<%@page import="Beans.PropertiesBean"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
 <%@ page import="java.util.*" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>View System Properties</title>

<link href="css/wrapperClassStyle.css" rel="stylesheet" type="text/css" />
<link href="css/allPagesCSS.css" rel="stylesheet" type="text/css" />

<jsp:include page="/wrapperPage/MainHeader.jsp"></jsp:include>
<jsp:include page="/wrapperPage/navigation.jsp"></jsp:include>

</head>

<body>


<h3>Mbank's System Properties</h3>
<hr>

<div class="scrollIt">
<table class="gradienttable">

<tr>
<th><p>Properties Key</p></th>
<th><p>Properties Value</p></th>
</tr>


<%
@SuppressWarnings("unchecked")
List<PropertiesBean> propList = (List<PropertiesBean>)request.getAttribute("propList"); 

for(int i=0;i<propList.size();i++)
{
%>

<tr>
<td><%= propList.get(i).getProp_key() %> </td><td><%= propList.get(i).getValue() %></td>
</tr>
<%
}
%>
</table>
</div>
	<jsp:include page="/wrapperPage/footer.html"></jsp:include>
</body>

</html>