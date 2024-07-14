package com.webapp.codeathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.codeathon.entity.RootAdmin;

public interface RootAdminRepository extends JpaRepository<RootAdmin, Integer> {

	public RootAdmin findByEmail(String email);
}
