package com.webapp.codeathon.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.codeathon.entity.Contest;
import com.webapp.codeathon.entity.Organizer;
import com.webapp.codeathon.entity.Prize;
import com.webapp.codeathon.repository.ContestRepository;
import com.webapp.codeathon.repository.OrganizerRepository;
import com.webapp.codeathon.repository.PrizeRepository;

@Service
public class ContestService {

	@Autowired
	private ContestRepository contestRepository;
	
	@Autowired
	private OrganizerRepository organizerRepository;
	
	@Autowired
    private PrizeRepository prizeRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ContestRepository.class);

	public Contest saveContest(Contest contest) {
		System.out.println("EXECUTION 2");
	    // Setting the organizer object on each contest to establish the bi-directional relationship
	    Contest createdContest = contestRepository.save(contest);
	    
	    System.out.println("EXECUTION 3");
	    
	    String contestNumber = createdContest.getContestNumber();
    	System.out.println("CONTEST NUMBER IN ORGANIZER:: "+contestNumber);
	    logger.info("CONTEST NUMBER : {}", contestNumber);
	    
	    System.out.println("EXECUTION 4");
	    
	    // Check if the contestOrganizer list is null
	    List<Organizer> org = createdContest.getContestOrganizer();
	    if (org != null) {
	    	System.out.println("EXECUTION 5");
	    	System.out.println("Organizers list size: " + org.size());
	        for (Organizer organizer : org) {
	        	System.out.println("EXECUTION 6");
//	        	logger.info("CONTEST NUMBER : {}", contestNumber);
	            organizer.setContestNumber(contestNumber);
//	            logger.info("ORGANIZER : {}", organizer);
	            organizerRepository.save(organizer);
	        }
	    }else {
            System.out.println("Organizer list is null.");
        }
	    
	 // Check if the prize list is null
        List<Prize> prizes = createdContest.getPrize();
        if (prizes != null) {
            System.out.println("EXECUTION 8");
            System.out.println("Prizes list size: " + prizes.size());
            for (Prize prize : prizes) {
                System.out.println("EXECUTION 9");
                prize.setContestNumber(contestNumber);
                prizeRepository.save(prize);
            }
        } else {
            System.out.println("Prize list is null.");
        }

	    
	    System.out.println("EXECUTION 7");
	    return createdContest;
	}

}
