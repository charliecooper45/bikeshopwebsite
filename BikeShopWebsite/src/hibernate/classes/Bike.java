package hibernate.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bike", catalog = "hibernate_test_database")
public class Bike {
	//TODO: the number of bikes attached to a BikeModel can dictate the number in stock (store procedure)
	//TODO: the system should respond correctly if the user tries to buy a bike that is out of stock
	@Id
	@Column(unique = true, nullable = false)
	private String serialNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private BikeModel bikeModel;
	
	// default constructor for hibernate
	public Bike() {
		super();
	}
	
	public Bike(String serialNumber, BikeModel bikeModel) {
		this.serialNumber = serialNumber;
		this.bikeModel = bikeModel;
	}
}
