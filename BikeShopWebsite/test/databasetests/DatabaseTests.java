package databasetests;

import hibernate.classes.Basket;
import hibernate.classes.Bike;
import hibernate.classes.BikeModel;
import hibernate.classes.BikeShopOrder;
import hibernate.classes.Brand;
import hibernate.classes.Payment;
import hibernate.classes.User;
import hibernate.utils.HibernateUtilities;
import junit.framework.Assert;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests hibernate functionality
 * @author Charlie
 * Mar 31, 2015 5:08:34 PM
 */
public class DatabaseTests {
	
	@Before 
	public void setup() {
		HibernateUtilities.deleteTestData();
	}
	
	@Test
	public void testDeleteData() {
		Session session = HibernateUtilities.getSession();
		
		HibernateUtilities.createTestData();
		
		HibernateUtilities.deleteTestData();
		
		Query namedQuery = session.getNamedQuery(Brand.QUERY_ALL);
		Assert.assertEquals("Brand table should be empty", 0, namedQuery.list().size());

		namedQuery = session.getNamedQuery(Bike.QUERY_ALL);
		Assert.assertEquals("Bike table should be empty", 0, namedQuery.list().size());
		
		namedQuery = session.getNamedQuery(BikeModel.QUERY_ALL);
		Assert.assertEquals("BikeModel table should be empty", 0, namedQuery.list().size());
		
		namedQuery = session.getNamedQuery(User.QUERY_ALL);
		Assert.assertEquals("User table should be empty", 0, namedQuery.list().size());
		
		namedQuery = session.getNamedQuery(Basket.QUERY_ALL);
		Assert.assertEquals("Basket table should be empty", 0, namedQuery.list().size());
		
		namedQuery = session.getNamedQuery(Payment.QUERY_ALL);
		Assert.assertEquals("Payment table should be empty", 0, namedQuery.list().size());
		
		namedQuery = session.getNamedQuery(BikeShopOrder.QUERY_ALL);
		Assert.assertEquals("BikeShopOrder table should be empty", 0, namedQuery.list().size());
	}
}
