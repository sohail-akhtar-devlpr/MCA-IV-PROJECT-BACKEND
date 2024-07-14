package com.webapp.codeathon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.codeathon.entity.Contest;
import com.webapp.codeathon.entity.Prize;
import com.webapp.codeathon.repository.ContestRepository;
import com.webapp.codeathon.repository.PrizeRepository;

@Service
public class PrizeService {
	
	@Autowired
	private PrizeRepository prizeRepository;
	
	@Autowired
	private ContestRepository contestRepository;

	public List<Prize> savePrize(List<Prize> prizeList) {
		
		for (Prize prize : prizeList) {
            Contest contest = contestRepository.findByContestNumber(prize.getContestNumber());
            if (contest != null) {
//                Contest contest = contestant.getContest();
                prize.setContest(contest);
                prizeRepository.save(prize);
            }
        }
        return prizeList;
	}
}
