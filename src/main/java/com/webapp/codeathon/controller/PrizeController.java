package com.webapp.codeathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.codeathon.entity.Prize;
import com.webapp.codeathon.service.PrizeService;

@RestController
public class PrizeController {
	
	@Autowired
	private PrizeService prizeService;
	
	@PostMapping("/subadmin/create-prizes")
	public ResponseEntity<List<Prize>> createPrizeHandler(@RequestBody List<Prize> prizeList) {
        try {
            List<Prize> createdPrizeList = prizeService.savePrize(prizeList);
            if (createdPrizeList == null || createdPrizeList.isEmpty()) {
                return new ResponseEntity<>(createdPrizeList, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(createdPrizeList, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
