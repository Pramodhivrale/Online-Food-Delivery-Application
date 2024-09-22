package com.request;

import java.util.List;

import lombok.Data;

@Data
public class AddCartItemRequest 
{
	private Long foodId;
	private int quentity;
	private List<String> ingredients;

}
