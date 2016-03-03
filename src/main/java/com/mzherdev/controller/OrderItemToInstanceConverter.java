package com.mzherdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.mzherdev.model.OrderItem;
import com.mzherdev.model.Product;
import com.mzherdev.service.ProductService;

public class OrderItemToInstanceConverter implements Converter<String, OrderItem> {

	@Autowired
	ProductService productService;

	@Override
	public OrderItem convert(String str) {
		OrderItem item = new OrderItem();
		String name = str.split(" ")[0];
		String quantity = str.split(" ")[1];
		for (Product p : productService.getAllProducts()) {
			if (name.equals(p.getName())) {
				item.setProduct(p);
			item.setQuantity(Integer.parseInt(quantity));
			System.out.println("convert " + name + " : " + quantity);
			}
		}
		return item;
	}

}
