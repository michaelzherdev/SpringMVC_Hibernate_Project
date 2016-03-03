package com.mzherdev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mzherdev.dao.SaleDao;
import com.mzherdev.model.Sale;

@Service
public class SaleServiceImpl implements SaleService{
	
	@Autowired
	private SaleDao saleDao;

	@Transactional
	public void add(Sale sale) {
		saleDao.add(sale);
	}

	@Transactional
	public void edit(Sale sale) {
		saleDao.edit(sale);
	}

	@Transactional
	public Sale getSale(int saleId) {
		return saleDao.getSale(saleId);
	}

	@Transactional
	public List<Sale> getAllSales() {
		return saleDao.getAllSales();
	}

	public SaleDao getSaleDao() {
		return saleDao;
	}

	public void setSaleDao(SaleDao saleDao) {
		this.saleDao = saleDao;
	}

}
