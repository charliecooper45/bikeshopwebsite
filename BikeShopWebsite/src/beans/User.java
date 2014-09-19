package beans;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -3641491359008054513L;
	private String email = "";
	private String password = "";
	
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
}
