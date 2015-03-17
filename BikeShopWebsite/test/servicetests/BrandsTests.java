package servicetests;

import javax.ws.rs.core.Response;

import hibernate.classes.Brand;
import junit.framework.Assert;

import org.junit.Test;

import client.TestClient;

/**
 * Validates web service methods for Brands
 * @author Charlie
 * Mar 16, 2015 7:33:25 PM
 */
public class BrandsTests {
	//TODO: must clear all test data for tests, possible an abstract base class to do this?
	
	@Test
	public void testAddBrand() {
		TestClient client = new TestClient();
		
		Brand brand = new Brand("Brand for test method");
		
		Response response = client.testAddBrand(brand);
		
		Assert.assertEquals("Brand should have been added to the database", 200, response.getStatus());
	}
}
