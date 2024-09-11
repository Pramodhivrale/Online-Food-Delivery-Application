package com.service;

import java.util.List;

import com.entity.Category;
import com.entity.Food;
import com.entity.Restaurant;

public interface Foodservice {
	public Food createFood(CreateFoodReq req, Category category, Restaurant restaurant);

	void deleteFood(Long foodId) throws Exception;

	public List<Food> getRestuarantsFood(Long restuarantId, boolean isvegiterian, boolean nonVeg, boolean isseasonal,
			String foodCategory);
	
	public List<Food> searchFood(String keyword);
	
	public Food findFoodById(Long foodId) throws Exception;
	
	public Food updateAvailabilityStatus(Long foodId) throws Exception;

}
