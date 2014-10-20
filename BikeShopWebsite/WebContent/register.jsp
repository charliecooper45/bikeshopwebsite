<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="css/register.css">
	<script src="javascript/validation.js"></script>
	<title>Bike Shop</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<form id="registerForm" action="/BikeShopWebsite/FormController" method="post" >
		<label for="email">Email:</label>
		<input type="text" id="email" name="email" />
		<label class="error" id="emailError">Not a valid e-mail address</label>
		
		<label for="password">Password:</label>
		<input type="password" id="password" name="password" />
		<label class="error" id="passwordError">Password cannot be empty</label>
		
		<label for="confirmPassword">Confirm password:</label>
		<input type="password" id="confirmPassword" name="confirmPassword" >
		<label class="error" id="confirmPasswordError">Confirm password cannot be empty</label>
		
		<input type="submit" value="Register" onClick="return validateRegisterForm();" />
		<input type="hidden" name="formType" value="register"/>
	</form>
	<jsp:include page="footer.jsp" />
</body>
</html>