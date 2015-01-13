package hibernate.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name=BikeModel.QUERY_BY_BRAND_ID, query="from BikeModel bm where bm.brand.id = :brandId"),
})
@Entity
//TODO: change database name before production
@Table(name = "bike_model", catalog = "hibernate_test_database")
public class BikeModel implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String QUERY_BY_BRAND_ID = "Bike.By.Brand.Id";

	@Id
	@Column(unique = true, nullable = false)
	private String name;

	@Column
	private String price;

	@ManyToOne
	private Brand brand;

	@Column
	private String image;

	// default constructor for hibernate
	public BikeModel() {
		super();
	}

	public BikeModel(String name, String price, Brand brand, String image) {
		this.name = name;
		this.price = price;
		this.brand = brand;
		this.image = image;
	}

}
