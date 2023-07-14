<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<% 
	Connection con=(Connection)application.getAttribute("jdbccon");
	List<Integer> sps=(List<Integer>) session.getAttribute("cart");
	if(sps==null)
	{ %>
		<h3>No product are selected Your Cart is Empty</h3>
	
	<%	
	}
	else
	{%>
	
		<ul>
		<% for(int n:sps){ %>
			<li> <%= n %> </li>
		<%} %>
		</ul>	
		<a href=>Confirm </a>
	<%}
	%>

</body>
</html>