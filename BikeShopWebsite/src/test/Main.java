package test;

import hibernate.classes.BikeModel;
import hibernate.classes.Brand;
import hibernate.utils.HibernateUtilities;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
	
	public static void main(String[] args) {
		// TODO: delete this
		Session session = HibernateUtilities.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Brand brand = null;
		// insert brands
		for (int i = 1; i <= 10; i++) {
			brand = new Brand("Brand " + i);
			session.save(brand);
		}

		// insert bike models
		for (int i = 1; i <= 10; i++) {
			BikeModel bikeModel = new BikeModel("Bike Model " + i, "£1000", brand, null);
			session.save(bikeModel);
		}
		tx.commit();
		
//		session.clear();
//		Query namedQuery = session.getNamedQuery(Brand.QUERY_BY_ID);
//		namedQuery.setParameter("id", 10);
//		brand = (Brand) namedQuery.uniqueResult();
//		System.out.println("Found brand: " + brand.getId());
//		
//		List<BikeModel> bikeModels = brand.getBikeModels();
//		System.out.println("Brand " + brand.getId() + " has " + bikeModels.size() + " bike models");
	}
}
