package com.webapp.codeathon.config;

import java.io.IOException;
import java.util.Date;
//import java.sql.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("ENTERED IN JWT TOKEN VALIDATION FILTER");
//		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
//		
//		if(authentication != null) {
//			SecretKey key=  Keys.hmacShaKeyFor(JwtSecurityContext.JWT_KEY.getBytes());
//			String jwt=
//					Jwts.builder()
//					.setIssuer("Aligarh Muslim University")
//					.setIssuedAt(new Date())
//					.setExpiration(new Date(new Date().getTime()+86400000))
//					.claim("authorities", populateAuthorities(authentication.getAuthorities()))
//					.claim("email", authentication.getName())
//					.signWith(key).compact();
//		}
		
		//1. FETCH THE HEADER
		String jwt=request.getHeader(JwtSecurityContext.JWT_HEADER);
		
		System.out.println("REQUEST HEADER : "+jwt);
		
		//2. IF NOT NULL, THEN FETCH THE JWT INFORMATION
		if(jwt != null) {
			
			try {
				//Extracting the Bearer
				jwt= jwt.substring(7);
				
				SecretKey key= Keys.hmacShaKeyFor(JwtSecurityContext.JWT_KEY.getBytes());
				Claims claims= Jwts.parser()
						.verifyWith(key)
						.build()
						.parseSignedClaims(jwt)
						.getPayload();
				
				String username= String.valueOf(claims.get("username"));
				String authorities= (String)claims.get("authorities");
				
				List<GrantedAuthority> auths=
						AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
				
				Authentication auth= new UsernamePasswordAuthenticationToken(username,null, auths);
				
				SecurityContextHolder.getContext().setAuthentication(auth);
				
			} catch (Exception e) {
				
				throw new BadCredentialsException("Invalid Token Received...error");
			}
		} //end of if-block
		
		filterChain.doFilter(request, response);
	}//end of doFilterInternal-blockk
	
	
	//Auth  token verification
	public Claims extractAllClaims(String token) {
		 SecretKey key= Keys.hmacShaKeyFor(JwtSecurityContext.JWT_KEY.getBytes());
	        return ((JwtParser) Jwts.parser().verifyWith(key)).parseSignedClaims(token).getPayload();
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
	    
	   

	

//	public String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
//		Set<String> authoritiesSet=new HashSet<>();
//		
//		for(GrantedAuthority authority:collection) {
//			authoritiesSet.add(authority.getAuthority());
//		}
//		return String.join(",", authoritiesSet);
//	}
	
	
//	protected boolean shouldNotFilter(HttpServletRequest req) throws ServletException{
//		return req.getServletPath().equals("/api/auth/user/");
//	}


}
