<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.cj.protocol.Resultset"%>
<%@page import="java.sql.PreparedStatement"%>
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
	PreparedStatement ps=null;
	ResultSet rs=null;
	    try{
				ps=con.prepareStatement("select * from product where cat_id=?");
				String cid=request.getParameter("cid");
				ps.setString(1, cid);
				rs=ps.executeQuery();
				
	%>  <form action="addToCart" method="get">
			Select product::<br/>
			<select name="selectedPid">
			
			<%
			while(rs.next())
			{%>
			
				<option value=<%=rs.getString(1)%> > <%= rs.getString(2) %> </option>
				
				
		 <% }
			
			%>
			</select>
			<br>
			<input type="submit" value="ADD TO CART"/> 
	
		</form>
		<%
		
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	%>
	

</body>
</html>