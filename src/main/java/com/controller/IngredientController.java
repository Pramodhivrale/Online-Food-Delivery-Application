package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.IngredientsCategory;
import com.entity.IngredientsItem;
import com.request.IngredientCategoryRequest;
import com.request.IngredientRequest;
import com.service.IngredientService;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;

	@PostMapping("/category")
	public ResponseEntity<IngredientsCategory> createIngredientCategory(

			@RequestBody IngredientCategoryRequest req) throws Exception {
		IngredientsCategory createingredientcategory = ingredientService.createingredientcategory(req.getName(),
				req.getRestuarntId());

		return new ResponseEntity<IngredientsCategory>(createingredientcategory, HttpStatus.CREATED);

	}

	@PostMapping()
	public ResponseEntity<IngredientsItem> createIngredientItem(

			@RequestBody IngredientRequest req) throws Exception {
		IngredientsItem ingredientItem = ingredientService.createIngredientItem(req.getRestuarantId(), req.getName(),
				req.getCategoryId());

		return new ResponseEntity<IngredientsItem>(ingredientItem, HttpStatus.CREATED);

	}

	@PutMapping("/{id}/stock")
	public ResponseEntity<IngredientsItem> updateIngreidentStock(@PathVariable Long id) throws Exception {

		IngredientsItem updateStock = ingredientService.updateStock(id);
		return new ResponseEntity<IngredientsItem>(updateStock, HttpStatus.CREATED);

	}
	
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<List<IngredientsItem>> getRestaurantIngrediants(
			@PathVariable Long id
			){
		
		List<IngredientsItem> restaurantIngredients = ingredientService.findRestaurantIngredients(id);
		return new ResponseEntity<>(restaurantIngredients,HttpStatus.CREATED);
	}
	
	@GetMapping("/restaurant/{id}/category")
	public ResponseEntity<List<IngredientsCategory>> getRestaurantIngrediantCategory(
			@PathVariable Long id
	) throws Exception{
		
		List<IngredientsCategory> restaurantIngredients = ingredientService.findIngredientsCategoryByRestaruantId(id);
		return new ResponseEntity<>(restaurantIngredients,HttpStatus.CREATED);
	}

}
