package com.webapp.codeathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.codeathon.entity.Winners;

@Repository
public interface WinnersRepository extends JpaRepository<Winners, Integer>{
	
}
