package com.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Address;
import com.entity.Cart;
import com.entity.CartItems;
import com.entity.OrderItems;
import com.entity.Orders;
import com.entity.Restaurant;
import com.entity.User;
import com.repo.Addressrepo;
import com.repo.OrderItemRepo;
import com.repo.OrderRepo;
import com.repo.UserRepository;
import com.request.OrderRequest;

@Service
public class OrderSeriveImpl implements OrderService {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private OrderItemRepo orderItemRepo;

	@Autowired
	private Addressrepo addressrepo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ResturantService resturantService;

	@Autowired
	private CartService cartService;

	@Override
	public Orders createOrder(OrderRequest order, User user) throws Exception {
		Address deliveryAddress = order.getDeliveryAddress();
		Address address = addressrepo.save(deliveryAddress);
		if (!user.getAddress().contains(address)) {
			user.getAddress().add(address);
			userRepository.save(user);
		}

		Restaurant resturantById = resturantService.findResturantById(order.getRestaurantId());
		Orders createdOrder = new Orders();
		createdOrder.setCustomer(user);
		createdOrder.setCreatedAt(new Date(0));
		createdOrder.setOrderStatus("PENDING");
		createdOrder.setDeliveryAddress(deliveryAddress);
		createdOrder.setRestaurants(resturantById);

		Cart cart = cartService.findCartByuserId(user.getId());
		List<OrderItems> orderItems = new ArrayList<>();

		for (CartItems cartItems : cart.getCardItems()) {
			OrderItems orderItem = new OrderItems();
			orderItem.setFood(cartItems.getFood());
			orderItem.setIngredients(cartItems.getIngredients());
			orderItem.setQuentity(cartItems.getQuentity());
			orderItem.setTotalPrice(cartItems.getTotalPrice());

			OrderItems save = orderItemRepo.save(orderItem);
			orderItems.add(orderItem);

		}
		Long totalPrice = cartService.calculateCartTotals(cart);

		createdOrder.setItems(orderItems);
		createdOrder.setTotalPrice(totalPrice);
		Orders savedOrder = orderRepo.save(createdOrder);
		resturantById.getOrders().add(savedOrder);
		return createdOrder;
	}

	@Override
	public Orders updateOrder(Long orderId, String orderStatus) throws Exception {

		Orders order = findOrderById(orderId);
		if(orderStatus.equals("OUT_FOR_DELIVERY") || 
				orderStatus.equals("DELIVERED") ||
				orderStatus.equals("COMPLETED")|| 
				orderStatus.equals("PENDING"))
		{
			order.setOrderStatus(orderStatus);
			return orderRepo.save(order);
			
		}
		throw new Exception("Plsese valid order status");
	}

	@Override
	public void cancelOrder(long orderId) throws Exception {
           Orders orderById = findOrderById(orderId);
           orderRepo.deleteById(orderId);
	}

	@Override
	public List<Orders> getUsersOrder(Long userId) throws Exception {
        
		return orderRepo.findByCustomerId(userId);
	}

	@Override
	public List<Orders> getRestuarantsOrder(Long restuarnatId, String orderStatus) throws Exception {
         List<Orders> orders = orderRepo.findByRestaurantId(restuarnatId);
         if(orderStatus != null) {
        	 orders = orders.stream().filter(order->order.getOrderStatus()
        			                      .equals(orderStatus)).collect(Collectors.toList());	 
         }
		return orders;
	}

	@Override
	public Orders findOrderById(Long orderId) throws Exception {
		Optional<Orders> order = orderRepo.findById(orderId);
		if(order.isEmpty()) {
			throw new Exception("Order not found");
		}
		return order.get();
	}

}
