package beans;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -3641491359008054513L;
	private String email = "";
	private String password = "";
	private String errorMessage = "";
	
	public User() {}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Object getErrorMessage() {
		return errorMessage;
	}

	public boolean validate() {
		System.out.println("Email: " + email);
		if(!email.equals("charliecooper98@gmail.com")) {
			errorMessage = "Email is not recognised";
			return false;
		}
		System.out.println("Email recognised!");
		if(!password.equals("password")) {
			errorMessage = "Password is incorrect";
			return false;
		}
		
		return true;
	}
}
