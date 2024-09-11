package com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Restaurant;

@Repository
public interface RestuarantRepo extends JpaRepository<Restaurant, Long> {

//	@Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE lower(concat('%',:query,'%')) OR lower(r.cuisineType) "
//			+ "LIKE lower(concat('%',:query,'%')) ")
//	List<Restaurant> findBySearchQuery(String query);

	@Query("SELECT r FROM Restaurant r WHERE lower(r.resturantName) LIKE lower(concat('%',:query,'%')) OR lower(r.cuisineType) "
			+ "LIKE lower(concat('%',:query,'%')) ")
	List<Restaurant> findBySearchQuery(@Param("query") String query);

	Restaurant findByOwenerId(Long userId);

}
