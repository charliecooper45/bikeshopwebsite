package hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtilities {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static boolean connectedToDatabase = true;

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

	public synchronized static boolean isConnectedToDatabase() {
		return connectedToDatabase;
	}

	public synchronized static void setConnectedToDatabase(boolean connectedToDatabase) {
		HibernateUtilities.connectedToDatabase = connectedToDatabase;
	}
}
