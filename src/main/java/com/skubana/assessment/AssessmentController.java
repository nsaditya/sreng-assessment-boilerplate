package com.skubana.assessment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.skubana.assessment.pojo.Order;

@RestController
public class AssessmentController {

	private static final String MARKETPLATE_ENDPOINT_URL = "http://assessment.skubana.com/orders";
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private FulfilmentController fulfilmentController;

	@Scheduled(fixedDelay = 10000)
	public List<Order> getOrders() {
		List<Order> orderList = restTemplate.exchange(MARKETPLATE_ENDPOINT_URL,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Order>>() {
				})
				.getBody();

		// set orderItem->order reference then save
		orderList.stream()
				.forEach(order -> {
					order.getOrderItems().stream()
							.forEach(orderItem -> orderItem.setOrder(order));
					orderRepository.save(order);
					fulfilmentController.allocateInventory(order);
				});

		System.out.println(orderList.size() + " orders downloaded");

		return orderList;
	}

	

	

}
