package com.skubana.assessment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.skubana.assessment.pojo.Order;
import com.skubana.assessment.pojo.OrderItem;
import com.skubana.assessment.pojo.ProductStock;

@RestController
public class FulfilmentController {
	
	private static final String THREE_PL_ENDPOINT_URL = "http://assessment.skubana.com/products/stocks";
	
	@Autowired
	private ShipmentController shipmentController;
	
	@Async
	public void allocateInventory(Order order) {

		boolean fulfillable = true;
		if (fulfillable) {
			
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
	        Map map = new HashMap<String, String>();
	        map.put("Content-Type", "application/json");

	        headers.setAll(map);
	        
	        
	        List<OrderItem> orderItems = order.getOrderItems();
	        
	        List<ProductStock> productStockList = new ArrayList<ProductStock>();
	        
	        for(OrderItem orderItem : orderItems) {
	        	ProductStock stock = new ProductStock();
	        	stock.setQuantity(orderItem.getQuantity());
	        	stock.setSku(orderItem.getSku());
	        	
	        	productStockList.add(stock);
	        	
	        }

	        Map<String, List<ProductStock>> requestMap = new HashMap();
	        requestMap.put("productStocks", productStockList);
	        HttpEntity<?> request = new HttpEntity<>(requestMap, headers);
	        ResponseEntity<?> response = null;
	        try {
	        	response = new RestTemplate().postForEntity(THREE_PL_ENDPOINT_URL, request, String.class);
	        	HttpStatus status = response.getStatusCode();
		        
		        if (status.is2xxSuccessful()) {
		        	System.out.println("Successfully fulfilled");
		        	shipmentController.createShipment(order);
		        }
		        else {
		        	System.out.println("Order NOT fulfilled");
		        }
	        }
	        catch (Exception ex) {
	        	System.out.println("Order NOT fulfilled");
	        }
	        

		}

	}
}
