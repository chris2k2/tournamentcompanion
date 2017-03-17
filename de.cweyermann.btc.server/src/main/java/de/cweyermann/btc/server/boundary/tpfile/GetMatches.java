package de.cweyermann.btc.server.boundary.tpfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.cweyermann.btc.server.entity.Match;
import de.cweyermann.btc.server.entity.Matches;
import de.cweyermann.btc.server.entity.Player;
import de.cweyermann.btc.server.entity.Team;

public class GetMatches extends AbstractTpFileControl {

    private Map<Integer, Team> teamById = new HashMap<>();

    public GetMatches(Connection filePath) {
        super(filePath);
    }

    public Matches all() {
        fillTeams();
        List<Match> all = getMatches();

        return new Matches(all);
    }

    private List<Match> getMatches() {
        ResultSet resultSet = executeSql("SELECT hometeam.entry, awayteam.entry, "
                + "thematch.team1set1, thematch.team2set1, thematch.team1set2, "
                + "thematch.team2set2, thematch.team1set3, thematch.team2set3, "
                + "hometeam.walkover, awayteam.walkover, thematch.matchnr, thematch.roundnr "
                + "FROM PlayerMatch thematch INNER JOIN PlayerMatch AS hometeam ON thematch.van1 = hometeam.planning "
                + "INNER JOIN PlayerMatch AS awayteam ON thematch.van2 = awayteam.planning "
                + "AND thematch.draw = hometeam.draw AND thematch.draw = awayteam.draw "
                + "AND reversehomeaway=FALSE;");

        try {
            return convertMatches(resultSet);
        } catch (SQLException e) {
            throw new TpFileConnectionInvalid(e);
        }
    }

    private List<Match> convertMatches(ResultSet rs) throws SQLException {
        List<Match> matches = new ArrayList<>();

        while (rs.next()) {
            Match match = new Match();
            match.setTeam1(teamById.get(rs.getInt(1)));
            match.setTeam2(teamById.get(rs.getInt(2)));
            match.setSet1(buildSet(rs, 3, 4));
            match.setSet2(buildSet(rs, 5, 6));
            match.setSet3(buildSet(rs, 7, 8));
            match.setWalkoverTeam1(rs.getBoolean(9));
            match.setWalkoverTeam2(rs.getBoolean(10));
            match.setMatchnr(rs.getInt(11));
            match.setRoundnr(rs.getInt(12));

            matches.add(match);
        }

        return matches;
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

    private void fillTeams() {
        ResultSet resultSet = executeSql(
                "SELECT DISTINCT player1.name, player1.firstname, player1.memberid, "
                        + "player2.name, player2.firstname, player2.memberid, entry.id, club1.name, club2.name "
                        + "FROM Draw INNER JOIN PlayerMatch ON Draw.id = PlayerMatch.draw "
                        + "INNER JOIN Entry ON PlayerMatch.entry = Entry.id "
                        + "INNER JOIN Player AS player1 ON Entry.player1 = player1.id "
                        + "LEFT JOIN Player AS player2 ON Entry.player2 = player2.id "
                        + "LEFT JOIN Club AS club1 ON club1.id = player1.club "
                        + "LEFT JOIN Club AS club2 ON club2.id = player2.club ;");
        try {
            convertTeams(resultSet);
        } catch (SQLException e) {
            throw new TpFileConnectionInvalid(e);
        }
    }

    private void convertTeams(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Team currentTeam = new Team();
            int teamid = resultSet.getInt(7);
            currentTeam.setId(teamid);
            teamById.put(teamid, currentTeam);

            Player player1 = new Player(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3));
            player1.setClub(resultSet.getString(8));

            Player player2 = null;
            String name2 = resultSet.getString(4);
            if (name2 != null) {
                player2 = new Player(name2, resultSet.getString(5), resultSet.getString(6));
                player2.setClub(resultSet.getString(9));
            }

            currentTeam.setPlayer1(player1);
            currentTeam.setPlayer2(player2);
        }
    }

}
