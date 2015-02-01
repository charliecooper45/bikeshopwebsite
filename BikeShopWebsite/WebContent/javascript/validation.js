function validateLoginForm() {
	var email = document.forms["loginForm"]["email"].value;
	var valid = validateEmail(email);
	if (valid == false) {
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
	var validated = true;

	var password = document.forms["registerForm"]["password"].value;
	if (password.length == 0) {
		document.getElementById("passwordError").style.visibility = 'visible';
		validated = false;
	} else {
		document.getElementById("passwordError").style.visibility = 'hidden';
	}

	var confirmPassword = document.forms["registerForm"]["confirmPassword"].value;
	if (confirmPassword.length == 0) {
		document.getElementById("confirmPasswordError").style.visibility = 'visible';
		validated = false;
	} else {
		document.getElementById("confirmPasswordError").style.visibility = 'hidden';
	}

	if (validated) {
		if (password != confirmPassword) {
			document.getElementById("confirmPasswordError").innerHTML = "Password values must be equal";
			document.getElementById("confirmPasswordError").style.visibility = 'visible';
			validated = false;
		} else {
			document.getElementById("confirmPasswordError").style.visibility = 'hidden';
		}
	}

	var email = document.forms["registerForm"]["email"].value;
	var valid = validateEmail(email);
	if (valid == false) {
		document.getElementById("emailError").style.visibility = 'visible';
		validated = false;
	}

	var firstName = document.forms["registerForm"]["firstName"].value;
	if (firstName.trim() == "") {
		document.getElementById("firstNameError").style.visibility = 'visible';
		validated = false;
	} else {
		document.getElementById("firstNameError").style.visibility = 'hidden';
	}

	var surname = document.forms["registerForm"]["surname"].value;
	if (surname.trim() == "") {
		document.getElementById("surnameError").style.visibility = 'visible';
		validated = false;
	} else {
		document.getElementById("surnameError").style.visibility = 'hidden';
	}

	return validated;
}

function validatePayForm() {
	var validated = true;

	// validate the card number field
	var cardNumber = document.forms["payForm"]["cardNumber"].value;
	validated = validateNotEmpty(cardNumber, "cardNumberError");

	return validated;
}

function validateEmail(email) {
	var atpos = email.indexOf("@");
	var dotpos = email.lastIndexOf(".");
	if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length) {
		return false;
	}
	document.getElementById("emailError").style.visibility = 'hidden';
}

function validateNotEmpty(value, elementId) {
	if(value.length == 0) {
		document.getElementById(elementId).style.visibility = 'visible';
		return false;
	} else {
		document.getElementById(elementId).style.visibility = 'hidden';
		return true;
	}
	return false;
}

function validateDate(dateString) {
	if (!/^\d{1,2}\/\d{1,2}\/\d{4}$/.test(dateString))
		return false;
}