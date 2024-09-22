package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Cart;


@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
	
	public Cart findByCustomerId(Long userId);

}
