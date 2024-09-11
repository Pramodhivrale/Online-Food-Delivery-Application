package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Category;
import com.entity.Food;
import com.entity.Restaurant;
import com.repo.FoodRepo;

@Service
public class FoodServiceImplementation implements Foodservice {

	@Autowired
	private FoodRepo foodrepo;

	@Override
	public Food createFood(CreateFoodReq req, Category category, Restaurant restaurant) {
		Food food = new Food();
		food.setFoodCategory(category);
		food.setResturant(restaurant);
		food.setDescription(req.getDescription());
		food.setImages(req.getImages());
		food.setName(req.getName());
		food.setPrice(req.getPrice());
		food.setIngredientsItems(req.getIngredientsItems());
		food.setIsanSeasonal(req.isSeasional());
		food.setIsanVegiterain(req.isVegetarian());

		Food save = foodrepo.save(food);
		restaurant.getFoods().add(save);
		return save;

	}

	@Override
	public void deleteFood(Long foodId) throws Exception {

		Food foodById = findFoodById(foodId);
		foodById.setResturant(null);
		foodrepo.save(foodById);
	}

	@Override
	public List<Food> getRestuarantsFood(Long restuarantId, boolean isvegiterian, boolean nonVeg, boolean isseasonal,
			String foodCategory) {

		List<Food> foods = foodrepo.findByResturantId(restuarantId);

		if (isvegiterian) {
			filterByVegetarian(foods, isvegiterian);
		}
		if (nonVeg) {
			filterByNonVeg(foods, nonVeg);
		}
		if (isseasonal) {
			filterBySesasonal(foods, isseasonal);
		}
		if (foodCategory != null && !"".equals(foodCategory)) {
			filterByCategory(foods, foodCategory);
		}

		return foods;
	}

	private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
		return foods.stream().filter(food -> food.getFoodCategory().getName().equals(foodCategory))
				.collect(Collectors.toList());

	}

	private void filterBySesasonal(List<Food> foods, boolean isseasonal) {
		foods.stream().filter(food -> food.isIsanSeasonal() == isseasonal).collect(Collectors.toList());

	}

	private List<Food> filterByNonVeg(List<Food> foods, boolean nonVeg) {
		return foods.stream().filter(food -> food.isIsanVegiterain() == false).collect(Collectors.toList());

	}

	private List<Food> filterByVegetarian(List<Food> foods, boolean isvegiterian) {
		return foods.stream().filter(find -> find.isIsanVegiterain() == isvegiterian).collect(Collectors.toList());

	}

	@Override
	public List<Food> searchFood(String keyword) {
		return foodrepo.searchFood(keyword);
	}

	@Override
	public Food findFoodById(Long foodId) throws Exception {
		Optional<Food> byId = foodrepo.findById(foodId);
		if (byId.isEmpty()) {
			throw new Exception("Food is not exists");
		}
		return byId.get();
	}

	@Override
	public Food updateAvailabilityStatus(Long foodId) throws Exception {
		Optional<Food> byId = foodrepo.findById(foodId);
		if (byId.isEmpty()) {
			throw new Exception("Food is not exists");
		}
		Food food = byId.get();
		food.setAvailable(!food.isAvailable());
		return foodrepo.save(food);

	}

}
