package com.mzherdev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mzherdev.dao.OrderItemDao;
import com.mzherdev.model.OrderItem;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	
	@Autowired
	private OrderItemDao orderItemDao;

	@Transactional
	public void add(OrderItem orderItem) {
		orderItemDao.add(orderItem);
	}

	@Transactional
	public void edit(OrderItem orderItem) {
		orderItemDao.edit(orderItem);
	}

	@Transactional
	public OrderItem getOrderItem(int orderItemId) {
		return orderItemDao.getOrderItem(orderItemId);
	}

	@Transactional
	public List<OrderItem> getAllOrderItems() {
		return orderItemDao.getAllOrderItems();
	}

	public OrderItemDao getOrderItemDao() {
		return orderItemDao;
	}

	public void setOrderItemDao(OrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
	}
	
	

}
