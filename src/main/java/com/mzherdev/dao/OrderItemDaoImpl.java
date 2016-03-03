package com.mzherdev.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mzherdev.model.OrderItem;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	
public void add(OrderItem orderItem) {
	sessionFactory.getCurrentSession().save(orderItem);
}
	
	public void edit(OrderItem orderItem) {
		sessionFactory.getCurrentSession().update(orderItem);
	}
		
	public OrderItem getOrderItem(int orderItemId) {
		return (OrderItem) sessionFactory.getCurrentSession().get(OrderItem.class, orderItemId);
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderItem> getAllOrderItems() {
		List<OrderItem> result = (List<OrderItem>) sessionFactory.getCurrentSession().createQuery("from OrderItem").list();
		return result;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
		
}
