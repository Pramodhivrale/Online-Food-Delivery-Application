package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.CartService;

@RestController
@RequestMapping(("/api/"))
public class CartController {
	
	@Autowired
	private CartService cartService;

}
