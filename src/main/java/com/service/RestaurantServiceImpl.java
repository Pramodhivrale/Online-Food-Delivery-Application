package com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.RestuarantDao;
import com.entity.Address;
import com.entity.Restaurant;
import com.entity.User;
import com.repo.Addressrepo;
import com.repo.RestuarantRepo;
import com.repo.UserRepository;

@Service
public class RestaurantServiceImpl implements ResturantService {

	@Autowired
	private RestuarantRepo resturantRepo;

	@Autowired
	private Addressrepo addressrepo;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Restaurant createRestuarant(CreateRestuarntRequest req, User user) {
		Address adress = addressrepo.save(req.getAddress());

		Restaurant restaurant = new Restaurant();
		restaurant.setAdreess(adress);
		restaurant.setContactInfo(req.getContactInformation());
		restaurant.setCuisineType(req.getCuisineType());
		restaurant.setDescription(req.getDescription());
		restaurant.setImages(req.getImages());
		restaurant.setResturantName(req.getName());
		restaurant.setOpeningHoures(req.getOpeningHours());
		restaurant.setRegistrationDate(LocalDateTime.now());
		restaurant.setOwener(user);

		return resturantRepo.save(restaurant);
	}

	@Override
	public Restaurant updateResturant(Long resturantId, CreateRestuarntRequest updateRequest) {

		Optional<Restaurant> byId = resturantRepo.findById(resturantId);
		Restaurant restaurant = byId.get();

		if (restaurant.getCuisineType() != null) {
			restaurant.setCuisineType(updateRequest.getCuisineType());
		}
		if (restaurant.getDescription() != null) {
			restaurant.setDescription(updateRequest.getDescription());
		}
		if (restaurant.getResturantName() != null) {
			restaurant.setResturantName(updateRequest.getName());
		}

		return resturantRepo.save(restaurant);
	}

	@Override
	public void deleteResturant(Long resturantId) throws Exception {

		Optional<Restaurant> byId = resturantRepo.findById(resturantId);
		Restaurant restaurant = byId.get();
		resturantRepo.delete(restaurant);

	}

	@Override
	public List<Restaurant> getallRestaurant() {
		return resturantRepo.findAll();
	}

	@Override
	public List<Restaurant> searchRestaurant(String keyword) {

		return resturantRepo.findBySearchQuery(keyword);
	}

	@Override
	public Restaurant findResturantById(Long resturantId) throws Exception {
		Optional<Restaurant> byId = resturantRepo.findById(resturantId);
		if (byId.isEmpty()) {
			throw new Exception("Restuarant no found by id :" + resturantId);
		}
		return byId.get();
	}

	@Override
	public Restaurant getrestuarantByUserId(Long userId) throws Exception {
		Restaurant byOwenerId = resturantRepo.findByOwenerId(userId);
		if (byOwenerId == null) {
			throw new Exception("Restuarnat not found with owener id :" + userId);
		}
		return byOwenerId;
	}

	@Override
	public RestuarantDao addFavorites(Long resturantId, User user) throws Exception {
		Optional<Restaurant> byId = resturantRepo.findById(resturantId);
		Restaurant restaurant = byId.get();

		RestuarantDao dto = new RestuarantDao();
		dto.setTitle(restaurant.getResturantName());
		dto.setDescription(restaurant.getDescription());
		dto.setImages(restaurant.getImages());
		dto.setId(resturantId);

		if (user.getFavourite().contains(dto)) {
			user.getFavourite().remove(dto);
		} else {
			user.getFavourite().add(dto);
		}
		userRepository.save(user);

		return dto;
	}

	@Override
	public Restaurant updateRestuarantStatus(Long id) throws Exception {
		Optional<Restaurant> byId = resturantRepo.findById(id);
		if(byId.isEmpty()) {
			throw new Exception("Restuarnt on found with this id :"+id);
		}
		Restaurant restaurant = byId.get();
		restaurant.setOpen(!restaurant.isOpen());
		return resturantRepo.save(restaurant);
	}

}
