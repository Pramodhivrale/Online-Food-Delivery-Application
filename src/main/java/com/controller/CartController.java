package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Cart;
import com.entity.CartItems;
import com.request.AddCartItemRequest;
import com.response.UpdateCartItemReq;
import com.service.CartService;

@RestController
@RequestMapping(("/api/"))
public class CartController {

	@Autowired
	private CartService cartService;

	@PutMapping("/cart/add")
	public ResponseEntity<CartItems> addItemToCart(@RequestBody AddCartItemRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception {

		CartItems itemToCart = cartService.addItemToCart(req, jwt);
		return new ResponseEntity<>(itemToCart, HttpStatus.OK);
	}

	@PutMapping("/cart-item/update")
	public ResponseEntity<CartItems> updateCartItemQuentity(@RequestBody UpdateCartItemReq req,
			@RequestHeader("Authorization") String jwt) throws Exception {

		CartItems updateCardItemQuantity = cartService.updateCardItemQuantity(req.getCartItemId(), req.getQuentity());

		return new ResponseEntity<CartItems>(updateCardItemQuantity, HttpStatus.OK);
	}

	@DeleteMapping("/cart-item/{id}/remove")
	public ResponseEntity<Cart> removeCartItem(@PathVariable Long id, @RequestHeader("Authorization") String jwt)
			throws Exception {

		Cart cart = cartService.removeCartItems(id, jwt);

		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

	@PutMapping("/cart/clear")
	public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String jwt) throws Exception {

		Cart cleraCart = cartService.cleraCart(jwt);
		return new ResponseEntity<Cart>(cleraCart, HttpStatus.OK);
	}

	@GetMapping("/cart")
	public ResponseEntity<Cart> finduserCart(@RequestHeader("Authorization") String jwt) throws Exception {

		Cart cleraCart = cartService.findCartByuserId(jwt);
		return new ResponseEntity<Cart>(cleraCart, HttpStatus.OK);
	}

}
