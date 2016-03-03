package com.mzherdev.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem implements Serializable {
	
	private static final long serialVersionUID = 2532611949971908819L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column
	private int quantity;

	 @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "product_id")  
	private Product product;

	 @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "sale_id")  
	private Sale sale;
	 	 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	@Override
	public String toString() {
		return product.getName() + new BigDecimal(product.getPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue() + "$ * " + quantity;
	}
	
	

}
