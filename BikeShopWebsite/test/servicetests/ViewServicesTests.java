package servicetests;

import hibernate.classes.views.BikeView;
import hibernate.utils.HibernateUtilities;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.TestClient;

/**
 * Tests the services that provide the data displayed in the views on the client
 * @author Charlie
 * Mar 31, 2015 3:43:48 PM
 */
public class ViewServicesTests {
	private TestClient client;
	
	@Before
	public void setup() {
		
		client = new TestClient();
		
		// add tests data to the database
		HibernateUtilities.createTestData();
	}
	
	@After
	public void tearDown() {
		// removes all data from all tables
		HibernateUtilities.deleteTestData();
	}
	
	@Test
	public void testBikeView() {
		Response response = client.testGetBikeViews();	
		
		List<BikeView> bikes = response.readEntity(new GenericType<List<BikeView>>() {});
		
		Assert.assertEquals("Response from server should be OK", 200, response.getStatus());
		Assert.assertNotNull("Bikes should not be null", bikes);
		Assert.assertEquals("Bikes should have one bike", 1, bikes.size());
	}
}
