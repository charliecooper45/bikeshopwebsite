package hibernate.classes;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "bikeshop_order", catalog = "hibernate_test_database")
public class BikeShopOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;
	
	@OneToOne(optional = false)
	private User user;
	
	@OneToMany(mappedBy = "order")
	@Cascade(CascadeType.SAVE_UPDATE)
	private Set<Bike> bikes;
	
	// default constructor for hibernate
	public BikeShopOrder() {
		super();
	}

	public BikeShopOrder(User user, Set<Bike> bikes) {
		this.user = user;
		this.bikes = bikes;
		
		for (Bike bike : bikes) {
			bike.setOrder(this);
		}
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Set<Bike> getBikes() {
		return bikes;
	}
}
