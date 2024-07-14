package com.webapp.codeathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.codeathon.entity.Organizer;
import com.webapp.codeathon.service.OrganizerService;

@RestController
public class OrganizerController {
	
	@Autowired
	private OrganizerService organizerService;
	
	@PostMapping("/subadmin/create-organizers")
	public ResponseEntity<List<Organizer>> createOrganizerHandler(@RequestBody List<Organizer> organizerList) {
        try {
            List<Organizer> createdOrganizerList = organizerService.saveOrganizer(organizerList);
            if (createdOrganizerList == null || createdOrganizerList.isEmpty()) {
                return new ResponseEntity<>(createdOrganizerList, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(createdOrganizerList, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
