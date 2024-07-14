package com.webapp.codeathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.codeathon.entity.Contest;
import com.webapp.codeathon.entity.Contestant;
import com.webapp.codeathon.repository.ContestRepository;
import com.webapp.codeathon.repository.ContestantRepository;

@Service
public class ContestantService {
	
	@Autowired
	private ContestantRepository contestantRepository;
	
	@Autowired
	private ContestRepository contestRepository;

	
	public Contestant saveContestant(Contestant contestant) {
		
		Contest contest = contestRepository.findByContestNumber(contestant.getContestNumber());

		
		if(contest != null) {
	    // Setting the organizer object on each contest to establish the bi-directional relationship
	    contestant.setContest(contest);
		Contestant createdContestant = contestantRepository.save(contestant);
	    return createdContestant;
		}
	    // Check if the contestOrganizer list is null
//	    List<Organizer> org = createdContest.getContestOrganizer();
//	    if (org != null) {
//	        for (Organizer organizer : org) {
//	            organizer.setContestNumber(createdContest.getContestNumber());
//	            logger.info("ORGANIZER : {}", organizer);
//	            organizerRepository.save(organizer);
//	        }
//	    }
	    return null;
	}
}
