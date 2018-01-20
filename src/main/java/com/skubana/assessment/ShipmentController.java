package com.skubana.assessment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.skubana.assessment.pojo.Order;

@RestController
public class ShipmentController {
	
	private static final String SHIPMENT_ENDPOINT_URL = "http://assessment.skubana.com/shipments";


	public void createShipment(Order order) {

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		Map map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");

		headers.setAll(map);
		Map<String, String> requestMap = new HashMap();
		requestMap.put("orderNumber", order.getOrderNumber());
		HttpEntity<?> request = new HttpEntity<>(requestMap, headers);
		ResponseEntity<?> response = null;
		try {
			response = new RestTemplate().postForEntity(SHIPMENT_ENDPOINT_URL, request, String.class);
			HttpStatus status = response.getStatusCode();
			if (status.is2xxSuccessful()) {
				System.out.println("Created Shipment");
			}
			else {
				System.out.println("Shipment Failed");

			}
		}
		catch (Exception ex) {
			System.out.println("Shipment Failed");
		}

	}

}
