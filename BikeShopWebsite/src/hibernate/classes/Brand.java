package hibernate.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name=Brand.QUERY_ALL, query="from Brand")
})
@Entity
@Table(name= "brand", catalog = "hibernate_test_database")
public class Brand implements Serializable, Comparable<Brand> {
	private static final long serialVersionUID = 1L;

	public static final String QUERY_ALL = "Brand.All";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;
	
	@Column
	private String name;
	
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
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Brand brand) {
		return name.compareTo(brand.name);
	}
}
