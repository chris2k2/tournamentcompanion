package de.cweyermann.btc.server.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.cweyermann.btc.server.entity.ClubStanding;
import de.cweyermann.btc.server.entity.ClubStandings;
import de.cweyermann.btc.server.entity.Group;
import de.cweyermann.btc.server.entity.Standing;

public class CalculateClubStandings {

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
		Map<String, ClubStanding> club2Standing = new HashMap<>();

		for (Group group : groups) {
			List<Standing> groupStandings = group.getStandings();
			for (Standing groupStanding : groupStandings) {
				ClubStanding clubStanding = getClubStanding(standings, club2Standing, groupStanding);

				if (group.getBestPossiblePosition() == 1) {
					switch (groupStanding.getRanking()) {
					case 1:
						clubStanding.addPoints(first);
						break;
					case 2:
						clubStanding.addPoints(second);
						break;
					case 3:
						clubStanding.addPoints(third);
						break;
					case 4:
						clubStanding.addPoints(fourth);
						break;
					default:
					}
				}
			}
		}

		return standings;
	}

	private ClubStanding getClubStanding(ClubStandings standings, Map<String, ClubStanding> club2Standing,
			Standing groupStanding) {
		String clubName = groupStanding.getTeam().getPlayer1().getClub();
		ClubStanding clubStanding = club2Standing.get(clubName);
		if (clubStanding == null) {
			clubStanding = new ClubStanding();
			clubStanding.setClubName(clubName);
			club2Standing.put(clubName, clubStanding);
			standings.addStandings(clubStanding);
		}
		return clubStanding;
	}
}
