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
public class HibernateUtilities {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Use hibernate.cfg.xml to get a SessionFactory
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

	public static void deleteBrands() {
		Session session = HibernateUtilities.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query deleteQuery = session.createQuery("delete from Brand");
		deleteQuery.executeUpdate();
		tx.commit();
	}

	public static void deleteUsers() {
		Session session = HibernateUtilities.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query deleteQuery = session.createQuery("delete from User");
		deleteQuery.executeUpdate();
		tx.commit();
	}

	public static void deleteBikes() {
		Session session = HibernateUtilities.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query deleteQuery = session.createQuery("delete from Bike");
		deleteQuery.executeUpdate();
		tx.commit();
	}

	public static void createTestData() {
		// TODO: this could be moved over to Maven?
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		// brand
		Brand brand = new Brand("TestBrand");
		session.save(brand);
		
		// bike model
		BikeModel bikeModel = new BikeModel("TestBikeModel", "£1500", brand, null);
		session.save(bikeModel);
		
		// bike
		Bike bike = new Bike("100000001", bikeModel);
		session.save(bike);
		
		// user
		User user = new User("test@test.com", "q33efrdsa", "Test", "User");
		session.save(user);

		// basket
		Basket basket = new Basket(user);
		session.save(basket);
		
		// payment
		Payment payment = new Payment("8734 2342 1231 1232", new Date(), user, true);
		session.save(payment);
		
		// order
		Set<Bike> bikes = new HashSet<>();
		bikes.add(bike);
		BikeShopOrder order = new BikeShopOrder(user, bikes, payment);
		session.save(order);
		
		tx.commit();
		session.close();
	}

	public static void deleteTestData() {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		// delete all Brands - cascades to BikeModels and Bikes
		Query namedQuery = session.getNamedQuery(Brand.QUERY_ALL);
		@SuppressWarnings("unchecked")
		List<Brand> brands = namedQuery.list();
		
		for (Brand brand : brands) {
			session.delete(brand);
		}
		
		// delete all Users - cascade to Baskets, Payments, Orders
		namedQuery = session.getNamedQuery(User.QUERY_ALL);
		@SuppressWarnings("unchecked")
		List<User> users = namedQuery.list();
		
		for (User user : users) {
			session.delete(user);
		}
		
		tx.commit();
		session.close();
	}
}
