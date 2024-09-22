package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.IngredientsCategory;
import com.entity.IngredientsItem;
import com.entity.Restaurant;
import com.repo.IngredientItemRepository;
import com.repo.Ingredientscategoryrepo;

@Service
public class IngredientsSeriveImpl implements IngredientService {

	@Autowired
	private ResturantService resturantService;

	@Autowired
	private IngredientItemRepository ingredientItemRepository;

	@Autowired
	private Ingredientscategoryrepo ingredientscategoryrepo;

	@Override
	public IngredientsCategory createingredientcategory(String name, Long restaurantId) throws Exception {
		Restaurant resturantById = resturantService.findResturantById(restaurantId);
		IngredientsCategory category = new IngredientsCategory();
		category.setRestaurants(resturantById);
		category.setName(name);
		return ingredientscategoryrepo.save(category);
	}

	@Override
	public IngredientsCategory findIngredientsCategoryById(Long id) throws Exception {
		Optional<IngredientsCategory> byId = ingredientscategoryrepo.findById(id);
		if (byId.isEmpty()) {
			throw new Exception("Ingredient Category Not Found");
		}

		return byId.get();
	}

	@Override
	public List<IngredientsCategory> findIngredientsCategoryByRestaruantId(Long id) throws Exception {
		Restaurant resturantById = resturantService.findResturantById(id);
		return ingredientscategoryrepo.findByRestuarntId(id);
	}

	@Override
	public IngredientsItem createIngredientItem(Long resturantId, String ingredientName, Long ingredientCategoryId)
			throws Exception {
		Restaurant resturant = resturantService.findResturantById(resturantId);
		IngredientsCategory ingredientById = findIngredientsCategoryById(ingredientCategoryId);
		IngredientsItem item = new IngredientsItem();
		item.setName(ingredientName);
		item.setRestaurants(resturant);
		item.setCategory(ingredientById);
		IngredientsItem ingredient = ingredientItemRepository.save(item);
		ingredientById.getIngredientsItems().add(ingredient);
		return ingredient;
	}

	@Override
	public List<IngredientsItem> findRestaurantIngredients(Long restuantId) {

		return ingredientItemRepository.findByRstuarantId(restuantId);
	}

	@Override
	public IngredientsItem updateStock(Long id) throws Exception {
		Optional<IngredientsItem> optionalIngredientItem = ingredientItemRepository.findById(id);
		if(optionalIngredientItem.isEmpty()) {
			throw new Exception("Ingredient Not Found");
		}
		IngredientsItem ingredientsItem = optionalIngredientItem.get();
		ingredientsItem.setStock(!ingredientsItem.isStock());
		return ingredientItemRepository.save(ingredientsItem);
	}

}
