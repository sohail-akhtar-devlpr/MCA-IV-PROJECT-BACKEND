package com.webapp.codeathon.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.codeathon.config.AuthenticationTokenJwtUtil;
import com.webapp.codeathon.config.JwtSecurityContext;
import com.webapp.codeathon.entity.AuthenticationToken;
import com.webapp.codeathon.response.AuthenticationResponse;
import com.webapp.codeathon.role.UserRole;
import com.webapp.codeathon.service.AuthenticationTokenService;

import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationTokenController {

	@Autowired
	private AuthenticationTokenService authenticationTokenService;

	@Autowired
	private AuthenticationTokenJwtUtil authenticationTokenJwtUtil;

	@GetMapping("/generate")
	public String generateToken(@RequestParam String role) {
		return authenticationTokenService.createToken(role);
	}
	
	//Get the role from the JWT Token
	@GetMapping("/getrole")
	public ResponseEntity<Map<String, String>> getRoleFromToken(@RequestParam String jwtToken) {
	    System.out.println("JWT TOKEN:: " + jwtToken);
	    try {
	        String role = authenticationTokenJwtUtil.getRoleFromJwt(jwtToken);
	        
	        System.out.println("ROLE:: " + role);
	        if (role != null) {
	            Map<String, String> response = new HashMap<>();
	            response.put("role", role);
	            return ResponseEntity.ok(response);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	    }
	}

//	@PostMapping("/validate")
//    public ResponseEntity<AuthenticationResponse> validateToken(@RequestParam String token, HttpServletResponse response) {
//        boolean isValid = authenticationTokenService.validateToken(token);
//        
//        if (isValid) {
//            String jwtToken = authenticationTokenJwtUtil.generateJwtToken(token);
//            System.out.println("JWT TOKEN========== " + jwtToken);
//            Cookie cookie = new Cookie("jwtToken", jwtToken);
//            cookie.setHttpOnly(true);
//            // cookie.setSecure(true);
//            cookie.setPath("/");
//            cookie.setMaxAge(60 * 1);
//            response.addCookie(cookie);
//            return ResponseEntity.ok(new AuthenticationResponse(HttpStatus.CREATED, jwtToken));
//        } else {
//        	return ResponseEntity.ok(new AuthenticationResponse(HttpStatus.BAD_REQUEST, null));        }
//    }
//	
//	
//	// Backend code to check authentication means any alterations is done or not.
//	@GetMapping("/check")
//	public ResponseEntity<String> checkAuth(HttpServletRequest request,@CookieValue(value = "jwtToken", required = false) String jwtToken) {
//
//		//important for debugging process.
//		Enumeration<String> headerNames = request.getHeaderNames();
//	    while (headerNames.hasMoreElements()) {
//	        String headerName = headerNames.nextElement();
//	        System.out.println(headerName + ": " + request.getHeader(headerName));
//	    }
//		System.out.println("JWT TOKEN IN CHECK+++"+jwtToken);
//		if (jwtToken != null && validateJwtToken(jwtToken)) {
//	        return ResponseEntity.ok("authenticated");
//	    } else {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
//	    }
//	}

	@PostMapping("/validate")
	public ResponseEntity<AuthenticationResponse> validateToken(@RequestParam String token,
			HttpServletResponse response, @RequestParam String role) {
		
		System.out.println("TOKEN IN VALIDATION: "+token);
		System.out.println("ROLE IN VALIDATION: "+role);

		
		boolean isValid = authenticationTokenService.validateToken(token);
		
		if (isValid) {
			String jwtToken = authenticationTokenJwtUtil.generateJwtToken(token,role);
			System.out.println("JWT TOKEN========== " + jwtToken);
			
			String roleFromJwt = authenticationTokenJwtUtil.getRoleFromJwt(jwtToken);
			
			if(roleFromJwt != null) {
				Cookie cookie = new Cookie("jwtToken", jwtToken);
				cookie.setHttpOnly(true);
				// cookie.setSecure(true);
				cookie.setPath("/");
				cookie.setMaxAge(60 * 1);
				response.addCookie(cookie);
			}

			
			// Check authentication directly within this method
			boolean isAuthenticated = checkAuth(jwtToken);
			if (isAuthenticated) {
				return ResponseEntity.ok(new AuthenticationResponse(HttpStatus.CREATED, jwtToken));
			} else {
				return ResponseEntity.ok(new AuthenticationResponse(HttpStatus.BAD_REQUEST, null));
			}
		} else {
			return ResponseEntity.ok(new AuthenticationResponse(HttpStatus.BAD_REQUEST, null));
		}
	}

	// Method to check authentication
	private boolean checkAuth(String jwtToken) {
		
		System.out.println("JWT TOKEN IN CHECK+++" + jwtToken);

		return jwtToken != null && validateJwtToken(jwtToken);
	}

	public boolean validateJwtToken(String token) {
		SecretKey key = Keys.hmacShaKeyFor(JwtSecurityContext.JWT_KEY.getBytes());
		try {
			Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

//	//BASIC AUTH
//	@GetMapping("/basicauth")
//	public String basicAuthCheck() {
//		return "success";
//	}

//	//CREATE USER MEAN ADD TOKEN AND TOKEN PASSWORD
//	@PostMapping("/authtoken")
//	public String addAuthenticationToken(@RequestBody AuthenticationToken authenticationTokenEntity) {
//		return authenticationTokenService.addAuthenticationToken(authenticationTokenEntity);
//	}

//	//AUTHENTICATE THE USER MEAN AUTHENTICATION BASED ON TKEN AND TOKENPASSWORD
//	@PostMapping("/authenticatetoken")
//	public String authenticateToken(@RequestBody AuthenticationToken authenticationTokenEntity) {
//		return authenticationTokenService.authenticateToken(authenticationTokenEntity);
//	}

}
