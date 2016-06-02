package de.cweyermann.btc.server.control;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.cweyermann.btc.server.entity.Group;
import de.cweyermann.btc.server.entity.Match;
import de.cweyermann.btc.server.entity.Standing;
import de.cweyermann.btc.server.entity.Team;

public class CalculateGroupStandingsTest {

	private CalculateGroupStandings calc;

	@Before
	public void setup() {
		calc = new CalculateGroupStandings();
	}

	@Test
	public void emptyGroup_noStandings() {
		Group group = new Group();

		calc.add(group);

		assertEquals(0, group.getStandings().size());
	}

	@Test
	public void twoTeamsOneWithMorePoints_First() {
		Group group = new Group();
		Team team1 = new Team();
		Team team2 = new Team();
		group.addTeam(team1);
		group.addTeam(team2);
		Match match = new Match(team1, team2, "21-3", "21-2", null, false, false);
		group.addMatch(match);
		
		calc.add(group);
		
		List<Standing> standings = group.getStandings();
		Standing first = standings.get(0);
		assertEquals(team1, first.getTeam());
		assertEquals(2, first.getMatchesFor());
		assertEquals(0, first.getMatchesAgainst());
		assertEquals(2, first.getSetsFor());
		assertEquals(0, first.getSetsAgainst());
		assertEquals(42, first.getPointsFor());
		assertEquals(5, first.getPointsAgainst());

		Standing second = standings.get(1);
		assertEquals(team2, second.getTeam());
		assertEquals(0, second.getMatchesFor());
		assertEquals(2, second.getMatchesAgainst());
		assertEquals(0, second.getSetsFor());
		assertEquals(2, second.getSetsAgainst());
		assertEquals(5, second.getPointsFor());
		assertEquals(42, second.getPointsAgainst());
	}

}
