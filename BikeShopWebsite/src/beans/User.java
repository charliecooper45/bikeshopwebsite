package beans;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -3641491359008054513L;
	private String email = "";
	private String password = "";
	private String firstName = "";
	private String surname = "";
	
	public User() {}
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public User(String email, String password, String firstName, String surname) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getSurname() {
		return surname;
	}
}
