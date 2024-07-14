package com.webapp.codeathon.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.codeathon.entity.Address;
import com.webapp.codeathon.entity.Student;
import com.webapp.codeathon.repository.ContestRepository;
import com.webapp.codeathon.repository.StudentRepo;

@Service
public class StudentService {
	
//	private static final Logger logger = LoggerFactory.getLogger(ContestRepository.class);


	@Autowired
	private StudentRepo studentRepo;
	
//	public Student saveStudent(Student student) {
//		
//		Student savedStudent = studentRepo.save(student);
//		return savedStudent;
//	}
	
	public Student saveStudent(Student student) {
        
		// Setting the student object on each address to establish the bi-directional relationship
//        student.getAddresses().forEach(address -> address.setStudent(student));
//        
//        Student savedStudent = studentRepo.save(student);
//        return savedStudent;
        
        return studentRepo.save(student);
    }
}
