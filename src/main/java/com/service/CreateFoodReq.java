package com.service;

import java.util.List;

import com.entity.Category;
import com.entity.IngredientsItem;

import lombok.Data;

@Data
public class CreateFoodReq 
{
	private String name;
	private String description;
	private Long price;
	
	private Category category;
	private List<String> images;
	
	private Long restuarantId;
	private boolean vegetarian;
	private boolean seasional;
	
	private List<IngredientsItem> ingredientsItems;

}
