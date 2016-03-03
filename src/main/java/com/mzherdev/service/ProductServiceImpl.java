package com.mzherdev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mzherdev.dao.ProductDao;
import com.mzherdev.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;

	@Transactional
	public void add(Product product) {
		productDao.add(product);
	}

	@Transactional
	public void edit(Product product) {
		productDao.edit(product);
	}

	@Transactional
	public void delete(int productId) {
		productDao.delete(productId);
	}

	@Transactional
	public Product getProduct(int productId) {
		return productDao.getProduct(productId);
	}

	@Transactional
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
}

