package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Orders;
import com.entity.User;
import com.request.OrderRequest;
import com.service.OrderService;
import com.service.UserService;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@PostMapping("/order")
	public ResponseEntity<Orders> createOrder(@RequestBody OrderRequest orderRequest,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User userByJwttoken = userService.findUserByJwttoken(jwt);
		Orders order = orderService.createOrder(orderRequest, userByJwttoken);

		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@GetMapping("/order/user")
	public ResponseEntity<List<Orders>> getAllOrders(@RequestHeader("Authorization") String jwt) throws Exception {

		User userByJwttoken = userService.findUserByJwttoken(jwt);
		List<Orders> usersOrder = orderService.getUsersOrder(userByJwttoken.getId());

		return new ResponseEntity<List<Orders>>(usersOrder, HttpStatus.OK);
	}
	
	

}
