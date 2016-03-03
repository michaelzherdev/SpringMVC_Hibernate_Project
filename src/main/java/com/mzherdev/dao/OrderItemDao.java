package com.mzherdev.dao;

import java.util.List;

import com.mzherdev.model.OrderItem;;

public interface OrderItemDao {

	public void add(OrderItem orderItem);
	
	public void edit(OrderItem orderItem);
	
	public OrderItem getOrderItem(int orderItemId);
	
	public List<OrderItem> getAllOrderItems();
}
