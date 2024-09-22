package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Food;
import com.entity.Restaurant;
import com.entity.User;
import com.response.MessageResponse;
import com.service.CreateFoodReq;
import com.service.Foodservice;
import com.service.ResturantService;
import com.service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

	@Autowired
	private Foodservice foodservice;

	@Autowired
	private UserService userService;

	@Autowired
	private ResturantService resturantService;

	@PostMapping("/crateFood")
	public ResponseEntity<Food> createFood(@RequestBody CreateFoodReq req, @RequestHeader("Authorization") String jwt) throws Exception {

		User userByJwttoken = userService.findUserByJwttoken(jwt);
		Restaurant resturantById = resturantService.findResturantById(req.getRestuarantId());
		Food food = foodservice.createFood(req, req.getCategory(), resturantById);
		
		return new ResponseEntity<Food>(food,HttpStatus.CREATED);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
			@RequestHeader("Authorization") String jwt
			) throws Exception{
		
		User userByJwttoken = userService.findUserByJwttoken(jwt);
		
		foodservice.deleteFood(id);
		MessageResponse messageResponse=new MessageResponse();
		messageResponse.setMessage("Food deleted succesfully");
		return new ResponseEntity<MessageResponse>(messageResponse,HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Food> updateFoodavailablityStatus(@PathVariable Long id,@RequestHeader("Authorization") String jwt)throws Exception{
		User userByJwttoken = userService.findUserByJwttoken(jwt);
		Food food = foodservice.updateAvailabilityStatus(id);
		return new  ResponseEntity<Food>(food,HttpStatus.CREATED);
		
	}
}
