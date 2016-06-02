package de.cweyermann.btc.server.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Match extends AbstractEntity {

	private final static Pattern PATTERN = Pattern.compile("(\\d+)[-](\\d+)");

	private Team team1;

	private Team team2;

	private String set1;

	private String set2;

	private String set3;

	private boolean walkoverTeam1;

	private boolean walkoverTeam2;

	private boolean done;

	public Match() {

	}

	public Match(Team team1, Team team2, String set1, String set2, String set3, boolean walkoverTeam1,
			boolean walkoverTeam2) {
		super();
		this.team1 = team1;
		this.team2 = team2;
		this.set1 = set1;
		this.set2 = set2;
		this.set3 = set3;
		this.walkoverTeam1 = walkoverTeam1;
		this.walkoverTeam2 = walkoverTeam2;
	}

	public boolean isWalkoverTeam1() {
		return walkoverTeam1;
	}

	public void setWalkoverTeam1(boolean walkoverTeam1) {
		this.walkoverTeam1 = walkoverTeam1;
	}

	public boolean isWalkoverTeam2() {
		return walkoverTeam2;
	}

	public void setWalkoverTeam2(boolean walkoverTeam2) {
		this.walkoverTeam2 = walkoverTeam2;
	}

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public String getSet1() {
		return set1;
	}

	public void setSet1(String set1) {
		this.set1 = set1;
	}

	public String getSet2() {
		return set2;
	}

	public void setSet2(String set2) {
		this.set2 = set2;
	}

	public String getSet3() {
		return set3;
	}

	public void setSet3(String set3) {
		this.set3 = set3;
	}

	public Team getWinner() {
		boolean p1Won = didTeam1win(set1);
		boolean p1Won2 = didTeam1win(set2);
		boolean p1Won3 = didTeam1win(set3);

		Team winner = null;
		if (p1Won && p1Won2) {
			winner = team1;
		} else if (!p1Won && !p1Won2) {
			winner = team2;
		} else if (p1Won3) {
			winner = team1;
		} else {
			winner = team2;
		}

		return winner;
	}

	private boolean didTeam1win(String set) {
		return didTeamWin(team1, set);
	}

	private boolean didTeamWin(Team team, String set) {
		boolean won = false;

		if (set != null) {
			int p1points = getPoints(team1, set);
			int p2points = getPoints(team2, set);
			won = p1points > p2points;
		}

		if (walkoverTeam1) {
			won = false;
		}

		if (walkoverTeam2) {
			won = true;
		}

		if (team2.equals(team)) {
			won = !won;
		}

		return won;
	}

	private int getPoints(Team team, String set) {
		int result = 0;

		if (set != null) {
			Matcher matcher = PATTERN.matcher(set);
			matcher.matches();
			int p1points = Integer.parseInt(matcher.group(1));
			int p2points = Integer.parseInt(matcher.group(2));

			if (team1.equals(team)) {
				result = p1points;
			} else {
				result = p2points;
			}
		}
		
		return result;
	}

	public Team getLooser() {
		Team winner = getWinner();
		Team looser = team2;

		if (winner.equals(team2)) {
			looser = team1;
		}

		return looser;
	}

	public int getSets(Team team) {
		int counter = 0;
		if (didTeamWin(team, set1)) {
			counter++;
		}
		if (didTeamWin(team, set2)) {
			counter++;
		}
		if (counter == 1 && didTeamWin(team, set3)) {
			counter++;
		}

		return counter;
	}

	public int getPoints(Team team) {
		int counter = 0;
		counter += getPoints(team, set1);
		counter += getPoints(team, set2);
		counter += getPoints(team, set3);
		
		return counter;
	}

	public boolean isDone() {
		return set1 != null || walkoverTeam1 || walkoverTeam2;
	}

}
