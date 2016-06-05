package de.cweyermann.btc.server.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import de.cweyermann.btc.server.entity.Group;
import de.cweyermann.btc.server.entity.Match;
import de.cweyermann.btc.server.entity.Standing;
import de.cweyermann.btc.server.entity.Team;

public class CalculateGroupStandings {

	private static final Logger LOG = LogManager.getLogger(CalculateGroupStandings.class.getName());

	public void add(Group group) {
		calculateStandings(group);

		setRankings(group.getStandings(), group.getMatches());
	}

	private void calculateStandings(Group group) {
		Map<Team, Standing> standings = createEmptyStandings(group);

		for (Match match : group.getMatches()) {
			if (match.isDone()) {
				addMatch2Standing(match, standings.get(match.getTeam1()));
				addMatch2Standing(match, standings.get(match.getTeam2()));
			}
		}

		addStandings(group, standings);
	}

	private void addStandings(Group group, Map<Team, Standing> standings) {
		standings.values().forEach(s -> group.addStanding(s));
	}

	private void addMatch2Standing(Match match, Standing standing) {
		Team you = standing.getTeam();
		Team oponent = getOponent(match, you);

		standing.addSetsFor(match.getSets(you));
		standing.addSetsAgainst(match.getSets(oponent));
		standing.addPointsFor(match.getPoints(you));
		standing.addPointsAgainst(match.getPoints(oponent));

		if (you.equals(match.getWinner())) {
			standing.addMatchesFor(2);
		} else {
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
			standings.put(team, new Standing(team, 0, 0, 0, 0, 0, 0, 1));
		}

		return standings;
	}

	private void setRankings(List<Standing> standings, List<Match> matches) {
		standings.sort((o1, o2) -> o2.getMatchesFor() - o1.getMatchesFor());
		apply(standings);

		solveTieBreaks(standings, matches);
	}

	private void solveTieBreaks(List<Standing> standings, List<Match> matches) {
		Multimap<Integer, Standing> matches2Standings = ArrayListMultimap.create();

		standings.forEach(s -> matches2Standings.put(s.getMatchesFor(), s));

		for (Integer key : matches2Standings.keySet()) {
			Collection<Standing> standings4Key = matches2Standings.get(key);
			if (standings4Key.size() == 2) {
				solve2WayTie(standings4Key, matches);
			}

			if (standings4Key.size() > 2) {
				solveMultiwayTie(standings4Key, matches);
			}
		}
	}

	private void solve2WayTie(Collection<Standing> standings4Key, List<Match> matches) {

		List<Standing> standings = new ArrayList<>(standings4Key);
		Standing s1 = standings.get(0);
		Standing s2 = standings.get(1);
		LOG.info("2-wayTie detected: " + s1 + "///" + s2);

		if (compare(s1, s2, matches) > 0) {
			apply(Arrays.asList(s2, s1));
		} else {
			apply(Arrays.asList(s1, s2));
		}

	}

	private void solveMultiwayTie(Collection<Standing> standings4Key, List<Match> matches) {
		LOG.info(standings4Key.size() + "-way Tie encountered");
		
		List<Standing> standings = standings4Key.stream().sorted((s1, s2) -> compareBySets(s1, s2))
				.collect(Collectors.toList());
		apply(standings);
		LOG.info("... solved by sets");

		if (isStillTied(standings)) {
			Multimap<Integer, Standing> setDifferences = getSetDifference2Standings(standings);

			for (Integer key : setDifferences.keySet()) {
				Collection<Standing> subStandings4Key = setDifferences.get(key);
				if (subStandings4Key.size() == 2) {
					solve2WayTie(subStandings4Key, matches);
					LOG.info("... solved by 2 way tie after sets");
				}

				if (subStandings4Key.size() > 2) {
					solveMultiwayByPoints(subStandings4Key);
					LOG.info("... solved by points");
				}
			}
		}

	}

	private Multimap<Integer, Standing> getSetDifference2Standings(List<Standing> standings) {
		Multimap<Integer, Standing> setDifferences = ArrayListMultimap.create();
		standings.forEach(s -> setDifferences.put(s.getSetsFor() - s.getSetsAgainst(), s));
		return setDifferences;
	}

	private void solveMultiwayByPoints(Collection<Standing> subStandings4Key) {
		List<Standing> list = new ArrayList<>(subStandings4Key).stream().sorted((s1, s2) -> compareByPoints(s1, s2))
				.collect(Collectors.toList());

		apply(list);
	}

	private int compareByPoints(Standing s1, Standing s2) {
		int pointsDiff1 = s1.getPointsFor() - s1.getPointsAgainst();
		int pointsDiff2 = s2.getPointsFor() - s2.getPointsAgainst();

		return pointsDiff2 - pointsDiff1;
	}

	private boolean isStillTied(List<Standing> standings) {
		boolean hasSameSetDifference = false;
		Set<Integer> setDiffs = new HashSet<>();
		for (Standing s : standings) {
			if (setDiffs.contains(s.getSetsFor() - s.getSetsAgainst())) {
				hasSameSetDifference = true;
				break;
			}

			setDiffs.add(s.getSetsFor() - s.getSetsAgainst());
		}

		return hasSameSetDifference;
	}

	private void apply(Collection<Standing> standings4Key) {
		Optional<Integer> min = standings4Key.stream().map(s -> s.getRanking())
				.min(Comparator.comparing(i -> i));

		if (min.isPresent()) {
			int lowestRanking = min.get();
			List<Standing> standings = new ArrayList<>(standings4Key);
			for (int i = 0; i < standings.size(); i++) {
				standings.get(i).setRanking(lowestRanking + i);
			}
		}
	}

	private int compareBySets(Standing s1, Standing s2) {
		int setDifference1 = s1.getSetsFor() - s1.getSetsAgainst();
		int setDifference2 = s2.getSetsFor() - s2.getSetsAgainst();

		return setDifference2 - setDifference1;
	}

	private int compare(Standing first, Standing second, List<Match> matches) {
		int result = compareByDirectDuel(first, second, matches);

		if (result == 0) {
			result = compareBySets(first, second);
			LOG.info("... solved by sets:" + result);
		}

		if (result == 0) {
			result = compareByPoints(first, second);
			LOG.info("... solved by points:" + result);
		}

		return result;
	}

	private int compareByDirectDuel(Standing first, Standing second, List<Match> matches) {
		int result = 0;

		Team team1 = first.getTeam();
		Team team2 = second.getTeam();
		for (Match m : matches) {
			if ((team1.equals(m.getTeam1()) && team2.equals(m.getTeam2()))
					|| (team2.equals(m.getTeam1()) && team1.equals(m.getTeam2()))) {
				if (team1.equals(m.getWinner())) {
					result = -1;
				} else {
					result = 1;
				}
				LOG.info(".. solved by direct duel: " + m);
			}
		}

		return result;
	}

}
