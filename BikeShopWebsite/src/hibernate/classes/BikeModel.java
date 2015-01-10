package hibernate.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bike_model", catalog = "hibernate_test_database")
public class BikeModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(unique = true, nullable = false)
	private String name;
    
    @Column
	private String price;
	
    @ManyToOne
	private Brand brand;
	
    @Column
	private String image;
}
