package repository;

import hibernate.classes.Brand;

import java.util.List;

public interface BrandRepository {
	//TODO: add super interface for Repository
	
	void create(Brand brand);
	
	List<Brand> read();
}
