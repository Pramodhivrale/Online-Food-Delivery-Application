package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.Orders;
import com.entity.User;
import com.request.OrderRequest;

@Service
public class OrderSeriveImpl implements OrderService {

	@Override
	public Orders createOrder(OrderRequest order, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders updateOrder(Long orderId, String orderStatus) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelOrder(long orderId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Orders> getUsersOrder(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getRestuarantsOrder(Long restuarnatId, String orderStatus) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
