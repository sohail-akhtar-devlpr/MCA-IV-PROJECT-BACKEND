package com.webapp.codeathon.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.webapp.codeathon.entity.ContestQuestionSet;
//import com.webapp.codeathon.repository.ContestQuestionSetRepository;
//
//@Service
//public class ContestQuestionSetService {
//
//	@Autowired
//	private ContestQuestionSetRepository contestQuestionSetRepository;
//	
//	public ContestQuestionSet createContestQuestionSet(ContestQuestionSet contestQuestionSet) {
//		
//		return contestQuestionSetRepository.save(contestQuestionSet);
//	}
//}


//package com.webapp.codeathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.codeathon.entity.Contest;
import com.webapp.codeathon.entity.ContestQuestionSet;
import com.webapp.codeathon.entity.Questions;
import com.webapp.codeathon.entity.Examples;
import com.webapp.codeathon.entity.QuestionConstraints;
import com.webapp.codeathon.repository.ContestQuestionSetRepository;
import com.webapp.codeathon.repository.ContestRepository;

@Service
public class ContestQuestionSetService {

    @Autowired
    private ContestQuestionSetRepository contestQuestionSetRepository;
    
    @Autowired
    private ContestRepository contestRepository;

    public ContestQuestionSet createContestQuestionSet(ContestQuestionSet contestQuestionSet) {
        
    	Contest contest = 
    			contestRepository.findByContestNumber(contestQuestionSet.getContestNumber());
    	
        if( contest != null) {
        	contestQuestionSet.setContest(contest);
    		for (Questions question : contestQuestionSet.getQuestions()) {
                question.setContestQuestionSet(contestQuestionSet);
                for (Examples example : question.getExamples()) {
                    example.setQuestions(question);
                }
                for (QuestionConstraints constraint : question.getConstraints()) {
                	
                	System.out.println("Question Constraints--"+constraint);
                    constraint.setQuestions(question);
                }
            }
            return contestQuestionSetRepository.save(contestQuestionSet);
    	}
    	return null;
    }
}

