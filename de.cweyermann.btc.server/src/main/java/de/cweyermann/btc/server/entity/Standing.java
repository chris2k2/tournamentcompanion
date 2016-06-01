package de.cweyermann.btc.server.entity;

public class Standing extends AbstractEntity implements Comparable<Standing> {

	private Team player;

	private int matchesFor;

	private int matchesAgainst;

	private int setsFor;

	private int setsAgainst;

	private int pointsFor;

	private int pointsAgainst;

	private int ranking;

	public Team getTeam() {
		return player;
	}

	public void setTeam(Team player) {
		this.player = player;
	}

	public int getMatchesFor() {
		return matchesFor;
	}

	public void setMatchesFor(int matchesFor) {
		this.matchesFor = matchesFor;
	}

	public int getMatchesAgainst() {
		return matchesAgainst;
	}

	public void setMatchesAgainst(int matchesAgainst) {
		this.matchesAgainst = matchesAgainst;
	}

	public int getSetsFor() {
		return setsFor;
	}

	public void setSetsFor(int setsFor) {
		this.setsFor = setsFor;
	}

	public int getSetsAgainst() {
		return setsAgainst;
	}

	public void setSetsAgainst(int setsAgainst) {
		this.setsAgainst = setsAgainst;
	}

	public int getPointsFor() {
		return pointsFor;
	}

	public void setPointsFor(int pointsFor) {
		this.pointsFor = pointsFor;
	}

	public int getPointsAgainst() {
		return pointsAgainst;
	}

	public void setPointsAgainst(int pointsAgainst) {
		this.pointsAgainst = pointsAgainst;
	}

	public Standing(Team player, int matchesFor, int matchesAgainst, int setsFor, int setsAgainst, int pointsFor,
			int pointsAgainst, int ranking) {
		this.player = player;
		this.matchesFor = matchesFor;
		this.matchesAgainst = matchesAgainst;
		this.setsFor = setsFor;
		this.setsAgainst = setsAgainst;
		this.pointsFor = pointsFor;
		this.pointsAgainst = pointsAgainst;
		this.ranking = ranking;
	}

	@Override
	public int compareTo(Standing o) {
		return ranking - o.ranking;
	}


}
