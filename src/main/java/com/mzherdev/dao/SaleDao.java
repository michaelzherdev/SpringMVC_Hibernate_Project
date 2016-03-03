package com.mzherdev.dao;

import java.util.List;

import com.mzherdev.model.Sale;

public interface SaleDao {
	public void add(Sale sale);

	public void edit(Sale sale);

	public Sale getSale(int saleId);

	public List<Sale> getAllSales();

}
