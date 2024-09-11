package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.RestuarantDao;
import com.entity.Restaurant;
import com.entity.User;
import com.service.ResturantService;
import com.service.UserService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
	@Autowired
	private ResturantService resturantService;

	@Autowired
	private UserService userService;

	@GetMapping("/search")
	public ResponseEntity<List<Restaurant>> searchRestaurants(@RequestParam String keyword,
			@RequestHeader("Authorization") String jwt) throws Exception {

		User userByJwttoken = userService.findUserByJwttoken(jwt);
		List<Restaurant> searchRestaurant = resturantService.searchRestaurant(keyword);

		return new ResponseEntity<>(searchRestaurant, HttpStatus.OK);
	}

	@GetMapping("getAllRestaurant")
	public ResponseEntity<List<Restaurant>> getAllRestaurant(@RequestHeader("Authorization") String jwt)
			throws Exception {

		List<Restaurant> getallRestaurant = resturantService.getallRestaurant();
		return new ResponseEntity<>(getallRestaurant, HttpStatus.OK);
	}

//	@PostMapping("/{id}/status")
//	public ResponseEntity<Restaurant> updateRestaurantStatus(@RequestHeader("Authorization") String jwt,
//			@PathVariable Long id) throws Exception {
//
//		User userByJwttoken = userService.findUserByJwttoken(jwt);
//		Restaurant restaurant = resturantService.updateRestuarantStatus(id);
//		return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
//
//	}

	@GetMapping("/user")
	public ResponseEntity<Restaurant> findRestaurantByUserId(@RequestHeader("Authorization") String jwt)
			throws Exception {

		User userByJwttoken = userService.findUserByJwttoken(jwt);
		Restaurant resturantById = resturantService.findResturantById(userByJwttoken.getId());
		return new ResponseEntity<Restaurant>(resturantById, HttpStatus.OK);

	}

	@PutMapping("/{id}/add-favorites")
	public ResponseEntity<RestuarantDao> addToFavorites(@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) throws Exception {

		User userByJwttoken = userService.findUserByJwttoken(jwt);
		RestuarantDao favorites = resturantService.addFavorites(id, userByJwttoken);

		return new ResponseEntity<>(favorites, HttpStatus.OK);

	}

}
