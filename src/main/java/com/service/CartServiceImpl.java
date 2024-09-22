package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Cart;
import com.entity.CartItems;
import com.entity.Food;
import com.entity.User;
import com.repo.CartItemrepo;
import com.repo.CartRepo;
import com.repo.FoodRepo;
import com.request.AddCartItemRequest;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private CartItemrepo cartItemrepo;

	@Autowired
	private UserService userService;

	@Autowired
	private FoodRepo foodRepo;

	@Autowired
	private Foodservice foodservice;

	@Override
	public CartItems addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
		User user = userService.findUserByJwttoken(jwt);
		Food food = foodservice.findFoodById(req.getFoodId());
		Cart cart = cartRepo.findByCustomerId(user.getId());

		for (CartItems cartItems : cart.getCardItems()) {
			if (cartItems.getFood().equals(food)) {
				int newQuentity = cartItems.getQuentity() + req.getQuentity();
				return updateCardItemQuantity(cartItems.getId(), newQuentity);
			}
		}
		CartItems newcartItem = new CartItems();
		newcartItem.setFood(food);
		newcartItem.setCart(cart);
		newcartItem.setQuentity(req.getQuentity());
		newcartItem.setIngredients(req.getIngredients());
		newcartItem.setTotalPrice(req.getQuentity() * food.getPrice());

		CartItems save = cartItemrepo.save(newcartItem);
		cart.getCardItems().add(save);
		return save;
	}

	@Override
	public CartItems updateCardItemQuantity(Long cardItem, int quentity) throws Exception {
		Optional<CartItems> optionalCartItem = cartItemrepo.findById(cardItem);
		if (optionalCartItem.isEmpty()) {
			throw new Exception("cart item not found !");
		}
		CartItems items = optionalCartItem.get();
		items.setQuentity(quentity);
		items.setTotalPrice(items.getFood().getPrice() * quentity);
		return cartItemrepo.save(items);
	}

	@Override
	public Cart removeCartItems(Long cartItemId, String jwt) throws Exception {

		User user = userService.findUserByJwttoken(jwt);
		Cart cart = cartRepo.findByCustomerId(user.getId());

		Optional<CartItems> byId = cartItemrepo.findById(cartItemId);
		if (byId.isEmpty()) {
			throw new Exception("");
		}
		CartItems cartItems = byId.get();
		cart.getCardItems().remove(cartItems);
		return cartRepo.save(cart);
	}

	@Override
	public Long calculateCartTotals(Cart cart) throws Exception {
		Long total = 0L;

		for (CartItems cartItems : cart.getCardItems()) {
			total += cartItems.getFood().getPrice() * cartItems.getQuentity();
		}
		return total;
	}

	@Override
	public Cart findCartById(Long id) throws Exception {
		Optional<Cart> optionalCart = cartRepo.findById(id);
		if (optionalCart.isEmpty()) {
			throw new Exception("Cart not found with " + id);
		}
		return optionalCart.get();
	}

	@Override
	public Cart findCartByuserId(Long userId) throws Exception {

		return cartRepo.findByCustomerId(userId);
	}

	@Override
	public Cart cleraCart(Long userId) throws Exception {
		Cart byCustomerId = cartRepo.findByCustomerId(userId);
		byCustomerId.getCardItems().clear();

		return cartRepo.save(byCustomerId);
	}

}
