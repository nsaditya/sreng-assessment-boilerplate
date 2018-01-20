package com.skubana.assessment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skubana.assessment.pojo.Order;
import com.skubana.assessment.pojo.OrderItem;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
	
}
