package de.cweyermann.btc.server.entity;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class GroupTest {

	@Test
	public void standingsAdded_sorted() {
		Group group = new Group();

		group.addStanding(new Standing(null, 0, 0, 0, 0, 0, 0, 2));
		group.addStanding(new Standing(null, 0, 0, 0, 0, 0, 0, 1));
		
		List<Standing> standings = group.getStandings();
		assertEquals(1, standings.get(0).getRanking());
		assertEquals(2, standings.get(1).getRanking());
	}
}
