package com.myretail.rest.service;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.myretail.rest.db.dao.PriceDAOImpl;
import com.myretail.rest.db.dao.ProductDAOImpl;
import com.myretail.rest.db.entity.Price;
import com.myretail.rest.db.entity.Product;

@Path("/data")
public class MyRetailService {
	
	@GET
	@Path("/getProductById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProductById(@PathParam("id") String id)
	{
		ProductDAOImpl productDAO = new ProductDAOImpl();
		Product product = productDAO.getProductById(id);
		
		if (product == null){
			throw new ErrorNotFoundException("No records found for the id = " + id);
		}
		
		return product;
	}
	
	@GET
	@Path("/getPriceById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Price getPriceById(@PathParam("id")String id)
	{
		PriceDAOImpl priceDAO = new PriceDAOImpl();
		Price price = priceDAO.getPriceById(id);
		
		if (price == null){
			throw new ErrorNotFoundException("No records found for the id = " + id);
		}

		return price;
	}
	
	@GET
	@Path("/getProductsByCategory/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductsByCategory(@PathParam("category") String category)
	{
		ProductDAOImpl productDAO = new ProductDAOImpl();
		List<Product> products = productDAO.getProductsByCategory(category);
		
		if (products.size() == 0){
			throw new ErrorNotFoundException("No records found with the category = " + category);
		}
		
		return products;
	}
}
