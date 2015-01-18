package hibernate.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@NamedQueries(value = { 
		@NamedQuery(name = Basket.QUERY_BY_USER, query = "from Basket where user = :user") 
}) 
@Entity
@Table(name="basket", catalog="hibernate_test_database")
public class Basket {
	public static final String QUERY_BY_USER = "Query.By.User";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private User user;
	
	// default constructor for hibernate
	public Basket() {
		super();
	}
	
	public Basket(User user) {
		this.user = user;
	}
	
	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
