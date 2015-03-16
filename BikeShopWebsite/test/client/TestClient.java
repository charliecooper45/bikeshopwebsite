package client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Client for use in tests
 * @author Charlie 
 * Mar 14, 2015 12:59:04 PM
 */
public class TestClient {
	private Client client;
	
	public TestClient() {
		client = ClientBuilder.newClient();
	}

	public int testService() {
		WebTarget target = client.target("http://localhost:8080/BikeShopWebsite/webapi");
		
		Response response = target.path("/").request().get();
		
		return response.getStatus();
	}

	public int testAddBrand() {
		return -1;
	}
}
