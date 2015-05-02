package service;

import hibernate.classes.views.BikeView;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import repository.ViewsRepository;
import repository.ViewsRepositoryStub;

@Path("views")
public class ViewsResource {
	private ViewsRepository repository;
	
	public ViewsResource() {
		repository = new ViewsRepositoryStub();
	}
	
	@GET
	@Path("getbikeview")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBikeView() {
		List<BikeView> bikes = repository.readBikeViews();
		
		BikeView entity = bikes.get(0);
		
		System.out.println(bikes);
		
		Response response = Response.ok(entity).build();
		return response;
	}
	
	@GET
	@Path("getbikeviews")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBikeViews() {
		List<BikeView> bikes = repository.readBikeViews();
		
		GenericEntity<List<BikeView>> entity = new GenericEntity<List<BikeView>>(bikes) {};
		
		Response response = Response.ok(entity).build();
		return response;
	}
}
