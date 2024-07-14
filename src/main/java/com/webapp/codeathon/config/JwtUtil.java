package com.webapp.codeathon.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.webapp.codeathon.role.UserRole;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {
	
	SecretKey key = Keys.hmacShaKeyFor(JwtSecurityContext.JWT_KEY.getBytes());
	
	public String generateJwtToken(Authentication authentication) {
	    return Jwts.builder()
	        .issuer("Aligarh Muslim University")
	        .issuedAt(new Date())
	        .expiration(new Date(new Date().getTime() + 1000L * 60 * 60 * 24 * 30)) //30 minutes
	        // .claim("authorities", populateAuthorities(authentication.getAuthorities()))
	        .claim("username", authentication.getName())
	        .claim("role", UserRole.SUBADMIN)
	        .signWith(key).compact();
	}
	
	public String getEmailFromJwt(String jwt) {
		jwt= jwt.substring(7);
		System.out.println("GET EMAIL FROM JWT::"+jwt);
		Claims claims=
				Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
		//email is set as username in the jwt token
		String email= String.valueOf(claims.get("username"));
		System.out.println("CLAIMS EMAIL:: "+email);
		return email;
	}

}
