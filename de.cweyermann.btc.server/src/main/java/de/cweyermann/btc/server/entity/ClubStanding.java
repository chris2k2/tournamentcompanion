package de.cweyermann.btc.server.entity;

public class ClubStanding extends AbstractEntity {

	private String clubName;

	private double points = 0;

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public double getPoints() {
		return points;
	}

	public void addPoints(double points) {
		this.points += points;
	}

}
