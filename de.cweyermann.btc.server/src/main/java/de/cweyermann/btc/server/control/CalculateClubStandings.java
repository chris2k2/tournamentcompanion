package de.cweyermann.btc.server.control;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.cweyermann.btc.server.entity.ClubStanding;
import de.cweyermann.btc.server.entity.ClubStandings;
import de.cweyermann.btc.server.entity.Group;
import de.cweyermann.btc.server.entity.Match;
import de.cweyermann.btc.server.entity.Player;
import de.cweyermann.btc.server.entity.Standing;
import de.cweyermann.btc.server.entity.Team;

public class CalculateClubStandings {

	private static final Logger LOG = LogManager.getLogger(CalculateClubStandings.class.getName());

	private final int loosersWinner;
	private final int fourth;
	private final int third;
	private final int second;
	private final int first;

	public CalculateClubStandings(int first, int second, int third, int fourth, int loosersWinner) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.loosersWinner = loosersWinner;
	}

	public ClubStandings execute(List<Group> groups) {
		ClubStandings standings = new ClubStandings();
		Map<String, ClubStanding> club2Standing = buildMap(groups);

		for (Group group : groups) {
			switch (group.getType()) {
			case FINALGROUP:
				calculateSingleGroup(club2Standing, group);
				break;
			case KO:
				calculateKo(club2Standing, group);
				break;
			case LOOSERSKO:
				calculateLoosersKo(club2Standing, group);
				break;
			default:
			}
		}

		add(standings, club2Standing);
		return standings;
	}

	private void calculateLoosersKo(Map<String, ClubStanding> club2Standing, Group group) {
		List<Match> finals = getFinals(group);

		Match finalMatch = finals.get(0);
		if (finalMatch.isDone()) {
			awardPoints(club2Standing, finalMatch.getWinner(), loosersWinner);
		}
	}

	private void add(ClubStandings standings, Map<String, ClubStanding> club2Standing) {
		for (ClubStanding standing : club2Standing.values()) {
			standings.addStandings(standing);
		}
	}

	private Map<String, ClubStanding> buildMap(List<Group> groups) {
		Map<String, ClubStanding> clubStanding = new HashMap<>();

		for (Group group : groups) {
			for (Team team : group.getTeams()) {
				addClubFrom(clubStanding, team.getPlayer1());

				if (team.getPlayer2() != null) {
					addClubFrom(clubStanding, team.getPlayer2());
				}
			}
		}

		return clubStanding;
	}

	private void addClubFrom(Map<String, ClubStanding> clubStanding, Player player) {
		ClubStanding standing2 = new ClubStanding();
		String clubName2 = player.getClub();
		standing2.setClubName(clubName2);
		clubStanding.put(clubName2, standing2);
	}

	private void calculateKo(Map<String, ClubStanding> club2Standing, Group group) {
		List<Match> finals = getFinals(group);

		Match finalMatch = finals.get(0);
		if (finalMatch.isDone()) {
			awardPoints(club2Standing, finalMatch.getWinner(), first);
			awardPoints(club2Standing, finalMatch.getLooser(), second);
		}

		if (finals.size() > 1) {
			Match thirdMatch = finals.get(1);
			if (thirdMatch.isDone()) {
				awardPoints(club2Standing, thirdMatch.getWinner(), third);
				awardPoints(club2Standing, thirdMatch.getLooser(), fourth);
			}
		} else {
			List<Match> halffinals = getHalfFinals(group);
			for (Match hf : halffinals) {
				if (hf.isDone()) {
					awardPoints(club2Standing, hf.getLooser(), third);
				}
			}
		}
	}

	private List<Match> getFinals(Group group) {
		int max = getLastRound(group);
		LOG.info("Roundnr in KO round for finals: " + max);
		List<Match> finals = getRound(group, max);
		LOG.info("Final games: " + finals);
		return finals;
	}

	private List<Match> getHalfFinals(Group group) {
		int max = getLastRound(group);

		List<Match> finals = getRound(group, max - 1);

		LOG.info("Halffinal games: " + finals);
		return finals;
	}

	private int getLastRound(Group group) {
		int max = group.getMatches().stream().max((a, b) -> a.getRoundnr() - b.getRoundnr()).get().getRoundnr();
		return max;
	}

	private List<Match> getRound(Group group, int max) {
		List<Match> finals = group.getMatches().stream().filter(m -> m.getRoundnr() == max)
				.collect(Collectors.toList());
		Collections.sort(finals, (a, b) -> a.getMatchnr() - b.getMatchnr());
		return finals;
	}

	private void calculateSingleGroup(Map<String, ClubStanding> club2Standing, Group group) {
		List<Standing> groupStandings = group.getStandings();
		for (Standing groupStanding : groupStandings) {
			Team team = groupStanding.getTeam();

			switch (groupStanding.getRanking()) {
			case 1:
				awardPoints(club2Standing, team, first);
				break;
			case 2:
				awardPoints(club2Standing, team, second);
				break;
			case 3:
				awardPoints(club2Standing, team, third);
				break;
			case 4:
				awardPoints(club2Standing, team, fourth);
				break;
			default:
			}
		}
	}

	private void awardPoints(Map<String, ClubStanding> club2Standing, Team team, int points) {
		String clubName = team.getPlayer1().getClub();

		if (team.getPlayer2() != null) {
			double halfPoints = points / 2.0;
			String clubName2 = team.getPlayer2().getClub();
			club2Standing.get(clubName).addPoints(halfPoints);
			club2Standing.get(clubName2).addPoints(halfPoints);
		} else {
			club2Standing.get(clubName).addPoints(points);
		}
	}
}
