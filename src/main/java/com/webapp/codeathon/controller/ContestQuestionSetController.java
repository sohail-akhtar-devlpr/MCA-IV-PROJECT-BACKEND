package com.webapp.codeathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.codeathon.entity.ContestQuestionSet;
import com.webapp.codeathon.service.ContestQuestionSetService;

@RestController
public class ContestQuestionSetController {

	@Autowired
	private ContestQuestionSetService contestQuestionSetService;
	
	@PostMapping("/subadmin/create/contest-question-set")
	public ResponseEntity<ContestQuestionSet> createContestQuestionSetHandler
									(@RequestBody ContestQuestionSet contestQuestionSet) {
		
		try {
			ContestQuestionSet conteQuestionSetCreated
			=contestQuestionSetService.createContestQuestionSet(contestQuestionSet);
			if(conteQuestionSetCreated != null) {
				return new ResponseEntity<>(conteQuestionSetCreated,HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>(conteQuestionSetCreated,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
		    System.err.println("Error in creating contest: " + e.getMessage());
		    e.printStackTrace();
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
