package de.cweyermann.btc.server.control;

import java.util.ArrayList;
import java.util.List;

import de.cweyermann.btc.server.entity.Group;
import de.cweyermann.btc.server.entity.Match;
import de.cweyermann.btc.server.entity.Team;

public final class BuilderUtils {

	public static Group buildGroup(List<Match> matches, Team... teams) {
		Group group = new Group();
		group.setQualification(false);
		group.setPosition(1);

		for (Team t : teams) {
			group.addTeam(t);
			t.setId(group.getTeams().indexOf(t) + 1);
		}

		for (Match m : matches) {
			group.addMatch(m);
		}

		new CalculateGroupStandings().addCalculations(group);

		return group;
	}

	public static Group getGroupInRanking(Team... teams) {
		List<Match> matches = new ArrayList<>();

		for (int i = 0; i < teams.length; i++) {
			for (int j = i + 1; j < teams.length; j++) {
				matches.add(new Match(teams[i], teams[j], "21-0", "21-0", null, false, false, 0, 0));
			}
		}
		
		return buildGroup(matches, teams);
	}
}
