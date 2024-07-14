package com.webapp.codeathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.codeathon.entity.Contest;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Integer>{
	public Contest findByContestNumber(String contestNumber);
}
