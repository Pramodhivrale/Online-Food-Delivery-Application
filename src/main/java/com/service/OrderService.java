package com.service;

import java.util.List;

import com.entity.Orders;
import com.entity.User;
import com.request.OrderRequest;

public interface OrderService {
	public Orders createOrder(OrderRequest order, User user);

	public Orders updateOrder(Long orderId, String orderStatus) throws Exception;

	public void cancelOrder(long orderId) throws Exception;

	public List<Orders> getUsersOrder(Long userId) throws Exception;

	public List<Orders> getRestuarantsOrder(Long restuarnatId, String orderStatus) throws Exception;

}
