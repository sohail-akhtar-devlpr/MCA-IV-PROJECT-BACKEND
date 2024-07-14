package com.webapp.codeathon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.codeathon.entity.Contest;
import com.webapp.codeathon.entity.Contestant;
import com.webapp.codeathon.entity.Prize;
import com.webapp.codeathon.entity.Winners;
import com.webapp.codeathon.repository.ContestantRepository;
import com.webapp.codeathon.repository.PrizeRepository;
import com.webapp.codeathon.repository.WinnersRepository;

@Service
public class WinnersService {
	
	@Autowired
	private ContestantRepository contestantRepository;
	
	@Autowired
	private WinnersRepository winnersRepository;
	
	@Autowired
	private PrizeRepository prizeRepository;

	
	public List<Winners>  saveWinners(List<Winners>  winnersList) {
		
		for (Winners winners : winnersList) {
            Contestant contestant = contestantRepository.findByEnrollmentNumber(winners.getContestantId());
            //find the contestId from the Contestant
            if (contestant != null) {
                Contest contest = contestant.getContest();
                int contestId = contest.getContestId();                            
                String winnerRank = winners.getWinnerRank();
                                            
                //Prize object
                Prize prize = prizeRepository.findByPrizeIdAndPrizeRank(contestId, winnerRank);
                
                winners.setContest(contest);
                winners.setPrize(prize);
                winnersRepository.save(winners);
            }
        }
        return winnersList;
	}
}
