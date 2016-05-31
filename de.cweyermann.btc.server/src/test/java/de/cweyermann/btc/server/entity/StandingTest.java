package de.cweyermann.btc.server.entity;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StandingTest {

	@Test
	public void rankingHigher_Minus() {
		Standing playerA = new Standing(null, 2, 0, 0, 0, 0, 0, 1);
		Standing playerB = new Standing(null, 1, 0, 0, 0, 0, 0, 2);

		assertTrue(playerA.compareTo(playerB) < 0);
	}


	@Test
	public void rankingHigher_Plus() {
		Standing playerA = new Standing(null, 2, 0, 0, 0, 0, 0, 3);
		Standing playerB = new Standing(null, 1, 0, 0, 0, 0, 0, 2);

		assertTrue(playerA.compareTo(playerB) > 0);
	}


	@Test
	public void rankingSame_0() {
		Standing playerA = new Standing(null, 2, 0, 0, 0, 0, 0, 1);
		Standing playerB = new Standing(null, 1, 0, 0, 0, 0, 0, 1);

		assertTrue(playerA.compareTo(playerB) == 0);
	}

	
}
