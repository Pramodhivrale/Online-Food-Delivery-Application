package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Category;
import com.entity.Restaurant;
import com.repo.CategoryRepo;

@Service
public class CategoryServiceImplementation implements CategoryService	 {

	@Autowired
	private ResturantService resturantService;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Override
	public Category createCategory(String name, Long userId) throws Exception {
		Restaurant resturantById = resturantService.findResturantById(userId);
		Category category=new Category();
		category.setName(name);
		category.setRestaurants(resturantById);
		
		
		return categoryRepo.save(category);
	}

	@Override
	public List<Category> findcategoryByRestuarnat(Long id) throws Exception {
		Restaurant getrestuarantByUserId = resturantService.getrestuarantByUserId(id);
		
		return categoryRepo.findByRestaurantId(getrestuarantByUserId.getRestaurantId());
	}

	@Override
	public Category findCategoryById(Long id) throws Exception {
		Optional<Category> byId = categoryRepo.findById(id);
		if(byId.isEmpty()) {
			throw new Exception("Category not found");
		}
		return byId.get();
	}

	

}
