package hibernate.classes.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Object represenation of the bike_view view in the database.
 * <br> Used in the GUI to present the bike table to the user.
 * @author Charlie
 * Mar 27, 2015 2:52:15 PM
 */
@NamedQueries({
	@NamedQuery(name = BikeView.QUERY_ALL, query = "from BikeView"),
})
@Entity
@Table(name = "bike_view", catalog = "hibernate_test_database")
public class BikeView {
	public static final String QUERY_ALL = "Query.All";
	
	@Id
	@Column(name = "serial_number")
	private String serialNumber;
	
	@Column(name = "model_name")
	private String modelName;
	
	@Column(name = "brand_name")
	private String brandName;
	
	@Column(name = "basket_id")
	private Integer basketId;
	
	@Column(name = "order_id")
	private Integer orderId;
	
	// default constructor for hibernate
	public BikeView() {
		super();
	}
	
	public BikeView(String serialNumber, String modelName, String brandName, int basketId, int orderId) {
		this.serialNumber = serialNumber;
		this.modelName = modelName;
		this.brandName = brandName;
		this.basketId = basketId;
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "BikeView [serialNumber=" + serialNumber + ", modelName=" + modelName + ", brandName=" + brandName + ", basketId=" + basketId
				+ ", orderId=" + orderId + "]";
	}
}
