package servicetests;

import junit.framework.Assert;

import org.junit.Test;

import client.TestClient;

/*
 * Validates the web service provided by the bikeshopwebsite
 * @author Charlie 
 * Mar 14, 2015 12:56:58 PM
 */
public class WebServiceTest {
	@Test
	public void testService() {
		TestClient client = new TestClient();
		
		int response = client.testService().getStatus();
		
		Assert.assertEquals("Response code should be 200", 200, response);
	}
}
