package test;

import hibernate.classes.Basket;
import hibernate.classes.Bike;
import hibernate.classes.BikeModel;
import hibernate.classes.Brand;
import hibernate.classes.User;
import hibernate.utils.HibernateUtilities;

import java.util.List;

import org.hibernate.Query;
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
		
		session.clear();
		Query namedQuery = session.getNamedQuery(Brand.QUERY_BY_ID);
		namedQuery.setParameter("id", 10);
		brand = (Brand) namedQuery.uniqueResult();
		System.out.println("Found brand: " + brand.getId());
		
		List<BikeModel> bikeModels = brand.getBikeModels();
		System.out.println("Brand " + brand.getId() + " has " + bikeModels.size() + " bike models");
		
//		tx = session.beginTransaction();
//		session.delete(brand);
//		tx.commit();
		
		tx = session.beginTransaction();
		User user = new User("charliecooper98@gmail.com", "password", "Charlie", "Cooper");
		session.save(user);
		tx.commit();
		
		tx = session.beginTransaction();
		Basket basket = new Basket(user);
		session.save(basket);
		tx.commit();
		
//		tx = session.beginTransaction();
//		session.delete(user);
//		tx.commit();
		
		tx = session.beginTransaction();
		Bike bike = new Bike("1000123", bikeModels.get(0));
		session.save(bike);
		tx.commit();
		
		session.clear();
		tx = session.beginTransaction();
		session.delete(bikeModels.get(0));
		tx.commit();
	}
}
