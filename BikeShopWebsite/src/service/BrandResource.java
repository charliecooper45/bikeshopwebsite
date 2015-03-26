package service;

import hibernate.classes.Brand;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import repository.BrandRepository;
import repository.BrandRepositoryStub;

@Path("brands")
public class BrandResource {
	private BrandRepository brandRepository;
	
	public BrandResource() {
		brandRepository = new BrandRepositoryStub();
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String checkService() {
		return "<b>Service up and running</b>";
	}
	
	@POST
	@Path("addbrand")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBrand(Brand brand) {
		// using POST because client does not know the id of the entity being created
		brandRepository.create(brand);
		
		return Response.ok().build();
	}
	
	@GET
	@Path("getbrands")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBrands() {
		List<Brand> brands = brandRepository.read();
		
		GenericEntity<List<Brand>> entity = new GenericEntity<List<Brand>>(brands) {};
		
		Response response = Response.ok(entity).build();
		return response;
	}
}
