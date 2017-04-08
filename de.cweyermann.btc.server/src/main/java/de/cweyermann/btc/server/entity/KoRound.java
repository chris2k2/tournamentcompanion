package de.cweyermann.btc.server.entity;

import java.util.List;

public class KoRound extends AbstractEntity {

	private Integer roundNr;
	
	private String name;
	
	private List<Match> matches;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	public Integer getRoundNr() {
		return roundNr;
	}

	public void setRoundNr(Integer roundNr) {
		this.roundNr = roundNr;
	}
}
