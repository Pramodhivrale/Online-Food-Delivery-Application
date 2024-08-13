package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Cart;

@Repository
public interface CardRepo extends JpaRepository<Cart, Long>{

}
