package repository;

import hibernate.classes.views.BikeView;
import hibernate.utils.HibernateUtilities;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class ViewsRepositoryStub implements ViewsRepository {
	private Session session;

	public ViewsRepositoryStub() {
		session = HibernateUtilities.getSession();
	}
	
	@Override
	public List<BikeView> readBikeViews() {
		Query namedQuery = session.getNamedQuery(BikeView.QUERY_ALL);
		@SuppressWarnings("unchecked")
		List<BikeView> bikes = namedQuery.list();
		
		System.out.println(bikes.get(0));
		
		return bikes;
	}
}
