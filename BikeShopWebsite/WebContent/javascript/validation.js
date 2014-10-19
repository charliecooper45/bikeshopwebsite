function validateLoginForm() {
	var email = document.forms["loginForm"]["email"].value;
	var valid = validateEmail(email);
	if(valid == false) {
		document.getElementById("emailError").style.visibility = 'visible';
		return false;
	}
	
	var password = document.forms["loginForm"]["password"].value;
	if (password == null || password == "") {
		document.getElementById("passwordError").style.visibility = 'visible';
		return false;
	} else {
		document.getElementById("passwordError").style.visibility = 'hidden';
	}

	return true;
}

function validateRegisterForm() {
	var email = document.forms["registerForm"]["email"].value;
	var valid = validateEmail(email);
	
	if(valid == false) {
		document.getElementById("emailError").style.visibility = 'visible';
		return false;
	}
	
	return false;
}

function validateEmail(email) {
	var atpos = email.indexOf("@");
	var dotpos = email.lastIndexOf(".");
	if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length) {
		return false;
	}
	document.getElementById("emailError").style.visibility = 'hidden';
}