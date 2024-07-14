package com.webapp.codeathon.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.webapp.codeathon.entity.AuthenticationToken;
import com.webapp.codeathon.exception.TokenNotFoundException;
import com.webapp.codeathon.repository.AuthenticationTokenRepository;
import com.webapp.codeathon.role.UserRole;
import com.webapp.codeathon.util.AuthTokenUtil;

@Service
public class AuthenticationTokenService {

	@Autowired
	private AuthenticationTokenRepository authenticationTokenRepository;
	
	//Create Token
	public String createToken(String role) {
		String token = AuthTokenUtil.generateToken(15);
		
		AuthenticationToken authenticationToken = new AuthenticationToken();
		
		authenticationToken.setToken(token);
		authenticationToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));
		authenticationToken.setRole(role);
		authenticationTokenRepository.save(authenticationToken);
		
		return token;
	}
	
//	public boolean validateToken(String token) {
//		return authenticationTokenRepository.findByToken(token)
//				.map(authToken -> !authToken.getExpiryDate().isBefore(LocalDateTime.now()))
//                .orElse(false);
//	}
	
	
	public boolean validateToken(String token) {
		return authenticationTokenRepository.findByToken(token)
				.map(authToken -> !authToken.getExpiryDate().isBefore(LocalDateTime.now()))
                .orElse(false);
	}
	

//	// ADD TOKEN AND PASSWORD IN THE DATABASE MEAN USER IS GETTING CREATED
//	public String addAuthenticationToken(AuthenticationToken authenticationToken) {
//
//		// PASSWORD IS IN ENCRYPTED FORM
//		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//		String encryptedPwd = bcrypt.encode(authenticationToken.getTokenPassword());
//		authenticationToken.setTokenPassword(encryptedPwd);
//		AuthenticationToken authToken = authenticationTokenRepository.save(authenticationToken);
//		return authToken.getToken() + "added to the database successfully";
//	}
//
//	// FIRST FIND THE USER AND THEN AUTHENTICATIE THE TOKEN WITH TOKEN PASSWORD
//	public String authenticateToken(AuthenticationToken authenticationToken) throws TokenNotFoundException {
//
//		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//		Optional<AuthenticationToken> opToken = authenticationTokenRepository
//				.findById(authenticationToken.getToken());
//		if (opToken.isPresent()) {
//			AuthenticationToken dbToken = opToken.get();
//			if (bcrypt.matches(authenticationToken.getTokenPassword(), dbToken.getTokenPassword()))
//				return "authenticated";
//			else
//				return "notauthenticated";
//		}
//		throw new TokenNotFoundException("Token Not Found");
//	}

}
