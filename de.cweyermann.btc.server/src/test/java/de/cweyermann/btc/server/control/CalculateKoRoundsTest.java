package de.cweyermann.btc.server.control;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.cweyermann.btc.server.entity.Group;
import de.cweyermann.btc.server.entity.KoRound;
import de.cweyermann.btc.server.entity.Match;
import de.cweyermann.btc.server.entity.Player;
import de.cweyermann.btc.server.entity.Team;

public class CalculateKoRoundsTest {
	private CalculateKoRounds calc;

	@Before
	public void init() {
		calc = new CalculateKoRounds();
	}

	@Test
	public void simpleKoTest() {
		Team team1 = buildTeamFrom("Winner");
		Team team2 = buildTeamFrom("Second");
		Team team3 = buildTeamFrom("Third");
		Team team4 = buildTeamFrom("Fourth");
		Group group = new Group();
		group.addMatch(new Match(team1, team3, "21-1", "21-0", null, false, false, 0, 0));
		group.addMatch(new Match(team2, team4, "21-2", "21-0", null, false, false, 1, 0));
		group.addMatch(new Match(team3, team4, "21-4", "21-0", null, false, false, 0, 1));
		group.addMatch(new Match(team1, team2, "21-3", "21-0", null, false, false, 1, 1));
		group.setKo(true);
		group.setPosition(1);

		calc.addKoRounds(group);
		List<KoRound> rounds = group.getKoRounds();

		assertEquals(2, rounds.size());
		assertEquals(0, rounds.get(0).getRoundNr().intValue());
		assertEquals(1, rounds.get(1).getRoundNr().intValue());
		assertEquals("Halbfinale", rounds.get(0).getName());
		assertEquals("Finale", rounds.get(1).getName());
	}

	@Test
	public void noKoGroup_doesNothing() {
		Group group = new Group();
		group.setKo(false);
		group.setPosition(1);

		calc.addKoRounds(group);
		assertEquals(0, group.getKoRounds().size());
	}
	
	private Team buildTeamFrom(String club) {
		Team team1 = new Team();
		Player player1 = new Player();
		player1.setSurname(club);
		team1.setPlayer1(player1);
		player1.setClub(club);
		return team1;
	}
}
