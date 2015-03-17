package client;

import hibernate.classes.Brand;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Client for use in tests
 * @author Charlie 
 * Mar 14, 2015 12:59:04 PM
 */
public class TestClient {
	private Client client;
	private WebTarget target;
	
	
	public TestClient() {
		client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080/BikeShopWebsite/webapi");
	}

	public Response testService() {
		Response response = target.path("/").request().get();
		
		return response;
	}

	public Response testAddBrand(Brand brand) {
		Response response = target.path("brands/addbrand").request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(brand, MediaType.APPLICATION_JSON));
		
		return response;
	}
}
