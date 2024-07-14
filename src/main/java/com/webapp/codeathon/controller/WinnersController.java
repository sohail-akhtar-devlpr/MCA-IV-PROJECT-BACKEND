package com.webapp.codeathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.codeathon.entity.Winners;
import com.webapp.codeathon.service.WinnersService;

@RestController
public class WinnersController {
	
	@Autowired
	private WinnersService winnersService;
	
	@PostMapping("/subadmin/create-winners")
	public ResponseEntity<List<Winners>> createWinnersHandler(@RequestBody List<Winners> winnersList) {
        try {
            List<Winners> createdWinnersList = winnersService.saveWinners(winnersList);
            if (createdWinnersList == null || createdWinnersList.isEmpty()) {
                return new ResponseEntity<>(createdWinnersList, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(createdWinnersList, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
