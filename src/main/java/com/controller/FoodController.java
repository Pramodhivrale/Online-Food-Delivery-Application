package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Food;
import com.entity.User;
import com.service.Foodservice;
import com.service.ResturantService;
import com.service.UserService;

@RestController
@RequestMapping("/api/food")
public class FoodController {
	@Autowired
	private Foodservice foodservice;

	@Autowired
	private UserService userService;

	@Autowired
	private ResturantService resturantService;

	@GetMapping("/searchFood")
	public ResponseEntity<List<Food>> searchfood(@RequestParam String name, @RequestHeader("Authorization") String jwt)
			throws Exception {

		User userByJwttoken = userService.findUserByJwttoken(jwt);
		List<Food> searchFood = foodservice.searchFood(name);
		return new ResponseEntity<>(searchFood, HttpStatus.CREATED);

	}

	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<List<Food>> getRestuarantFood(@RequestParam boolean veg, @RequestParam boolean nonVeg,
			@RequestParam boolean seasonal, @RequestParam String food_Category, @PathVariable Long restaurantId,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User userByJwttoken = userService.findUserByJwttoken(jwt);
		List<Food> restuarantsFood = foodservice.getRestuarantsFood(restaurantId, veg, nonVeg, seasonal, food_Category);

		return new ResponseEntity<>(restuarantsFood, HttpStatus.OK);

	}

}
