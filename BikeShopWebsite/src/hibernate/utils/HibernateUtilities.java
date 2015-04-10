package hibernate.utils;

import hibernate.classes.Basket;
import hibernate.classes.Bike;
import hibernate.classes.BikeModel;
import hibernate.classes.BikeShopOrder;
import hibernate.classes.Brand;
import hibernate.classes.Payment;
import hibernate.classes.User;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//TODO: update to latest stable build of hibernate
/**
 * Provides common utility hibernate functions.
 * @author Charlie
 * Apr 10, 2015 7:08:07 PM
 */
public class HibernateUtilities {
	private static final SessionFactory SESSION_FACTORY = buildSessionFactory();
	private static final Session SESSION = SESSION_FACTORY.openSession();

	private static SessionFactory buildSessionFactory() {
		try {
			// Use hibernate.cfg.xml to get a SessionFactory
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static Session getSession() {
		return SESSION;
	}

	public static void shutdown() {
		SESSION.close();
		SESSION_FACTORY.close();
	}

	public static void deleteBrands() {
		Transaction tx = SESSION.beginTransaction();
		Query deleteQuery = SESSION.createQuery("delete from Brand");
		deleteQuery.executeUpdate();
		tx.commit();
	}

	public static void deleteUsers() {
		Transaction tx = SESSION.beginTransaction();
		Query deleteQuery = SESSION.createQuery("delete from User");
		deleteQuery.executeUpdate();
		tx.commit();
	}

	public static void deleteBikes() {
		Transaction tx = SESSION.beginTransaction();
		Query deleteQuery = SESSION.createQuery("delete from Bike");
		deleteQuery.executeUpdate();
		tx.commit();
	}

	public static void createTestData() {
		SESSION.clear();
		Transaction tx = SESSION.beginTransaction();
		// brand
		Brand brand = new Brand("TestBrand");
		SESSION.save(brand);
		
		// bike model
		BikeModel bikeModel = new BikeModel("TestBikeModel", "£1500", brand, null);
		SESSION.save(bikeModel);
		
		// bike
		Bike bike = new Bike("100000001", bikeModel);
		SESSION.save(bike);
		
		// user
		User user = new User("test@test.com", "q33efrdsa", "Test", "User");
		SESSION.save(user);

		// basket
		Basket basket = new Basket(user);
		SESSION.save(basket);
		
		// payment
		Payment payment = new Payment("8734 2342 1231 1232", new Date(), user, true);
		SESSION.save(payment);
		
		// order
		Set<Bike> bikes = new HashSet<>();
		bikes.add(bike);
		BikeShopOrder order = new BikeShopOrder(user, bikes, payment);
		SESSION.save(order);
		
		tx.commit();
	}

	public static void deleteTestData() {
		SESSION.clear();
		Transaction tx = SESSION.beginTransaction();
		
		// delete all Brands - cascades to BikeModels and Bikes
		Query namedQuery = SESSION.getNamedQuery(Brand.QUERY_ALL);
		@SuppressWarnings("unchecked")
		List<Brand> brands = namedQuery.list();
		
		for (Brand brand : brands) {
			SESSION.delete(brand);
		}
		
		// delete all Users - cascade to Baskets, Payments, Orders
		namedQuery = SESSION.getNamedQuery(User.QUERY_ALL);
		@SuppressWarnings("unchecked")
		List<User> users = namedQuery.list();
		
		for (User user : users) {
			SESSION.delete(user);
		}
		
		tx.commit();
	}
}
