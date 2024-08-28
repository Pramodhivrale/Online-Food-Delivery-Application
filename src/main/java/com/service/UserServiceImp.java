package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.stereotype.Service;

import com.config.JwtProvider;
import com.entity.User;
import com.repo.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Override
	public User findUserByJwttoken(String jwt) throws Exception {
		String emailFromJwtToken = jwtProvider.getEmailFromJwtToken(jwt);
		User byEmail = userRepository.findByEmail(emailFromJwtToken);
		return byEmail;
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		User byEmail = userRepository.findByEmail(email);
		if(byEmail == null) {
			throw new Exception("User not found");
		}
		return byEmail;
	}

}
