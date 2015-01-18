package hibernate.utils;

import hibernate.classes.BikeModel;
import hibernate.classes.Brand;
import hibernate.classes.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import security.BCrypt;

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

	public static void createTestData() {
		// TODO: this could be moved over to Maven?
		Session session = HibernateUtilities.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Brand brand = null;
		// insert brands
		for (int i = 1; i <= 10; i++) {
			brand = new Brand("Brand " + i);
			session.save(brand);
		}

		// insert bike model
		for (int i = 1; i <= 10; i++) {
			BikeModel bikeModel = new BikeModel("Bike Model " + i, "£1000", brand, null);
			session.save(bikeModel);
		}
		
		// insert user
		User user = new User("c@gmail.com", BCrypt.hashpw("password", BCrypt.gensalt()), "Charlie", "Cooper");
		session.save(user);
		tx.commit();
	}
}
