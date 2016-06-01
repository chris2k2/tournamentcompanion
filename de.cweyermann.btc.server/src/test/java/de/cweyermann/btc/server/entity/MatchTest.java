package de.cweyermann.btc.server.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatchTest {

	@Test
	public void twoSets_WinnerIs1() {
		assertEquals("p1", getWinner("21-13", "21-12", null));
	}

	@Test
	public void threeSets_WinnerIs2() {
		assertEquals("p2", getWinner("21-0", "21-23", "21-23"));
	}

	@Test
	public void threeSets_LooserIs2() {
		assertEquals("p2", getLooser("21-0", "21-23", "30-29"));
	}

	@Test
	public void twoSets_LooserIs1() {
		assertEquals("p1", getLooser("21-23", "21-23", null));
	}

	private Match play(String set1, String set2, String set3) {
		Player p1 = new Player();
		p1.setSurname("p1");
		Player p2 = new Player();
		p2.setSurname("p2");

		Match match = new Match();
		match.setPlayer1(p1);
		match.setPlayer2(p2);
		match.setSet1(set1);
		match.setSet2(set2);
		match.setSet3(set3);

		return match;
	}

	private String getWinner(String set1, String set2, String set3) {
		return play(set1, set2, set3).getWinner().getSurname();
	}

	private String getLooser(String set1, String set2, String set3) {
		return play(set1, set2, set3).getLooser().getSurname();
	}
}
