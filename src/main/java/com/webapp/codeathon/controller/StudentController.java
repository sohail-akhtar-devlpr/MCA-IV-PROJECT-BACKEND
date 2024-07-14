package com.webapp.codeathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.codeathon.entity.Student;
import com.webapp.codeathon.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/subadmin/student/st")
	public Student saveStudentDetails(@RequestBody Student student) {
		
		return studentService.saveStudent(student);
	}
}
