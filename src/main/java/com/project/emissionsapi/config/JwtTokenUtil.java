package com.project.emissionsapi.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
	public static final long JWT_TOKEN_VALIDITY = 8 * 60 * 60 * 1000;

	@Value("${jwt.secret}")
	private String secret;

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

//	public Date getRoleFromToken(String token) {
//		return getClaimFromToken(token, Claims::get("roles", List<GrantedAuthority>.class)));
//	}
//	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {

		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		String token = "Bearer ";
		token += Jwts.builder().setSubject(subject).addClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)) // in milliseconds
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
		return token;
	}

//	public Boolean validateToken(String token, UserDetails userDetails) {
//		final String username = getUsernameFromToken(token);
//		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		List<String> roles = new ArrayList<>();
		userDetails.getAuthorities().forEach(authority ->  {
			roles.add(authority.getAuthority());
		});
		claims.put("roles", roles );
		return doGenerateToken(claims, userDetails.getUsername());
	}
}