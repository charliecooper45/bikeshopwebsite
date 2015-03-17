package repository;

import hibernate.classes.Brand;
import hibernate.utils.HibernateUtilities;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BrandRepositoryStub implements BrandRepository {
	private Session session;

	public BrandRepositoryStub() {
		session = HibernateUtilities.getSessionFactory().openSession();
	}
	
	public void create(Brand brand) {
		Transaction tx = session.beginTransaction();
		session.save(brand);
		tx.commit();
	}

}
