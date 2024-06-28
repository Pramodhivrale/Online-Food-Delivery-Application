package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long engItemId;

	private String name;

	@ManyToOne
	private IngredientsCategory category;
	
	@JsonIgnore
	@ManyToOne
	private Restuarant restaurants;
	
	private boolean stock=true;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
