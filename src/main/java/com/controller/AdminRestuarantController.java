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

import com.entity.Restaurant;
import com.entity.User;
import com.response.MessageResponse;
import com.service.CreateRestuarntRequest;
import com.service.ResturantService;
import com.service.UserService;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestuarantController {
	@Autowired
	private ResturantService resturantService;

	@Autowired
	private UserService userService;

	@PostMapping()
	public ResponseEntity<Restaurant> createRestuarant(@RequestBody CreateRestuarntRequest createRestuarntRequest,
			@RequestHeader("Authorization") String jwt) throws Exception {

		User userByJwttoken = userService.findUserByJwttoken(jwt);

		Restaurant restuarant = resturantService.createRestuarant(createRestuarntRequest, userByJwttoken);

		return new ResponseEntity<Restaurant>(restuarant, HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestuarntRequest req,
			@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
		//User userByJwttoken = userService.findUserByJwttoken(jwt);

		Restaurant updateResturant = resturantService.updateResturant(id, req);

		return new ResponseEntity<Restaurant>(updateResturant, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteRestaruant(@RequestBody CreateRestuarntRequest req,
			@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
		//User userByJwttoken = userService.findUserByJwttoken(jwt);

		resturantService.deleteResturant(id);
		MessageResponse res = new MessageResponse();
		res.setMessage("Restaurant deleted succesfully !");

		return new ResponseEntity<MessageResponse>(res, HttpStatus.OK);
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<Restaurant> updatedRestaurantStatus(@RequestBody CreateRestuarntRequest req,
			@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {

		Restaurant updateRestuarantStatus = resturantService.updateRestuarantStatus(id);

		return new ResponseEntity<Restaurant>(updateRestuarantStatus, HttpStatus.OK);
	}

}
