package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class BikeShopWebsiteResource {
	@GET
	@Produces({MediaType.TEXT_HTML})
	public String getAllActivities() {
		System.out.println("Get all activities!");
		
		return "<b>Service up and running</b>";
	}
}
