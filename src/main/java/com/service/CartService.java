package com.service;

import com.entity.Cart;
import com.entity.CartItems;
import com.request.AddCartItemRequest;

public interface CartService 
{
	public CartItems addItemToCart(AddCartItemRequest req,String jwt)throws Exception;

	public CartItems updateCardItemQuantity(Long cardItem,int quentiry) throws Exception;
	
	public Cart removeCartItems(Long cartItemId,String jwt)throws Exception;
	
	public Long calculateCartTotals(Cart cart)throws Exception;
	
	public Cart findCartById(Long id) throws Exception;
	
	public Cart findCartByuserId(Long userId)throws Exception;
	
	public Cart cleraCart(Long userId)throws Exception;
}
