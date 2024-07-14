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
public class Winners {

	// 1
	@Id
	@Column(name = "winner_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int winnerId;

	// 2
	private String winnerRank;

	//3
	private String contestantId;

	// 4
	@ManyToOne
	@JoinColumn(name = "fk_contest_id")
	@JsonBackReference(value = "contest-winners")
	private Contest contest;

	// 4
//	@OneToOne
//	@JoinColumn(name = "fk_contestant_id")
//	@JsonBackReference(value = "contestant-winner")
//	private Contestant contestant;

	// 5
	// is table me mera foreign key banega
	@OneToOne
	@JoinColumn(name = "fk_prize_id")
	@JsonBackReference(value = "prize-winner")
	private Prize prize;

	public Winners() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Winners(int winnerId, String winnerRank, String contestantId, Contest contest, Prize prize) {
		super();
		this.winnerId = winnerId;
		this.winnerRank = winnerRank;
		this.contestantId = contestantId;
		this.contest = contest;
		this.prize = prize;
	}

	public int getWinnerId() {
		return winnerId;
	}

	public void setWinnerId(int winnerId) {
		this.winnerId = winnerId;
	}

	public String getWinnerRank() {
		return winnerRank;
	}

	public void setWinnerRank(String winnerRank) {
		this.winnerRank = winnerRank;
	}

	public String getContestantId() {
		return contestantId;
	}

	public void setContestantId(String contestantId) {
		this.contestantId = contestantId;
	}

	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}

	public Prize getPrize() {
		return prize;
	}

	public void setPrize(Prize prize) {
		this.prize = prize;
	}

}
