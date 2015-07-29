package test;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.codehaus.jettison.json.JSONException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.myretail.rest.db.entity.Price;
import com.myretail.rest.db.entity.Product;

import static org.hamcrest.Matchers.*;

/**
 * Testing the Rest Service calls
 */

@SuppressWarnings("deprecation")
public class MyRetailServiceTest {
	private static SessionFactory factory;
	
	private static Product product1;
	private static Product product2;
	private static Price price;
	final private String baseUrl = "http://localhost:8080/MyRetailApp/api/data";
	
	
	//Add test records before running unit tests
	@BeforeClass
	public static void before(){

		addTestRecords();
	}
	
	//Remove test records after running unit tests
	@AfterClass
	public static void After()
	{
		deleteTestRecords();
	}
	
	
	@Test 
	public void getPriceByIdTest()
	{
		String path = "/getPriceById/";
		String param = price.getId();
		
		expect()
			.body("id", equalTo(price.getId()))
			.body("price", equalTo(price.getPrice()))
			.when()
			.get(baseUrl + path + param);
		
	}
	
	@Test 
	public void getProductByIdTest()
	{
		String path = "/getProductById/";
		String param = product1.getId();
		
		expect()
			.body("id", equalTo(product1.getId()))
			.body("sku", equalTo(product1.getSku()))
			.body("name", equalTo(product1.getName()))
			.body("category", equalTo(product1.getCategory()))
			.when()
			.get(baseUrl + path + param);
	}
	
	@Test 
	public void getProductsByCategory() throws JSONException
	{
		
		String path = "/getProductsByCategory/";
		String param = "Pets";
		
			expect()
				.body("id", hasItems("1111","2222"))
				.body("sku", hasItems("ABCD", "EFGH"))
				.body("name", hasItems("Dog","Cat"))
				.body("category", hasItems("Pets","Pets"))
				.when()
				.get(baseUrl + path + param);	
	}
	
	/**
	 * Helper Methods
	 */
	
	/**
	 * Add test records to the database
	 */
	public static void addTestRecords()
	{
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = factory.openSession();
		
		//Insert Price
		price = new Price("1111",1.00);
		session.beginTransaction();
		session.save(price);
		session.getTransaction().commit();
		
		//Insert Products 
		product1 = new Product("1111","ABCD","Dog","Pets", java.sql.Date.valueOf("1992-12-10"));
		product2 = new Product("2222","EFGH","Cat","Pets", java.sql.Date.valueOf("1992-12-10"));
		
		//Insert Products
		session.beginTransaction();
		session.save(product1);
		session.getTransaction().commit();
		
		session.beginTransaction();
		session.save(product2);
		session.getTransaction().commit();
		
		session.close();
		factory.close();
	}
	
	/**
	 * Delete the test records from the database
	 */
	
	public static void deleteTestRecords()
	{
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = factory.openSession();
		
		//delete the record not want the record persisted 
		session.beginTransaction();
		session.delete(session.load(Price.class, price.getId()));	
		session.getTransaction().commit();

		
		//delete the record not want the record persisted 
		session.beginTransaction();
		session.delete(session.load(Product.class, product1.getId()));	
		session.delete(session.load(Product.class, product2.getId()));
		session.getTransaction().commit();
						
						
		session.close();
	    factory.close();
	}

}
