package com.myretail.rest.db.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.myretail.rest.db.entity.Product;

public class ProductDAOImpl implements ProductDAO{

	private static SessionFactory factory;
	private static Session session; 
	
/**
 * Get the Product with the 'id' from the Product table 
 */
	public Product getProductById(String id) {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		session = factory.openSession();
		
		session.beginTransaction();
		Product product = (Product) session.get(Product.class, id);
		session.getTransaction().commit();
		
		session.close();
		factory.close();
				
		return product;
	}
/**
 * Using a category grab all the records with that category from the product table
 */
	public List<Product> getProductsByCategory(String category) {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		session = factory.openSession();

		session.beginTransaction();
		String hql = String.format("FROM Product  WHERE category= '%s'", category);
		Query query = session.createQuery(hql);
		List<Product> products = query.list();
		session.getTransaction().commit();
		
		session.close();
		factory.close();
		
		return products;
	}

}
