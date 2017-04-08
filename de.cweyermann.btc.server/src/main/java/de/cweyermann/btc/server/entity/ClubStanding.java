package de.cweyermann.btc.server.entity;

public class ClubStanding extends AbstractEntity {

    private int position;

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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    @Override
    public String toString() {
    	return clubName + "(" + points + ")";
    }

}
