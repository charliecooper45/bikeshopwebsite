package hibernate.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name=Brand.QUERY_ALL, query="from Brand"),
	@NamedQuery(name=Brand.QUERY_BY_ID, query="from Brand where id = :id")
})
@Entity
@Table(name= "brand", catalog = "hibernate_test_database")
public class Brand implements Serializable, Comparable<Brand> {
	private static final long serialVersionUID = 1L;

	public static final String QUERY_ALL = "Brand.All";
	public static final String QUERY_BY_ID = "Brand.By.Id";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;
	
	@Column
	private String name;
	
	@OneToMany(mappedBy = "brand")
	private List<BikeModel> bikeModels = new ArrayList<>(0);
	
	// default constructor for hibernate
	public Brand() {
		super();
	}
	
	public Brand(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<BikeModel> getBikeModels() {
		return bikeModels;
	}
	
	public void setBikeModels(List<BikeModel> bikeModels) {
		this.bikeModels = bikeModels;
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Brand brand) {
		return name.compareTo(brand.name);
	}
}
