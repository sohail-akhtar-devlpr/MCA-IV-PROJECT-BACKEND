package com.webapp.codeathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.codeathon.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long>{

}
