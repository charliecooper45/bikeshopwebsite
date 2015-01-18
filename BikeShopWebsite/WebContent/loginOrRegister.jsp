<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Bike Shop</title>
	<link rel="stylesheet" type="text/css" href="css/loginOrRegister.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div>
		<h3>Please <a href="/BikeShopWebsite/HomeController?action=login">login</a> or 
			<a href="/BikeShopWebsite/HomeController?action=register">register</a> to continue</h3>
	</div>
	
	<jsp:include page="footer.jsp" />
</body>
</html>