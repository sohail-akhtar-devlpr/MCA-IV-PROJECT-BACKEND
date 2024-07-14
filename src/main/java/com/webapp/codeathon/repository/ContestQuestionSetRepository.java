package com.webapp.codeathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.codeathon.entity.ContestQuestionSet;

public interface ContestQuestionSetRepository extends JpaRepository<ContestQuestionSet, Integer> {

}
