package com.webapp.codeathon.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureAlgorithm;

@Service
public class AuthenticationTokenJwtUtil {
	
	SecretKey key = Keys.hmacShaKeyFor(JwtSecurityContext.JWT_KEY.getBytes());
// Generate jwt token with Auth Token and Role
    public String generateJwtToken(String AuthToken, String role) {
    	System.out.println("ROLE IN GENERATETOKEN: "+role);
        return Jwts.builder()
        		.subject(AuthToken)
        		.claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1 day
                .signWith(key)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public String extractToken(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
    
    
//    public String getRoleFromJwt(String jwtToken) {
//    	Claims claims = Jwts.parser()
//    						.verifyWith(key)
//    						.build()
//    						.parseSignedClaims(jwtToken)
//    						.getPayload();
//    	return claims.get("role",String.class);
//    }
    
    public String getRoleFromJwt(String jwtToken) {
    	System.out.println("TOKEN IN THE ROLE: "+jwtToken);
        try {
            Claims claims = Jwts.parser()
                                .verifyWith(key)
                                .build()
                                .parseSignedClaims(jwtToken)
                                .getPayload();
            String role = claims.get("role", String.class);
            System.out.println("Extracted Role: " + role);  // Print statement to check the extracted role
//            System.out.println("Claims: "+claims);
            return role;
        } catch (Exception e) {
            System.out.println("Exception while extracting role: " + e.getMessage());
            return null;
        }
    }

}