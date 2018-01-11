package com.skubana.assessment.pojo;

import java.util.List;

public class Inventory {
	private List<ProductStock> productStocks = null;

	public List<ProductStock> getProductStocks() {
		return productStocks;
	}

	public void setProductStocks(List<ProductStock> productStocks) {
		this.productStocks = productStocks;
	}
}
