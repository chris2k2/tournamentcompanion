package de.cweyermann.btc.server.boundary.tpfile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import de.cweyermann.btc.server.entity.Group;
import de.cweyermann.btc.server.entity.Match;

public class GetGroupTest {

	@Test
	public void minimal_twoTeams() throws SQLException {
		GetGroup uut = new GetGroup(TestUtils.getConnection("minimum.TP"));

		Group group = uut.get(1);

		assertEquals(2, group.getTeams().size());
		assertEquals("Nachname1", group.getTeams().get(0).getPlayer1().getSurname());
		assertEquals("Vorname1", group.getTeams().get(0).getPlayer1().getFirstName());
		assertEquals("1234", group.getTeams().get(0).getPlayer1().getTournamentId());
		assertEquals("Nachname2", group.getTeams().get(1).getPlayer1().getSurname());
		assertEquals("Vorname2", group.getTeams().get(1).getPlayer1().getFirstName());
		assertEquals("5678", group.getTeams().get(1).getPlayer1().getTournamentId());
		Match match = group.getMatches().get(0);
		assertEquals(1, group.getMatches().size());
		assertEquals(2, match.getTeam1().getId());
		assertEquals(1, match.getTeam2().getId());
		assertEquals("4-21", match.getSet1());
		assertEquals("4-21", match.getSet2());
		assertEquals(false, match.isWalkoverTeam1());
		assertEquals(false, match.isWalkoverTeam2());
	}
	
	@Test
	public void demo_worksOnDoubles()
	{
		GetGroup uut = new GetGroup(TestUtils.getConnection("demo.tp"));

		Group group = uut.get(1);

		assertEquals(5, group.getTeams().size());
		assertEquals("Bakker", group.getTeams().get(0).getPlayer1().getSurname());
		assertEquals("Velden", group.getTeams().get(0).getPlayer2().getSurname());
	}

	@Test
	public void differentDraws_distinguishes()
	{
		GetGroup uut = new GetGroup(TestUtils.getConnection("ko.tp"));

		Group group = uut.get(10);

		assertEquals("MS - 3", group.getName());
		assertTrue(group.isKo());
		assertEquals(3, group.getMatches().size());
	}

	
}
