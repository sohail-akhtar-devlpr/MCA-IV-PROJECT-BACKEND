package com.webapp.codeathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.codeathon.entity.Prize;

@Repository
public interface PrizeRepository extends JpaRepository<Prize, Integer>{
	public Prize findByPrizeIdAndPrizeRank(int prizeId, String prizeRank);
}
