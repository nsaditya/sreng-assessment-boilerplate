package com.skubana.assessment.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="sku_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<OrderItem> orderItems = null;

	private String orderNumber;

	public Long getOrderId() {
		return orderId;
	}

	public Order setOrderId(Long orderId) {
		this.orderId = orderId;
		return this;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public Order setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
		return this;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public Order setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
		return this;
	}

}