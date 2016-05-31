package de.cweyermann.btc.server.boundary.tpfile;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import de.cweyermann.btc.server.entity.Group;
import de.cweyermann.btc.server.entity.Team;

public class GetTeamsOfGroupTest {

	@Before
	public void setup() {
	}

	@Test
	public void minimal_twoTeams() throws SQLException {
		GetTeamsOfGroup uut = new GetTeamsOfGroup(TestUtils.getTpFilePath("minimum.TP"));

		Group group = new Group();
		group.setId(1);

		uut.fill(group);

		assertEquals(2, group.getTeams().size());
		assertEquals("Nachname1", group.getTeams().get(0).getPlayer1().getSurname());
		assertEquals("Vorname1", group.getTeams().get(0).getPlayer1().getFirstName());
		assertEquals("1234", group.getTeams().get(0).getPlayer1().getTournamentId());
		assertEquals("Nachname2", group.getTeams().get(1).getPlayer1().getSurname());
		assertEquals("Vorname2", group.getTeams().get(1).getPlayer1().getFirstName());
		assertEquals("5678", group.getTeams().get(1).getPlayer1().getTournamentId());
	}
}
