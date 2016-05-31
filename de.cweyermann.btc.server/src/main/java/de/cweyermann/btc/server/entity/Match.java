package de.cweyermann.btc.server.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Match extends AbstractEntity {

	private final static Pattern PATTERN = Pattern.compile("(\\d+)[-](\\d+)");

	private Player player1;

	private Player player2;

	private String set1;

	private String set2;

	private String set3;

	private boolean walkover;

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
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

	public boolean isWalkover() {
		return walkover;
	}

	public void setWalkover(boolean walkover) {
		this.walkover = walkover;
	}

	public Player getWinner() {
		boolean p1Won = didPlayer1win(set1);
		boolean p1Won2 = didPlayer1win(set2);
		boolean p1Won3 = didPlayer1win(set3);

		Player winner = null;
		if (p1Won && p1Won2) {
			winner = player1;
		} else if (!p1Won && !p1Won2) {
			winner = player2;
		} else if (p1Won3) {
			winner = player1;
		} else {
			winner = player2;
		}

		return winner;
	}

	private boolean didPlayer1win(String set) {
		boolean p1Won = false;

		if (set != null) {
			Matcher matcher = PATTERN.matcher(set);
			matcher.matches();
			Integer p1points = Integer.parseInt(matcher.group(1));
			Integer p2points = Integer.parseInt(matcher.group(2));
			p1Won = p1points > p2points;
		}
		return p1Won;
	}

	public Player getLooser() {
		Player winner = getWinner();
		Player looser = player2;

		if (winner.equals(player2)) {
			looser = player1;
		}

		return looser;
	}

}
