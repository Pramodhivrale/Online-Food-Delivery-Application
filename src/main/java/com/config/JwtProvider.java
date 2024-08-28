package com.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

	private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SERCATE_KEY.getBytes());

	public String generateToken(Authentication auth) {

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		String roles = populateAuthorities(authorities);

		String jwt = Jwts.builder().setIssuedAt(new java.util.Date())
				.setExpiration(new java.util.Date(System.currentTimeMillis() + 86400000)).claim("email", auth.getName())
				.claim("authorities", roles).signWith(key).compact();
		return jwt;

	}

	public String getEmailFromJwtToken(String jwt) {
		if (jwt.startsWith("Bearer ")) {
			jwt = jwt.substring(7);
		}
		Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

		String email = String.valueOf(claims.get("email"));

		return email;
	}

//	private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
//	    return authorities.stream()
//	        .map(GrantedAuthority::getAuthority)
//	        .collect(Collectors.joining(","));
//	}

	private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Set<String> auth = new HashSet<>();

		for (GrantedAuthority authority : authorities) {
			auth.add(authority.getAuthority());
		}
		return String.join(",", auth);

	}
}
