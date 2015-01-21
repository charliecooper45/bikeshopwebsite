package hibernate.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name=User.QUERY_USER_BY_EMAIL, query="from User u where u.email = :email"),
})
@Entity
@Table(name= "user", catalog = "hibernate_test_database")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String QUERY_USER_BY_EMAIL = "User.By.Email";

	@Id
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column
	private String firstName;
	
	@Column
	private String surname;
	
	@OneToOne
	private Basket basket;
	
	// default constructor for hibernate
	public User() {
		super();
	}

	public User(String email, String password, String firstName, String surname) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Basket getBasket() {
		return basket;
	}
	
	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	
	@Override
	public String toString() {
		return firstName + " " + surname;
	}
}