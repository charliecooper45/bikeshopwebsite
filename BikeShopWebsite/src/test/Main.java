package test;

import hibernate.classes.Brand;
import hibernate.utils.HibernateUtilities;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class Main {
	public static void main(String[] args) {
		//TODO: this could be moved over to Maven?
        Session session = HibernateUtilities.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        // insert brands
        for(int i = 1; i <= 10; i++) {
        	Brand brand = new Brand("Brand " + i);
        	session.save(brand);
        }
        tx.commit();
        HibernateUtilities.shutdown();
	}
}
