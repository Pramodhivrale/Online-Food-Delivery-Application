package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Category;
import com.entity.User;
import com.service.CategoryService;
import com.service.UserService;

@RestController
@RequestMapping("/api/admin/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@PostMapping("/admin/category")
	public ResponseEntity<Category> createCategory(@RequestBody Category category,
			@RequestHeader("Authorization") String jwt) throws Exception

	{

		User userByJwttoken = userService.findUserByJwttoken(jwt);
		Category category2 = categoryService.createCategory(category.getName(), userByJwttoken.getId());
		return new ResponseEntity<>(category2, HttpStatus.CREATED);

	}

	@GetMapping("/category/restaurant")
	public ResponseEntity<List<Category>> getrestuarantCategory(@RequestHeader("Authorization") String jwt)
			throws Exception {

		User userByJwttoken = userService.findUserByJwttoken(jwt);
		List<Category> listofcategory = categoryService.findcategoryByRestuarnat(userByJwttoken.getId());
		return new ResponseEntity<List<Category>>(listofcategory, HttpStatus.CREATED);

	}
}
