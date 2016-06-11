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
	
	@Test
	public void duplicateStandings_comesAstwo()
	{
		Group group = new Group();
		group.addStanding(new Standing(null, 1, 0, 0, 0, 0, 0, 0));
		group.addStanding(new Standing(null, 2, 0, 0, 0, 0, 0, 0));
		
		List<Standing> standings = group.getStandings();
		assertEquals(1, standings.get(0).getMatchesFor());
		assertEquals(2, standings.get(1).getMatchesFor());
		
	}
	
	@Test
	public void matchesAdded_sorted() {
		Group group = new Group();
		group.addMatch(new Match(null, null, null, null, null, false, false, 1, 0));
		group.addMatch(new Match(null, null, null, null, null, false, false, 0, 0));
		
		List<Match> matches = group.getMatches();
		assertEquals(0, matches.get(0).getMatchnr());
		assertEquals(1, matches.get(1).getMatchnr());
	}

	@Test
	public void qualification_posMinus1()
	{
		Group group = new Group();
		group.setQualification(true);
		group.setPosition(1);
	
		assertEquals(-1, group.getBestPossiblePosition());
	}
	
	@Test
	public void notQualification_posKept()
	{
		Group group = new Group();
		group.setQualification(false);
		group.setPosition(3);
	
		assertEquals(3, group.getBestPossiblePosition());
	}
}
