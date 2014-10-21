<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="css/login.css">
	<script src="javascript/validation.js"></script>
	<title>Bike Shop</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<form id="loginForm" action="/BikeShopWebsite/FormController" method="post" >
		<label for="email">Email:</label>
		<input type="text" id="email" name="email" value="<%= request.getAttribute("email") %>" />
		<label class="error" id="emailError">Not a valid e-mail address</label>
		<label for="password">Password:</label>
		<input type="password" id="password" name="password" >
		<label class="error" id="passwordError">Password cannot be empty</label>
		<input type="submit" value="Login" onclick="return validateLoginForm()" />
		<label id="validationError"><%= request.getAttribute("validationMessage") %></label> 
		<input type="hidden" name="formType" value="login"/>
	</form>
	<a href="/BikeShopWebsite/HomeController?action=register">Click here to register</a>
	
	<jsp:include page="footer.jsp" />
</body>
</html>