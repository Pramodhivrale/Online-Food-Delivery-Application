package com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Orders;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Long> {
	
	public List<Orders> findByCustomerId(Long userId);
	
	public List<Orders> findByRestaurantId(Long restuantId);

}
