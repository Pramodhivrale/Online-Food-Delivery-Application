package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.OrderItems;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItems, Long> {

}
