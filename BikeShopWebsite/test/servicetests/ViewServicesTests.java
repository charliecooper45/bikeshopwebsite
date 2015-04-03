package servicetests;

import hibernate.utils.HibernateUtilities;

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
		System.out.println("pause here");
	}
}
