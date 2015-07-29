package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

import com.myretail.rest.db.dao.ProductDAOImpl;
import com.myretail.rest.db.entity.Product;

/**
 * Test that you can read and write to/from Product table
 */
@SuppressWarnings("deprecation")
public class ProductDaoImplTest {
	
	private static SessionFactory factory;
	private static Session session;

	@Test 
	public void getProductByIdTest()
	{
		
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		session = factory.openSession();
		
		//Insert Product
		Product product = new Product("1111","ABCD","Dog","Pets", java.sql.Date.valueOf("1992-12-10"));
		session.beginTransaction();
		session.save(product);
		session.getTransaction().commit();
		
		session.close();
		factory.close();
		
		//Verify that the product is in the database
		ProductDAOImpl productMapper = new ProductDAOImpl();
		Product dbProduct = productMapper.getProductById(product.getId());
		assertEquals(product,dbProduct);
	
		
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		session = factory.openSession();
		
		//delete the record not want the record persisted 
		session.beginTransaction();
		session.delete(session.load(Product.class, product.getId()));	
		session.getTransaction().commit();
		
		//Verify that the record is not longer in the database 
		session.beginTransaction();
		Product expectNullProduct = (Product)session.get(Product.class, product.getId());
		assertNull(expectNullProduct);
		
		session.close();
		factory.close();
		
	}
	
	
	@Test 
	public void readAndWriteTest()
	{
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		session = factory.openSession();
		
//		//Insert Product
		Product product = new Product("1111","ABCD","Dog","Pets", java.sql.Date.valueOf("1992-12-10"));
		session.beginTransaction();
		session.save(product);
	
		//Verify that the product is in the database
		Product dbProduct = (Product) session.get(Product.class, product.getId());
		assertEquals(product, dbProduct);
		
		session.close();
		factory.close();
	}
	
	@Test
	public void getProductsByCategoryTest()
	{
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		session = factory.openSession();
		
		List<Product> products = new ArrayList<Product>();
		Product product1 = new Product("1111","ABCD","Dog","Pets", java.sql.Date.valueOf("1992-12-10"));
		Product product2 = new Product("2222","EFGH","Cat","Pets", java.sql.Date.valueOf("1992-12-10"));
		products.add(product1);
		products.add(product2);
		
		//Insert Products
		session.beginTransaction();
		session.save(product1);
		session.save(product2);
		session.getTransaction().commit();
		
		session.close();
		factory.close();
		
		//Verify that the product is in the database
		ProductDAOImpl productMapper = new ProductDAOImpl();
		List<Product> dbProducts = productMapper.getProductsByCategory(product1.getCategory());
		assertEquals(dbProducts.size(),2);
		
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		session = factory.openSession();
		
		//delete the record not want the record persisted 
		session.beginTransaction();
		session.delete(session.load(Product.class, product1.getId()));	
		session.delete(session.load(Product.class, product2.getId()));
		session.getTransaction().commit();
				
		//Verify that the record is not longer in the database 
		session.beginTransaction();
		Product expectNullProduct1 = (Product)session.get(Product.class, product1.getId());
		Product expectNullProduct2 = (Product)session.get(Product.class, product2.getId());
		
		assertNull(expectNullProduct1);
		assertNull(expectNullProduct2);
				
		session.close();
		factory.close();
	}	

}
