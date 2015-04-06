package servicetests;

import hibernate.classes.Brand;
import hibernate.utils.HibernateUtilities;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import client.TestClient;

/**
 * Validates web service methods for Brands
 * @author Charlie
 * Mar 16, 2015 7:33:25 PM
 */
public class BrandsTests {
	private TestClient client;

	//TODO: must clear all test data for tests, possible an abstract base class to do this?
	
	@Before
	public void setup() {
		client = new TestClient();
		HibernateUtilities.deleteBrands();
	}
	
	@Test
	public void testGetBrands() {
		Response response = client.testGetBrands();
		List<Brand> brands = response.readEntity(new GenericType<List<Brand>>() {});
		
		Assert.assertEquals("List of brands should be returned", 200, response.getStatus());
		Assert.assertNotNull("Brands should not be null", brands);
	}
	
	@Test
	public void testAddBrand() {
		Brand brand = new Brand("Brand for test method");
		
		Response response = client.testAddBrand(brand);
		
		Assert.assertEquals("Brand should have been added to the database", 200, response.getStatus());
	}
}
