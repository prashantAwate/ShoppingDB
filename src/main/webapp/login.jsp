<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	${cookie.error.value}<br>
	<form action="logincheck" method="post">
		Enter UID:
		<input type="text" name="uid"/><br/>
		Enter Password:
		<input type="password" name="pswd"><br/>
		
		<input type="submit" value="Submit"/>
		<input type="reset" value="Clear"/>
		</form>
		
		
</body>
</html>