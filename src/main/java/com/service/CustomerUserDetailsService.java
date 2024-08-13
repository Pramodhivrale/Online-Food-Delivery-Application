package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.USER_ROLE;
import com.entity.User;
import com.repo.UserRepository;

// This class will not allow spring to generate security password which 
//is default password comes after security dependency addition
@Service
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository loginUser;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = loginUser.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with these email :" + username);
		}

		USER_ROLE role = user.getRole();

		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(role.toString()));

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}

}
