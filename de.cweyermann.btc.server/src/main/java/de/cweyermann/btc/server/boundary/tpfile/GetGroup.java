package de.cweyermann.btc.server.boundary.tpfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import de.cweyermann.btc.server.entity.Group;
import de.cweyermann.btc.server.entity.Match;
import de.cweyermann.btc.server.entity.Player;
import de.cweyermann.btc.server.entity.Team;

public class GetGroup extends AbstractTpFileControl {

	private Map<Integer, Team> teamById = new HashMap<>();

	public GetGroup(Connection filePath) {
		super(filePath);
	}

	public Group get(int id) {
		Group group = new Group();
		group.setId(id);

		fillTeams(group);
		fillMatches(group);

		return group;
	}

	public void fillMatches(Group group) {
		int id = group.getId();
		ResultSet resultSet = executeSql("SELECT hometeam.entry, awayteam.entry, "
				+ "thematch.team1set1, thematch.team2set1, thematch.team1set2, "
				+ "thematch.team2set2, thematch.team1set3, thematch.team2set3, "
				+ "hometeam.walkover, awayteam.walkover	"
				+ "FROM	PlayerMatch theMatch INNER JOIN PlayerMatch AS hometeam ON thematch.van1 = hometeam.planning "
				+ "INNER JOIN PlayerMatch AS awayteam ON thematch.van2 = awayteam.planning " + "WHERE thematch.draw = "
				+ id + " AND hometeam.draw=" + id + " AND awayteam.draw=" + id + ";");

		try {
			convertMatches(group, resultSet);
		} catch (SQLException e) {
			throw new TpFileConnectionInvalid(e);
		}
	}

	private void convertMatches(Group group, ResultSet rs) throws SQLException {
		while (rs.next()) {
			Match match = new Match();
			match.setTeam1(teamById.get(rs.getInt(1)));
			match.setTeam2(teamById.get(rs.getInt(2)));
			match.setSet1(buildSet(rs, 3, 4));
			match.setSet2(buildSet(rs, 5, 6));
			match.setSet3(buildSet(rs, 7, 8));
			match.setWalkoverTeam1(rs.getBoolean(9));
			match.setWalkoverTeam2(rs.getBoolean(10));

			group.addMatch(match);
		}
	}

	private String buildSet(ResultSet rs, int team1Column, int team2Column) throws SQLException {
		int homePoints = rs.getInt(team1Column);
		int awayPoints = rs.getInt(team2Column);
		String result = null;
		if (homePoints > 0 || awayPoints > 0) {
			result = homePoints + "-" + awayPoints;
		}
		return result;
	}

	public void fillTeams(Group group) {
		ResultSet resultSet = executeSql("select distinct player1.name, player1.firstname, player1.memberid, "
				+ "player2.name, player2.firstname, player2.memberid, Entry.id from "
				+ "Draw INNER JOIN PlayerMatch ON Draw.id = PlayerMatch.draw, "
				+ "PlayerMatch INNER JOIN Entry ON PlayerMatch.entry = Entry.id, "
				+ "Entry INNER JOIN Player as player1 ON Entry.player1 = player1.id, "
				+ "Entry LEFT JOIN Player as player2 ON Entry.player2 = player2.id " + "WHERE draw.id = "
				+ group.getId() + "; ");

		try {
			convertTeams(group, resultSet);
		} catch (SQLException e) {
			throw new TpFileConnectionInvalid(e);
		}
	}

	private void convertTeams(Group group, ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			Team currentTeam = new Team();
			int teamid = resultSet.getInt(7);
			currentTeam.setId(teamid);
			teamById.put(teamid, currentTeam);

			Player player1 = new Player(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));

			Player player2 = null;
			String name2 = resultSet.getString(4);
			if (name2 != null) {
				player2 = new Player(name2, resultSet.getString(5), resultSet.getString(6));
			}

			currentTeam.setPlayer1(player1);
			currentTeam.setPlayer2(player2);

			group.addTeam(currentTeam);
		}
	}
}
