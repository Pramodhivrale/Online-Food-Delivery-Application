package com.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.JwtProvider;
import com.entity.Cart;
import com.entity.USER_ROLE;
import com.entity.User;
import com.repo.CardRepo;
import com.repo.UserRepository;
import com.request.LoginRequest;
import com.response.AuthResponse;
import com.service.CustomerUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private CustomerUserDetailsService customeUserDetailsService;

	@Autowired
	private CardRepo cardRepo;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> creatUserHandeler(@RequestBody User user) throws Exception {

		User isEmailExist = userRepository.findByEmail(user.getEmail());
		if (isEmailExist == null) {
			throw new Exception("Email is allreday used with another account !");
		}

		User createdUser = new User();
		createdUser.setEmail(user.getEmail());
		createdUser.setFullname(user.getFullname());
		createdUser.setRole(user.getRole());
		createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

		User savedUser = userRepository.save(createdUser);

		Cart c = new Cart();
		c.setCustomer(savedUser);
		cardRepo.save(c);

		org.springframework.security.core.Authentication authentication = new UsernamePasswordAuthenticationToken(
				user.getEmail(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Register success");
		authResponse.setRole(savedUser.getRole());

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> singin(@RequestBody LoginRequest loginRequest) {
		String useremail = loginRequest.getEmail();
		String userpassword = loginRequest.getPassword();

		Authentication authentication = authenticate(useremail, userpassword);

		String jwt = jwtProvider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Register success");

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

		authResponse.setRole(USER_ROLE.valueOf(role));

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
	}

	private Authentication authenticate(String useremail, String userpassword) {

		UserDetails userDetails = customeUserDetailsService.loadUserByUsername(useremail);

		if (userDetails == null) {
			throw new BadCredentialsException("Invalid username....");
		}
		if (!passwordEncoder.matches(userpassword, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid password....");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
