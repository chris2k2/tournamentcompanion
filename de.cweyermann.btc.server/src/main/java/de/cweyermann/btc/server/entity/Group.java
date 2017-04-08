package de.cweyermann.btc.server.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group extends AbstractEntryWithId {

	public enum GroupType {
		QUALIFICATION, FINALGROUP, KO, LOOSERSKO;
	}

	private String name;

	private boolean ko;

	private List<Standing> standings = new ArrayList<>();

	private List<Match> matches = new ArrayList<>();

	private List<Team> teams = new ArrayList<>();

	private List<KoRound> rounds = new ArrayList<>();
	
	private int position;

	private boolean qualification;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Standing> getStandings() {
		ArrayList<Standing> list = new ArrayList<>(standings);
		Collections.sort(list);
		return list;
	}

	public void addStanding(Standing standing) {
		standings.add(standing);
	}

	public List<Match> getMatches() {
		ArrayList<Match> list = new ArrayList<>(matches);
		Collections.sort(list, (o1, o2) -> o1.getMatchnr() - o2.getMatchnr());
		return list;
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

	public boolean isKo() {
		return ko;
	}

	public void setKo(boolean ko) {
		this.ko = ko;
	}

	public GroupType getType() {
		GroupType type = GroupType.QUALIFICATION;
		if (ko && position == 1) {
			type = GroupType.KO;
		} else if (ko) {
			type = GroupType.LOOSERSKO;
		} else if (!qualification) {
			type = GroupType.FINALGROUP;
		}

		return type;
	}

	public void setPosition(int pos) {
		this.position = pos;
	}

	public void setQualification(boolean isQuali) {
		this.qualification = isQuali;
	}

	public List<KoRound> getKoRounds() {
		return rounds;
	}

	public void addRound(KoRound round) {
		this.rounds.add(round);
	}
}
