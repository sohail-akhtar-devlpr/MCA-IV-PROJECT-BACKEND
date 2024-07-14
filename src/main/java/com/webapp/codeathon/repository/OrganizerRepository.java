package com.webapp.codeathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.codeathon.entity.Organizer;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
	
}
