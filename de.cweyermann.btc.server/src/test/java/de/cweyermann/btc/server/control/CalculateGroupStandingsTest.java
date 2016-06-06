package de.cweyermann.btc.server.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
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
		Team team1 = new Team();
		Team team2 = new Team();
		List<Match> matches = new ArrayList<Match>();
		matches.add(new Match(team1, team2, "21-3", "21-2", null, false, false, 0, 0));

		Group group = buildGroup(matches, team1, team2);

		List<Standing> standings = group.getStandings();
		Standing first = standings.get(0);
		assertEquals(team1, first.getTeam());
		assertEquals(2, first.getMatchesFor());
		assertEquals(0, first.getMatchesAgainst());
		assertEquals(2, first.getSetsFor());
		assertEquals(0, first.getSetsAgainst());
		assertEquals(42, first.getPointsFor());
		assertEquals(5, first.getPointsAgainst());
		assertEquals(1, first.getRanking());

		Standing second = standings.get(1);
		assertEquals(team2, second.getTeam());
		assertEquals(0, second.getMatchesFor());
		assertEquals(2, second.getMatchesAgainst());
		assertEquals(0, second.getSetsFor());
		assertEquals(2, second.getSetsAgainst());
		assertEquals(5, second.getPointsFor());
		assertEquals(42, second.getPointsAgainst());
		assertEquals(2, second.getRanking());
	}

	@Test
	public void team2HasMorePoints_isFirst() {
		Team team1 = new Team();
		Team team2 = new Team();
		List<Match> matches = new ArrayList<Match>();
		matches.add(new Match(team1, team2, "21-3", "21-23", "12-21", false, false, 0, 0));

		Group group = buildGroup(matches, team1, team2);

		assertEquals(team2, group.getStandings().get(0).getTeam());
		assertEquals(team1, group.getStandings().get(1).getTeam());
	}

	@Test
	public void team3HasMorePoints_isFirst() {
		Team team1 = new Team();
		Team team2 = new Team();
		Team team3 = new Team();
		List<Match> matches = new ArrayList<Match>();
		matches.add(new Match(team1, team2, "21-3", "21-23", "12-21", false, false, 0, 0));
		matches.add(new Match(team1, team3, "21-3", "21-23", "12-21", false, false, 0, 0));
		matches.add(new Match(team2, team3, "21-3", "21-23", "12-21", false, false, 0, 0));

		Group group = buildGroup(matches, team1, team2, team3);

		assertEquals(team3, group.getStandings().get(0).getTeam());
		assertEquals(team2, group.getStandings().get(1).getTeam());
		assertEquals(team1, group.getStandings().get(2).getTeam());
	}

	@Test
	public void twoTeamsEqualTeam2WonDirectDuel_Leads() {
		Team team1 = new Team();
		Team team2 = new Team();
		Team team3 = new Team();
		Team team4 = new Team();
		List<Match> matches = buildDoubleDraw(team1, team2, team3, team4);

		Group group = buildGroup(matches, team1, team2, team3, team4);

		assertEquals(Arrays.asList(team2, team1, team3, team4), getTeamStandings(group));
	}

	@Test
	public void twoTeamsEqualTeamWonDirectDuel_LeadsRegardlessOfSeed() {
		Team team1 = new Team();
		Team team2 = new Team();
		Team team3 = new Team();
		Team team4 = new Team();
		List<Match> matches = new ArrayList<Match>();
		matches.add(new Match(team2, team1, "25-23", "25-23", null, false, false, 0, 0));
		matches.add(new Match(team1, team3, "21-3", "21-2", null, false, false, 0, 0));
		matches.add(new Match(team1, team4, "21-1", "21-1", null, false, false, 0, 0));
		matches.add(new Match(team2, team3, "21-3", "21-2", null, false, false, 0, 0));
		matches.add(new Match(team2, team4, "21-23", "21-23", null, false, false, 0, 0));
		matches.add(new Match(team3, team4, "21-3", "21-2", null, false, false, 0, 0));

		Group group = buildGroup(matches, team1, team2, team4, team3);

		assertEquals(Arrays.asList(team2, team1, team3, team4), getTeamStandings(group));
	}

	@Test
	public void threeTimesTied_NoDirectDuelButSetDifference() {
		Team team1 = new Team();
		Team team2 = new Team();
		Team team3 = new Team();
		Team team4 = new Team();

		List<Match> matches = new ArrayList<Match>();
		matches.add(new Match(team1, team2, "21-0", "21-0", null, false, false, 0, 0));
		matches.add(new Match(team1, team3, "21-23", "25-27", null, false, false, 0, 0));
		matches.add(new Match(team1, team4, "21-1", "21-1", null, false, false, 0, 0));
		matches.add(new Match(team2, team3, "21-3", "21-23", "21-5", false, false, 0, 0));
		matches.add(new Match(team2, team4, "21-0", "21-0", null, false, false, 0, 0));
		matches.add(new Match(team3, team4, "21-3", "21-2", null, false, false, 0, 0));

		Group group = buildGroup(matches, team1, team2, team3, team4);

		assertEquals(Arrays.asList(team3, team1, team2, team4), getTeamStandings(group));
	}

	@Test
	public void nonPlayedMatch_ignored() {
		Team team1 = new Team();
		Team team2 = new Team();

		List<Match> matches = new ArrayList<Match>();
		matches.add(new Match(team1, team2, null, null, null, false, false, 0, 0));

		Group group = buildGroup(matches, team1, team2);

		assertEquals(0, group.getStandings().get(0).getMatchesFor());
		assertEquals(0, group.getStandings().get(1).getMatchesFor());
		assertEquals(0, group.getStandings().get(0).getPointsFor());
		assertEquals(0, group.getStandings().get(1).getPointsFor());
	}

	@Test
	public void twoTeamsEqualTeam1WonDirectDuel_Leads() {
		Team team1 = new Team();
		Team team2 = new Team();
		Team team3 = new Team();
		Team team4 = new Team();
		List<Match> matches = new ArrayList<Match>();
		matches.add(new Match(team1, team2, "25-23", "25-23", null, false, false, 0, 0));
		matches.add(new Match(team1, team3, "21-3", "21-2", null, false, false, 0, 0));
		matches.add(new Match(team1, team4, "21-23", "21-23", null, false, false, 0, 0));
		matches.add(new Match(team2, team3, "21-3", "21-2", null, false, false, 0, 0));
		matches.add(new Match(team2, team4, "25-23", "25-23", null, false, false, 0, 0));
		matches.add(new Match(team3, team4, "21-3", "21-2", null, false, false, 0, 0));

		Group group = buildGroup(matches, team1, team2, team3, team4);

		assertEquals(Arrays.asList(team1, team2, team3, team4), getTeamStandings(group));
	}

	@Test
	public void threeTeamsOneBetterSetsOther2DirectDuel_FirstBetterSetsThanDD() {
		Team team1 = new Team();
		Team team2 = new Team();
		Team team3 = new Team();
		Team team4 = new Team();
		List<Match> matches = new ArrayList<Match>();
		matches.add(new Match(team1, team2, "0-21", "0-21", null, false, false, 0, 0));
		matches.add(new Match(team1, team3, "21-0", "21-0", null, false, false, 0, 0));
		matches.add(new Match(team1, team4, "21-0", "0-21", "21-0", false, false, 0, 0));
		matches.add(new Match(team2, team3, "0-21", "0-21", null, false, false, 0, 0));
		matches.add(new Match(team2, team4, "21-0", "0-21", "21-0", false, false, 0, 0));
		matches.add(new Match(team3, team4, "21-0", "21-0", null, false, false, 0, 0));

		Group group = buildGroup(matches, team1, team2, team3, team4);

		assertEquals(Arrays.asList(team3, team2, team1, team4), getTeamStandings(group));
	}

	@Test
	public void threeTimesTiedAfterSets_PointsAccounted() {
		Team team1 = new Team();
		Team team2 = new Team();
		Team team3 = new Team();
		Team team4 = new Team();
		List<Match> matches = new ArrayList<Match>();
		matches.add(new Match(team1, team4, "21-0", "21-0", null, false, false, 0, 0));
		matches.add(new Match(team2, team4, "21-0", "21-19", null, false, false, 0, 0));
		matches.add(new Match(team3, team4, "21-0", "21-5", null, false, false, 0, 0));

		Group group = buildGroup(matches, team1, team2, team3, team4);

		assertEquals(Arrays.asList(team1, team3, team2, team4), getTeamStandings(group));
	}

	@Test
	public void twoTeamsNoDirectDuel_SetsCount() {
		Team team1 = new Team();
		Team team2 = new Team();
		Team team3 = new Team();

		List<Match> matches = new ArrayList<Match>();
		matches.add(new Match(team1, team3, "21-0", "12-21", "21-5", false, false, 0, 0));
		matches.add(new Match(team2, team3, "21-0", "21-0", null, false, false, 0, 0));

		Group group = buildGroup(matches, team1, team2, team3);

		assertEquals(Arrays.asList(team2, team1, team3), getTeamStandings(group));
	}

	@Test
	public void twoTeamsNoDirectDuel2_SetsCount() {
		Team team1 = new Team();
		Team team2 = new Team();
		Team team3 = new Team();

		List<Match> matches = new ArrayList<Match>();
		matches.add(new Match(team1, team3, "21-23", "27-25", "0-21", false, false, 0, 0));
		matches.add(new Match(team1, team2, "21-23", "21-23", null, false, false, 0, 0));

		Group group = buildGroup(matches, team1, team2, team3);

		assertEquals(Arrays.asList(team2, team3, team1), getTeamStandings(group));
	}

	@Test
	public void twoTeamsNoDirectDuel2BothIn2Sets_PointsCount() {
		Team team1 = new Team();
		Team team2 = new Team();
		Team team3 = new Team();

		List<Match> matches = new ArrayList<Match>();
		matches.add(new Match(team1, team3, "21-23", "0-21", null, false, false, 0, 0));
		matches.add(new Match(team1, team2, "21-23", "21-23", null, false, false, 0, 0));

		Group group = buildGroup(matches, team1, team2, team3);

		assertEquals(Arrays.asList(team3, team2, team1), getTeamStandings(group));
	}

	@Test
	public void kophase_doesNothing() {
		Team team1 = new Team();
		Team team2 = new Team();

		Group g = new Group();
		g.addTeam(team1);
		g.addTeam(team2);
		g.addMatch(new Match(team1, team2, "0-21", "0-21", null, false, false, 0, 0));
		g.setKo(true);
		
		calc.add(g);
		
		assertTrue(g.getStandings().isEmpty());
	}

	@Test
	public void multiwayTieSettledByPoints() {
		Team team1 = new Team();
		Team team2 = new Team();
		Team team3 = new Team();

		List<Match> matches = new ArrayList<Match>();
		matches.add(new Match(team1, team3, "21-0", "21-19", null, false, false, 0, 0));
		matches.add(new Match(team1, team2, "0-21", "0-21", null, false, false, 0, 0));
		matches.add(new Match(team2, team3, "0-21", "0-21", null, false, false, 0, 0));

		Group group = buildGroup(matches, team1, team2, team3);

		assertEquals(Arrays.asList(team3, team2, team1), getTeamStandings(group));

	}

	private List<Match> buildDoubleDraw(Team team1, Team team2, Team team3, Team team4) {
		List<Match> matches = new ArrayList<Match>();
		matches.add(new Match(team1, team2, "21-23", "21-23", null, false, false, 0, 0));
		matches.add(new Match(team1, team3, "21-3", "21-2", null, false, false, 0, 0));
		matches.add(new Match(team1, team4, "21-1", "21-1", null, false, false, 0, 0));
		matches.add(new Match(team2, team3, "21-3", "21-2", null, false, false, 0, 0));
		matches.add(new Match(team2, team4, "21-23", "21-23", null, false, false, 0, 0));
		matches.add(new Match(team3, team4, "21-3", "21-2", null, false, false, 0, 0));
		return matches;
	}

	private List<Team> getTeamStandings(Group group) {
		List<Team> result = new ArrayList<>();
		for (Standing s : group.getStandings()) {
			result.add(s.getTeam());
		}
		return result;
	}

	private Group buildGroup(List<Match> matches, Team... teams) {
		Group group = new Group();

		for (Team t : teams) {
			group.addTeam(t);
			t.setId(group.getTeams().indexOf(t) + 1);
		}

		for (Match m : matches) {
			group.addMatch(m);
		}

		calc.add(group);

		return group;
	}

}
