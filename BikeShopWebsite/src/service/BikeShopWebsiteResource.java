package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("brands")
public class BikeShopWebsiteResource {
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getAllActivities() {
		System.out.println("Get all activities!");
		
		return "<b>Service up and running</b>";
	}
	
	@POST
	@Path("addbrand")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addActivity() {
		// using POST because client does not know the id of the entity being created
		return Response.notModified().build();
	}
}
