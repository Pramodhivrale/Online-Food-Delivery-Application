package com.service;

import java.util.List;

import com.entity.Address;
import com.entity.ContactInformation;

import lombok.Data;

@Data
public class CreateRestuarntRequest {

	private Long id;
	private String name;
	private String description;
	private String cuisineType;
	private Address address;
	private ContactInformation contactInformation;
	private String openingHours;
	private List<String> images;
}
