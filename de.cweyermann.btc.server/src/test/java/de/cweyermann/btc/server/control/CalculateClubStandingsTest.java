package de.cweyermann.btc.server.control;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import de.cweyermann.btc.server.entity.ClubStandings;
import de.cweyermann.btc.server.entity.Group;
import de.cweyermann.btc.server.entity.Player;
import de.cweyermann.btc.server.entity.Team;

public class CalculateClubStandingsTest {

	private CalculateClubStandings calc;

	@Before
	public void init() {
		calc = new CalculateClubStandings(10, 6, 4, 2, 1);
	}

	@Test
	public void oneGroupfirstPlace_first() {
		Team team1 = buildTeamFrom("Club1");
		Team team2 = buildTeamFrom("Club2");

		Group group = BuilderUtils.getGroupInRanking(team1, team2);
		ClubStandings standings = calc.execute(Arrays.asList(group));

		assertEquals(10, standings.getStandings().get(0).getPoints(), 0.001);
		assertEquals("Club1", standings.getStandings().get(0).getClubName());
	}

	@Test
	public void oneGroupfirstPlaceButQualification_noPoints() {
		Team team1 = buildTeamFrom("Club1");
		Team team2 = buildTeamFrom("Club2");

		Group group = BuilderUtils.getGroupInRanking(team1, team2);
		group.setQualification(true);
		ClubStandings standings = calc.execute(Arrays.asList(group));

		assertEquals(0, standings.getStandings().get(0).getPoints(), 0.001);
	}

	@Test
	public void twoGroups_firstPlace() {
		ClubStandings standings = twoGroupsWonByClub1("Club1", "other");

		assertEquals(20, standings.getStandings().get(0).getPoints(), 0.001);
		assertEquals("Club1", standings.getStandings().get(0).getClubName());
	}

	@Test
	public void twoGroups_oneWonOneLost() {
		Team team1 = buildTeamFrom("Club1");
		Team team2 = buildTeamFrom("Club2");

		Group group = BuilderUtils.getGroupInRanking(team1, team2);
		Group group2 = BuilderUtils.getGroupInRanking(team2, team1);
		ClubStandings standings = calc.execute(Arrays.asList(group, group2));

		assertEquals(16, standings.getStandings().get(0).getPoints(), 0.001);
		assertEquals("Club1", standings.getStandings().get(0).getClubName());
	}

	@Test
	public void oneBigGroup_allTeamsCalculated() {
		Group group = BuilderUtils.getGroupInRanking(buildTeamFrom("Club1"), buildTeamFrom("Club2"),
				buildTeamFrom("Club3"), buildTeamFrom("Club4"), buildTeamFrom("Club5"));

		ClubStandings standings = calc.execute(Arrays.asList(group));

		assertEquals(10, standings.getStandings().get(0).getPoints(), 0.001);
		assertEquals("Club1", standings.getStandings().get(0).getClubName());
		assertEquals(6, standings.getStandings().get(1).getPoints(), 0.001);
		assertEquals("Club2", standings.getStandings().get(1).getClubName());
		assertEquals(4, standings.getStandings().get(2).getPoints(), 0.001);
		assertEquals("Club3", standings.getStandings().get(2).getClubName());
		assertEquals(2, standings.getStandings().get(3).getPoints(), 0.001);
		assertEquals("Club4", standings.getStandings().get(3).getClubName());
		assertEquals(0, standings.getStandings().get(4).getPoints(), 0.001);
		assertEquals("Club5", standings.getStandings().get(4).getClubName());
	}

	@Test
	public void twoTeamsAsked_allTeamsCalculated() {
		ClubStandings standings = twoGroupsWonByClub1("Club_winner", "Club1");

		assertEquals(20, standings.getStandings().get(0).getPoints(), 0.001);
		assertEquals("Club_winner", standings.getStandings().get(0).getClubName());
		assertEquals(12, standings.getStandings().get(1).getPoints(), 0.001);
		assertEquals("Club1", standings.getStandings().get(1).getClubName());

	}

	private ClubStandings twoGroupsWonByClub1(String clubname, String otherClub) {
		Team team1 = buildTeamFrom(clubname);
		Team team2 = buildTeamFrom(otherClub);

		Group group = BuilderUtils.getGroupInRanking(team1, team2);
		Group group2 = BuilderUtils.getGroupInRanking(team1, team2);

		ClubStandings standings = calc.execute(Arrays.asList(group, group2));
		return standings;
	}

	private Team buildTeamFrom(String club) {
		Team team1 = new Team();
		Player player1 = new Player();
		team1.setPlayer1(player1);
		player1.setClub(club);
		return team1;
	}
}
