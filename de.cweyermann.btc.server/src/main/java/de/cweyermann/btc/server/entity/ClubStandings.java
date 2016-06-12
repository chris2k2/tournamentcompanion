package de.cweyermann.btc.server.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ClubStandings extends AbstractEntity {

	private List<ClubStanding> standings = new ArrayList<>();

	public List<ClubStanding> getStandings() {
		Collections.sort(standings, (a,b) -> (int) Math.signum(b.getPoints() - a.getPoints()));
		
		return standings;
	}

	public void addStandings(ClubStanding standing) {
		standings.add(standing);
	}
}
