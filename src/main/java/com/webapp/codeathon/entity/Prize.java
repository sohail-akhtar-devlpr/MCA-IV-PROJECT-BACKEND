package com.webapp.codeathon.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Prize {

	// 1
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prizeId;
	
	// 2
	private String contestNumber;

	// 3
	@Column(name = "prize_rank")
	private String prizeRank;

	// 4
	@ManyToOne
	@JoinColumn(name = "fk_contest_id")
	@JsonBackReference(value = "contest-prize")
	private Contest contest;

	// 5
	@OneToOne(mappedBy = "prize", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "prize-winner")
	private Winners winner;

	// 6
	private String prizeName;

	// 7
	private String prizeCategory;

	// 8
	private String prizeValue;

	// 9
	private String prizeDescription;

	public Prize() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Prize(int prizeId, String contestNumber, String prizeRank, Contest contest, Winners winner, String prizeName,
			String prizeCategory, String prizeValue, String prizeDescription) {
		super();
		this.prizeId = prizeId;
		this.contestNumber = contestNumber;
		this.prizeRank = prizeRank;
		this.contest = contest;
		this.winner = winner;
		this.prizeName = prizeName;
		this.prizeCategory = prizeCategory;
		this.prizeValue = prizeValue;
		this.prizeDescription = prizeDescription;
	}

	public int getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(int prizeId) {
		this.prizeId = prizeId;
	}

	public String getContestNumber() {
		return contestNumber;
	}

	public void setContestNumber(String contestNumber) {
		this.contestNumber = contestNumber;
	}

	public String getPrizeRank() {
		return prizeRank;
	}

	public void setPrizeRank(String prizeRank) {
		this.prizeRank = prizeRank;
	}

	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}

	public Winners getWinner() {
		return winner;
	}

	public void setWinner(Winners winner) {
		this.winner = winner;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getPrizeCategory() {
		return prizeCategory;
	}

	public void setPrizeCategory(String prizeCategory) {
		this.prizeCategory = prizeCategory;
	}

	public String getPrizeValue() {
		return prizeValue;
	}

	public void setPrizeValue(String prizeValue) {
		this.prizeValue = prizeValue;
	}

	public String getPrizeDescription() {
		return prizeDescription;
	}

	public void setPrizeDescription(String prizeDescription) {
		this.prizeDescription = prizeDescription;
	}

}
