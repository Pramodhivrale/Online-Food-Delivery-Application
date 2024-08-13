package com.entity;

import java.util.List;

import com.dto.RestuarantDao;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fullname;
	
	private String email;
	
	private String password;
	
	private USER_ROLE role=USER_ROLE.ROLE_CUSTOMER;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
	private List<Orders> orders;
	
	@ElementCollection
	private List<RestuarantDao> favourite;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Address> address;
	
	

}
