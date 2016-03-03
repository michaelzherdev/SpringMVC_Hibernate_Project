package com.mzherdev.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mzherdev.model.Sale;

@Repository
public class SaleDaoImpl implements SaleDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void add(Sale sale) {
		sessionFactory.getCurrentSession().save(sale);
	}

	@Override
	public void edit(Sale sale) {
		sessionFactory.getCurrentSession().update(sale);
	}

	@Override
	public Sale getSale(int saleId) {
		return (Sale) sessionFactory.getCurrentSession().get(Sale.class, saleId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sale> getAllSales() {
		return (List<Sale>) sessionFactory.getCurrentSession().createQuery("from Sale").list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
