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

import com.entity.Orders;
import com.entity.User;
import com.service.OrderService;
import com.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@GetMapping("/order/restaurant/{id}")
	public ResponseEntity<List<Orders>> getOrderHistory(@RequestHeader("Authorization") String jwt,
			@PathVariable Long id, @RequestParam(required = false) String orderStatus) throws Exception {

		List<Orders> restuarantsOrder = orderService.getRestuarantsOrder(id, orderStatus);
		return new ResponseEntity<List<Orders>>(restuarantsOrder, HttpStatus.OK);
	}
	
	@PutMapping("/order/{id}/{orderStatus}")
	public ResponseEntity<Orders> updateOrderStatus(
			@PathVariable Long id,
			@PathVariable String orderStatus
			) throws Exception{
		User user = userService.findUserByJwttoken(orderStatus);
		Orders updateOrder = orderService.updateOrder(id, orderStatus);
		return new ResponseEntity<Orders>(updateOrder,HttpStatus.OK);
				
	}
}
