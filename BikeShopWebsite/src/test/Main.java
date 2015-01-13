package test;

import hibernate.classes.BikeModel;
import hibernate.classes.Brand;
import hibernate.utils.HibernateUtilities;

import org.hibernate.Session;
import org.hibernate.Transaction;



public class Main {
	public static void main(String[] args) {
		//TODO: delete this
        Session session = HibernateUtilities.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        Brand brand = null;
        // insert brands
        for(int i = 1; i <= 10; i++) {
        	brand = new Brand("Brand " + i);
        	session.save(brand);
        }
        
        // insert bike model
        BikeModel bikeModel = new BikeModel("Bike Model 1", "£1000", brand, null);
        session.save(bikeModel);
        tx.commit();
	}
}
