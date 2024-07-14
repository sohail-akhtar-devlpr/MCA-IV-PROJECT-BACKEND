package com.webapp.codeathon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webapp.codeathon.entity.AuthenticationToken;


public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Integer> {
	//future 
	Optional<AuthenticationToken> findByToken(String token);
	
}