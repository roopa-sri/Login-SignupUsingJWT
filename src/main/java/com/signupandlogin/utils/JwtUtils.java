package com.signupandlogin.utils;

import java.nio.file.AccessDeniedException;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.signupandlogin.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	private static String secret = "This_is_secret";
	private static long expiryDuration = 60 * 60;

	public String generateJwt(User user) {

		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expiryDuration * 1000;

		Date issuedAt = new Date(milliTime);
		Date expiryAt = new Date(expiryTime);

		// claims ->JWT PAYLOAD
		Claims claims = Jwts.claims().setIssuer(user.getId().toString()).setIssuedAt(issuedAt).setExpiration(expiryAt);

		// optional claims
		claims.put("type", user.getUserType());
		claims.put("name", user.getName());
		claims.put("emailId", user.getEmailId());

		// generate jwt using claims
		return Jwts.builder().setClaims(claims)// SET METHOD JWT BUILDER
				.signWith(SignatureAlgorithm.HS512, secret).compact();// CONVERT TO STRING
	}

	public Claims validateToken(String authorization) throws Exception {

		try {
			Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
			System.out.println(claims.get("name"));
			return claims;
		} catch (Exception e) {
			throw new AccessDeniedException("Access Denied");
		}

	}
}
