package com.service;

import com.entity.User;

public interface UserService {
	
  public User findUserByJwttoken(String jwt) throws Exception;
 
  public User findUserByEmail(String email) throws Exception;
  
}
