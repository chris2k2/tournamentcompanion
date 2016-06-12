package de.cweyermann.btc.server.entity;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class ClubStandingsTest {

	@Test
	public void standingsAdded_comeOutSorted() {
		ClubStandings standings = new ClubStandings();
		ClubStanding standing = new ClubStanding();
		standing.addPoints(123);
		ClubStanding standing2 = new ClubStanding();
		standing2.addPoints(1);
		
		standings.addStandings(standing2);
		standings.addStandings(standing);
		
		assertEquals(Arrays.asList(standing, standing2), standings.getStandings());
	}

	@Test
	public void standingsAddedDouble_comeOutSorted() {
		ClubStandings standings = new ClubStandings();
		ClubStanding standing = new ClubStanding();
		standing.addPoints(5.1);
		ClubStanding standing2 = new ClubStanding();
		standing2.addPoints(5);
		
		standings.addStandings(standing2);
		standings.addStandings(standing);
		
		assertEquals(Arrays.asList(standing, standing2), standings.getStandings());
	}
}
