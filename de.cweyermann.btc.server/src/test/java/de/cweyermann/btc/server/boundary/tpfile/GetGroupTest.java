package de.cweyermann.btc.server.boundary.tpfile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import de.cweyermann.btc.server.entity.Group;
import de.cweyermann.btc.server.entity.Group.GroupType;
import de.cweyermann.btc.server.entity.Match;
import de.cweyermann.btc.server.entity.Player;

public class GetGroupTest {

	@Test
	public void minimal_twoTeams() throws SQLException {
		GetGroup uut = new GetGroup(TpFileUtils.getConnection("minimum.TP"));

		Group group = uut.get(1);

		assertEquals(2, group.getTeams().size());
		assertEquals("Nachname1", group.getTeams().get(0).getPlayer1().getSurname());
		assertEquals("Vorname1", group.getTeams().get(0).getPlayer1().getFirstName());
		assertNull(group.getTeams().get(0).getPlayer1().getClub());
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
		assertEquals(GroupType.KO, group.getType());
	}

	@Test
	public void demo_worksOnDoubles() {
		GetGroup uut = new GetGroup(TpFileUtils.getConnection("demo.tp"));

		Group group = uut.get(1);

		assertEquals(5, group.getTeams().size());
		Player player1 = group.getTeams().get(0).getPlayer1();
		Player player2 = group.getTeams().get(0).getPlayer2();
		assertEquals("Bakker", player1.getSurname());
		assertEquals("Bakkum", player1.getClub());
		assertEquals("Velden", player2.getSurname());
		assertEquals("Bakkum", player2.getClub());
	}

	@Test
	public void differentDraws_distinguishes() {
		GetGroup uut = new GetGroup(TpFileUtils.getConnection("ko.tp"));

		Group group = uut.get(10);

		assertEquals("MS - 3", group.getName());
		assertTrue(group.isKo());
		assertEquals(3, group.getMatches().size());
	}

	@Test
	public void moreDraws_qualifcationIsTakenIntoAccount() {
		GetGroup uut = new GetGroup(TpFileUtils.getConnection("bigdemo.tp"));

		Group groupA = uut.get(1);
		Group groupD = uut.get(4);
		Group groupKo1 = uut.get(5);
		Group groupKo9 = uut.get(6);

		assertEquals(GroupType.QUALIFICATION, groupA.getType());
		assertEquals(GroupType.QUALIFICATION, groupD.getType());
		assertEquals(GroupType.KO, groupKo1.getType());
		assertEquals(GroupType.LOOSERSKO, groupKo9.getType());
	}

	@Test
	public void bigDemo_groupPhaseAvoidsReverseHomeAwayTrap() {
		GetGroup uut = new GetGroup(TpFileUtils.getConnection("bigdemo.tp"));

		Group groupA = uut.get(1);

		assertEquals(6, groupA.getMatches().size());
	}
}
