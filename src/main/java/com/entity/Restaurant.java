package com.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long resturnatId;
	
	@OneToOne
	private User owener;
	
	private String resturantName;
	
	private String description;
	
	private String cuisineType;
	
	@OneToOne
	private Address adreess;
	
	@Embedded
	private ContactInformation contactInfo;
	
	private String openingHoures;
	
	@OneToMany(mappedBy = "restaurants",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Orders> orders;
	
	@ElementCollection
	@Column(length = 1000)
	private List<String> images;
	
	private LocalDateTime registrationDate;
	
	private boolean open;
	
	@JsonIgnore
	@OneToMany(mappedBy = "resturant",cascade = CascadeType.ALL)
	private List<Food> foods;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
