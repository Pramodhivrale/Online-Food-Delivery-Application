package com.service;

import java.util.List;

import com.entity.IngredientsCategory;
import com.entity.IngredientsItem;

public interface IngredientService 
{
	public IngredientsCategory createingredientcategory(String name,Long restaurantId) throws Exception;
	
	public IngredientsCategory findIngredientsCategoryById(Long id)throws Exception;
	
	public List<IngredientsCategory> findIngredientsCategoryByRestaruantId(Long id)throws Exception;
	
	public IngredientsItem createIngredientItem(Long resturantId,String ingredientName,Long ingredientCategoryId)throws Exception;
	
	public List<IngredientsItem> findRestaurantIngredients(Long restuantId);
	
	public IngredientsItem updateStock(Long id)throws Exception;
	
	

}
