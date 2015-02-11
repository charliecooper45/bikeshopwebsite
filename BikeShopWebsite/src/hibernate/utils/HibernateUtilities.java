package hibernate.utils;

import hibernate.classes.Bike;
import hibernate.classes.BikeModel;
import hibernate.classes.Brand;
import hibernate.classes.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import security.BCrypt;

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
		
		// insert bike
		Query namedQuery = session.getNamedQuery(BikeModel.QUERY_BY_NAME);
		namedQuery.setParameter("name", "Bike Model 1");
		BikeModel bikeModel = (BikeModel) namedQuery.uniqueResult();
		Bike bike = new Bike("1002321", bikeModel);
		bikeModel.addBike(bike);
		tx = session.beginTransaction();
		session.save(bike);
		tx.commit();
	}
}
