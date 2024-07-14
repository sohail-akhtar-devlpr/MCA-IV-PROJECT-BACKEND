package com.webapp.codeathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.codeathon.entity.Contestant;

public interface ContestantRepository extends JpaRepository<Contestant, String> {
	public Contestant findByEnrollmentNumber(String enrollmentNumber);
	public Contestant findByFacultyNumber(String facultyNumber);
}
