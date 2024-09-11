package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Food;
import java.util.List;


@Repository
public interface FoodRepo extends JpaRepository<Food, Long>{

	List<Food> findByResturantId(Long restaurantId);
	
	
	@Query("SELECT f FROM Food f WHERE f.name LIKE CONCAT('%', :keyword, '%') OR f.foodCategory.name LIKE CONCAT('%', :keyword, '%')")
	List<Food> searchFood(@Param("keyword") String keyword);
}
