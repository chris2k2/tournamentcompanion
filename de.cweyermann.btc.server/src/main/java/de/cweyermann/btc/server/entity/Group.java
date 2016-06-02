package de.cweyermann.btc.server.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group extends AbstractEntryWithId {

	private List<Standing> standings = new ArrayList<>();

	private List<Match> matches = new ArrayList<>();

	private List<Team> teams = new ArrayList<>();

	public List<Standing> getStandings() {
		Collections.sort(standings);
		return standings;
	}

	public void addStanding(Standing standing) {
		standings.add(standing);
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void addMatch(Match match) {
		matches.add(match);
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void addTeam(Team team) {
		teams.add(team);
	}
}
