package com.request;

import com.entity.Address;

import lombok.Data;

@Data
public class OrderRequest 
{
	private Long restaurantId;
	private Address deliveryAddress;

}
