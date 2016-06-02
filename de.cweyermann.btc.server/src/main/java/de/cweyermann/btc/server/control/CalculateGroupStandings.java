package de.cweyermann.btc.server.control;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.cweyermann.btc.server.entity.Group;
import de.cweyermann.btc.server.entity.Match;
import de.cweyermann.btc.server.entity.Standing;
import de.cweyermann.btc.server.entity.Team;

public class CalculateGroupStandings {

	public void add(Group group) {
		Map<Team, Standing> standings = createEmptyStandings(group);

		applyMatches2Standings(group.getMatches(), standings);
		
		setRankings(group.getStandings());
	}

	private void applyMatches2Standings(List<Match> matches, Map<Team, Standing> standings) {
		for (Match match : matches) {
			applyMatch2Standing(match, standings.get(match.getTeam1()));
			applyMatch2Standing(match, standings.get(match.getTeam2()));
		}
	}

	private void applyMatch2Standing(Match match, Standing standing) {
		Team you = standing.getTeam();
		Team oponent = getOponent(match, you);

		standing.addSetsFor(match.getSets(you));
		standing.addSetsAgainst(match.getSets(oponent));
		standing.addPointsFor(match.getPoints(you));
		standing.addPointsAgainst(match.getPoints(oponent));

		if (you.equals(match.getWinner())) {
			standing.addMatchesFor(2);
		} else if (oponent.equals(match.getWinner())) {
			standing.addMatchesAgainst(2);
		}
	}

	private Team getOponent(Match match, Team you) {
		Team oponent = match.getTeam1();

		if (match.getTeam1().equals(you)) {
			oponent = match.getTeam2();
		}
		return oponent;
	}

	private Map<Team, Standing> createEmptyStandings(Group group) {
		Map<Team, Standing> standings = new HashMap<>();

		for (Team team : group.getTeams()) {
			Standing standing = new Standing(team, 0, 0, 0, 0, 0, 0, 0);

			standings.put(team, standing);
			group.addStanding(standing);
		}

		return standings;
	}

	private void setRankings(List<Standing> standings) {
		
	}

}
