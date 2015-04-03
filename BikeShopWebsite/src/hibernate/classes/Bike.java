package hibernate.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = Bike.QUERY_BY_BIKE_MODEL, query = "from Bike where bikeModel = :bikeModel"),
	@NamedQuery(name = Bike.QUERY_ALL, query = "from Bike")
})
@Entity
@Table(name = "bike", catalog = "hibernate_test_database")
public class Bike implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String QUERY_BY_BIKE_MODEL = "Bike.By.Bike.Model";
	public static final String QUERY_ALL = "Bike.All";

	@Id
	@Column(unique = true, nullable = false, name = "serial_number")
	private String serialNumber;
	
	@ManyToOne
	@JoinColumn(name = "bike_model_id")
	private BikeModel bikeModel;
	
	@ManyToOne
	@JoinColumn(name = "basket_id")
	private Basket basket;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private BikeShopOrder order;
	
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
	
	public BikeShopOrder getOrder() {
		return order;
	}
	
	public void setOrder(BikeShopOrder order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((serialNumber == null) ? 0 : serialNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bike other = (Bike) obj;
		if (serialNumber == null) {
			if (other.serialNumber != null)
				return false;
		} else if (!serialNumber.equals(other.serialNumber))
			return false;
		return true;
	}
}
