package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.CartItems;

@Repository
public interface CartItemrepo extends JpaRepository<CartItems, Long> {

}
