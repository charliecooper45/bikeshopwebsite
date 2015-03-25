package repository;

import java.util.List;

import hibernate.classes.Brand;
import hibernate.utils.HibernateUtilities;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BrandRepositoryStub implements BrandRepository {
	private Session session;

	public BrandRepositoryStub() {
		session = HibernateUtilities.getSessionFactory().openSession();
	}
	
	@Override
	public void create(Brand brand) {
		Transaction tx = session.beginTransaction();
		session.save(brand);
		tx.commit();
	}

	@Override
	public List<Brand> read() {
		Query namedQuery = session.getNamedQuery(Brand.QUERY_ALL);
		@SuppressWarnings("unchecked")
		List<Brand> list = namedQuery.list();
		return list;
	}

}
