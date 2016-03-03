package com.mzherdev.controller;
import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.mzherdev.model.Product;
import com.mzherdev.service.ProductService;

public class ProductEditor extends PropertyEditorSupport {
	
	@Autowired
	private ProductService productService;	

	public ProductEditor(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Product product = productService.getProduct(Integer.parseInt(text));
		setValue(product);
	}
}
