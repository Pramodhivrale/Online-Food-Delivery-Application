package com.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {

//	
//	  this is one way to secure some specific url
//	  http.authorizeHttpRequests((request) -> request
//	  .antMatchers("/","/login","/about", "/swagger-ui.html").permitAll()
//	  .anyRequest().authenticated() ).formLogin();
//	  
//	  return http.build();
	  
	 

	/*
	 * this is another way to secure some specific url
	 * 
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception {
	 * 
	 * http.authorizeHttpRequests((request) -> request .requestMatchers("/",
	 * "/login", "/about", "/swagger-ui.html").permitAll()
	 * .anyRequest().authenticated() ).formLogin();
	 * 
	 * return http.build();
	 * 
	 * 
	 * }
	 * 
	 */

	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(
						Authorize -> Authorize.requestMatchers("/api/admin/**").hasAnyRole("RESTAURANT", "ADMIN")
								.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
				.addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
				.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configurationSource(corsConfigurationSource()));
		
		return httpSecurity.build();

	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {

		return new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration configuration = new CorsConfiguration();
				configuration.setAllowCredentials(true);
				configuration.setAllowedMethods(Collections.singletonList("*")); // Allow all methods
				configuration.setAllowedHeaders(Collections.singletonList("*")); // Allow all headers
				configuration.setExposedHeaders(Arrays.asList("Authorization")); // Specify which headers to expose
				configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080","","")); // Specify allowed origins
				configuration.setMaxAge(3600L); // Set cache duration for preflight requests
				return configuration;
			}
		};

	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
