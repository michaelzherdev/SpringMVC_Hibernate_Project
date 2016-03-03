package com.mzherdev.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale implements Serializable {

	private static final long serialVersionUID = 5789981007587667953L;

	public Sale() {
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(insertable = false, updatable = false)
	private Timestamp date;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sale")
	private List<OrderItem> items = new ArrayList<OrderItem>();

	@Column
	private double cost;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public double getCost() {
	return new BigDecimal(cost).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public int getItemsSize() {
		return items.size();
	}	
}
