package com.service;

import java.util.List;

import com.dto.RestuarantDao;
import com.entity.Restaurant;
import com.entity.User;

public interface ResturantService {

	public Restaurant createRestuarant(CreateRestuarntRequest req, User user);
	
	public Restaurant updateResturant(Long resturantId,CreateRestuarntRequest updateRequest);
	
	public void deleteResturant(Long resturantId) throws Exception;
	
	public List<Restaurant> getallRestaurant();
	
	public List<Restaurant> searchRestaurant(String keyword);
	
	public Restaurant findResturantById(Long resturantId) throws Exception;
	
	public Restaurant getrestuarantByUserId(Long userId) throws Exception;
	
	public RestuarantDao addFavorites(Long resturantId,User user) throws Exception;
	
	public Restaurant updateRestuarantStatus(Long id) throws Exception;

}
