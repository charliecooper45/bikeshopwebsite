function validateForm() {
	var email = document.forms["loginform"]["email"].value;
	var valid = validateEmail(email);
	if(valid == false) {
		document.getElementById("emailerror").style.visibility = 'visible';
		return false;
	}
	
	var password = document.forms["loginform"]["password"].value;
	if (password == null || password == "") {
		document.getElementById("passworderror").style.visibility = 'visible';
		return false;
	} else {
		document.getElementById("passworderror").style.visibility = 'hidden';
	}

	return true;
}

function validateEmail(email) {
	var atpos = email.indexOf("@");
	var dotpos = email.lastIndexOf(".");
	if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length) {
		return false;
	}
	document.getElementById("emailerror").style.visibility = 'hidden';
}