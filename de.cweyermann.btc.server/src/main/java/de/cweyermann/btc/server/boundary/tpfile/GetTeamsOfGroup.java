package de.cweyermann.btc.server.boundary.tpfile;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.cweyermann.btc.server.entity.Group;
import de.cweyermann.btc.server.entity.Player;
import de.cweyermann.btc.server.entity.Team;

public class GetTeamsOfGroup extends AbstractTpFileControl {

	public GetTeamsOfGroup(String filePath) {
		super(filePath);
	}

	public void fill(Group group) {
		ResultSet resultSet = executeSql("select distinct player1.name, player1.firstname, player1.memberid, "
				+ "player2.name, player2.firstname, player2.memberid, Entry.id from "
				+ "Draw INNER JOIN PlayerMatch ON Draw.id = PlayerMatch.draw, "
				+ "PlayerMatch INNER JOIN Entry ON PlayerMatch.entry = Entry.id, "
				+ "Entry INNER JOIN Player as player1 ON Entry.player1 = player1.id, "
				+ "Entry LEFT JOIN Player as player2 ON Entry.player2 = player2.id;");

		try {
			convert(group, resultSet);
		} catch (SQLException e) {
			throw new TpFileConnectionInvalid(e);
		}
	}

	private void convert(Group group, ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			Team currentTeam = new Team();
			currentTeam.setId(resultSet.getInt("Entry.id"));

			Player player1 = new Player(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));

			Player player2 = null;
			String name2 = resultSet.getString(3);
			if (name2 != null) {
				player2 = new Player(name2, resultSet.getString(4), resultSet.getString(5));
			}

			currentTeam.setPlayer1(player1);
			currentTeam.setPlayer2(player2);

			group.addTeam(currentTeam);
		}
	}
}
