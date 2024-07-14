package com.webapp.codeathon.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.codeathon.entity.Contest;
import com.webapp.codeathon.service.ContestService;

import jakarta.validation.Valid;

@RestController
public class ContestController {

    @Autowired
    private ContestService contestService;
    
    @PostMapping("/subadmin/create/contest")
    public ResponseEntity<?> createContestHandler(@Valid @RequestBody Contest contest,
    		BindingResult result){
    	
    	if(result.hasErrors()) {
    		Map<String, String> errors = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
    	}
    	
        try {
        	
        	System.out.println("EXECUTION 1");
            
        	 Contest createdContest = contestService.saveContest(contest);
//        	Contest createdContest = contestService.createContest(contest);
        	 
            return new ResponseEntity<>(createdContest, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
