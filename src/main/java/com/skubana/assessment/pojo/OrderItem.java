package com.skubana.assessment.pojo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="sku_order_item")
public class OrderItem {

	@Id
	@GeneratedValue
	private Long orderItemId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId", nullable = false)
	private Order order;

	private Integer quantity;
	private String sku;

	public Long getOrderItemId() {
		return orderItemId;
	}

	public OrderItem setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
		return this;
	}

	public Order getOrder() {
		return order;
	}

	public OrderItem setOrder(Order order) {
		this.order = order;
		return this;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public OrderItem setQuantity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}

	public String getSku() {
		return sku;
	}

	public OrderItem setSku(String sku) {
		this.sku = sku;
		return this;
	}

}