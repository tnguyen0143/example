package com.myretail.rest.db.dao;

import java.util.List;

import com.myretail.rest.db.entity.Product;


public interface ProductDAO {
	
	public Product getProductById(String id);
	
	public List<Product> getProductsByCategory(String category);

}
