package com.mzherdev.dao;

import java.util.List;

import com.mzherdev.model.Product;

public interface ProductDao {
		
		public void add(Product product);
		
		public void edit(Product product);
		
		public void delete(int productId);
		
		public Product getProduct(int productId);
		
		public List<Product> getAllProducts();
}
