package com.skubana.assessment;

import com.skubana.assessment.pojo.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class AssessmentController {

	private static final String MARKETPLATE_ENDPOINT_URL = "http://assessment.skubana.com/orders";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private OrderRepository orderRepository;

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
				});

		System.out.println(orderList.size() + " orders downloaded");

		return orderList;
	}

	public void allocateInventory(Order order) {

		boolean fulfillable = true;
		if (fulfillable) {

		}

	}

	public void createShipment(Order order) {

	}

}
