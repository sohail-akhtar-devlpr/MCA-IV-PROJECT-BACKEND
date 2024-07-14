package com.webapp.codeathon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.codeathon.entity.Contest;
import com.webapp.codeathon.entity.Organizer;
import com.webapp.codeathon.repository.ContestRepository;
import com.webapp.codeathon.repository.OrganizerRepository;

@Service
public class OrganizerService {
	
	@Autowired
	private OrganizerRepository organizerRepository;
	
	@Autowired
	private ContestRepository contestRepository;

	
	public List<Organizer> saveOrganizer(List<Organizer> organizerList) {
		
		for (Organizer organizer : organizerList) {
            Contest contest = contestRepository.findByContestNumber(organizer.getContestNumber());
            if (contest != null) {
            	
            	organizer.setContest(contest);
                organizerRepository.save(organizer);
            }
        }
        return organizerList;
	}
}
