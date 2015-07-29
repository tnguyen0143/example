package com.myretail.rest.db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.myretail.rest.db.entity.Price;
 
public class PriceDAOImpl implements PriceDAO {
	
	private static SessionFactory factory;
	private static Session session; 
	
	
	/**
	 *   Get the Price with the 'id' from the Price table 
	 */
	public Price getPriceById(String id) {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		session = factory.openSession();
		session.beginTransaction();
		
		Price price= (Price) session.get(Price.class, id);
		session.close();
		
		return price;
	}

	

}
