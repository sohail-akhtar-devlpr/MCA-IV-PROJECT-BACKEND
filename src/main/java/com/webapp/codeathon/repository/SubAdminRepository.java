package com.webapp.codeathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.codeathon.entity.SubAdmin;

public interface SubAdminRepository extends JpaRepository<SubAdmin, Integer> {
	
	public SubAdmin findByEmail(String email);

}
