package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

import com.myretail.rest.db.dao.PriceDAOImpl;
import com.myretail.rest.db.entity.Price;



/**
 * Test that you can read and write to/from Price table
 */

public class PriceDAOImplTest {
	
	/**
	 * Checks that you can read and write to the Price table in MyRetail database
	 */
	
	private static SessionFactory factory;
	private static Session session;

	@Test 
	public void getPriceByIdTest()
	{
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		session = factory.openSession();
		
		//Insert Price
		Price price = new Price("1111",1.00);
		session.beginTransaction();
		session.save(price);
		session.getTransaction().commit();
		
		session.close();
		factory.close();
		
		//Verify that the price is in the database
		PriceDAOImpl priceMapper = new PriceDAOImpl();
		Price dbPrice = priceMapper.getPriceById(price.getId());
		
		
		assertEquals(price.getId(), dbPrice.getId());
		assertEquals(price.getPrice(), dbPrice.getPrice(), 0.0F);
		
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		session = factory.openSession();
		
		//delete the record not want the record persisted 
		session.beginTransaction();
		session.delete(session.load(Price.class, price.getId()));	
		session.getTransaction().commit();
		
		//Verify that the record is not longer in the database 
		session.beginTransaction();
		Price expectNullPrice = (Price)session.get(Price.class, price.getId());
		assertNull(expectNullPrice);
		
		session.close();
		factory.close();
		
	}
	
	
	@Test 
	public void readAndWriteTest()
	{
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		session = factory.openSession();
		
		//Insert Price
		Price price = new Price("1111",1.00);
		session.beginTransaction();
		session.save(price);
	
		//Verify that the price is in the database
		Price dbPrice = (Price) session.get(Price.class, price.getId());
		assertEquals(price.getId(), dbPrice.getId());
		assertEquals(price.getPrice(), dbPrice.getPrice(), 0.0F);
		
		session.close();
		factory.close();
		
	}
	
}
