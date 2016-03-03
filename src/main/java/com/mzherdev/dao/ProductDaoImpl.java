package com.mzherdev.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mzherdev.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void add(Product product) {
		sessionFactory.getCurrentSession().save(product);
	}

	@Override
	public void edit(Product product) {
		sessionFactory.getCurrentSession().update(product);
	}

	@Override
	public void delete(int productId) {
		sessionFactory.getCurrentSession().delete(getProduct(productId));
	}

	@Override
	public Product getProduct(int productId) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, productId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		List<Product> result = (List<Product>) sessionFactory.getCurrentSession().createQuery("from Product").list();
		return result;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

}