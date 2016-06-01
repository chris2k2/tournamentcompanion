package de.cweyermann.btc.server.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Group extends AbstractEntryWithId {

	private SortedSet<Standing> standings = new TreeSet<>();

	private List<Match> matches = new ArrayList<>();

	private List<Team> teams = new ArrayList<>();

	public SortedSet<Standing> getStandings() {
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
