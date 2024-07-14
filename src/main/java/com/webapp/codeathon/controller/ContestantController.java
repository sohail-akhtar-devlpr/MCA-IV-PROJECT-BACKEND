package com.webapp.codeathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.codeathon.entity.Contestant;
import com.webapp.codeathon.service.ContestantService;

//@RestController
public class ContestantController {
	
	@Autowired
	private ContestantService contestantService;
	
	@PostMapping("/contestant/create-contestant")
	public ResponseEntity<Contestant> createContestantHandler(@RequestBody Contestant contestant){
		try {
            
       	 Contestant createdContestant = contestantService.saveContestant(contestant);
       	 if(createdContestant == null) {
       		return new ResponseEntity<>(createdContestant, HttpStatus.NOT_FOUND);
       	 }
           return new ResponseEntity<>(createdContestant, HttpStatus.CREATED);
       } catch (Exception e) {
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
	}

}
