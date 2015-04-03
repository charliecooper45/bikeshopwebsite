package hibernate.classes;

import java.util.Collections;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name=BikeShopOrder.QUERY_ALL, query="from BikeShopOrder")
})
@Entity
@Table(name = "bikeshop_order", catalog = "hibernate_test_database")
public class BikeShopOrder {
	public static final String QUERY_ALL = "BikeShopOrder.All";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;
	
	@OneToOne(optional = false)
	private User user;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private Set<Bike> bikes;
	
	@OneToOne(optional = false)
	//TODO: cascade here
	private Payment payment;
	
	// default constructor for hibernate
	public BikeShopOrder() {
		super();
	}

	public BikeShopOrder(User user, Set<Bike> bikes, Payment payment) {
		this.user = user;
		this.bikes = bikes;
		
		for (Bike bike : bikes) {
			bike.setOrder(this);
		}
		
		this.payment = payment;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Set<Bike> getBikes() {
		return Collections.unmodifiableSet(bikes);
	}
	
	public Payment getPayment() {
		return payment;
	}
}
