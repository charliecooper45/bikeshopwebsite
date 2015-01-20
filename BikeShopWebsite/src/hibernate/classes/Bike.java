package hibernate.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = Bike.QUERY_BY_BIKE_MODEL, query = "from Bike where bikeModel = :bikeModel")
})
@Entity
@Table(name = "bike", catalog = "hibernate_test_database")
public class Bike implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String QUERY_BY_BIKE_MODEL = "Query.By.Bike.Model";

	//TODO: the number of bikes attached to a BikeModel can dictate the number in stock (store procedure)
	//TODO: the system should respond correctly if the user tries to buy a bike that is out of stock
	@Id
	@Column(unique = true, nullable = false)
	private String serialNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private BikeModel bikeModel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Basket basket;
	
	// default constructor for hibernate
	public Bike() {
		super();
	}
	
	public Bike(String serialNumber, BikeModel bikeModel) {
		this.serialNumber = serialNumber;
		this.bikeModel = bikeModel;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	
	public BikeModel getBikeModel() {
		return bikeModel;
	}

	public void setBikeModel(BikeModel bikeModel) {
		this.bikeModel = bikeModel;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}
}
