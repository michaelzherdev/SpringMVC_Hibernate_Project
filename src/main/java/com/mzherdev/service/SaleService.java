package com.mzherdev.service;

import java.util.List;

import com.mzherdev.model.Sale;

public interface SaleService {
	
	public void add(Sale sale);

	public void edit(Sale sale);

	public Sale getSale(int saleId);

	public List<Sale> getAllSales();

}
