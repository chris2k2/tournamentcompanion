package de.cweyermann.btc.server.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClubStandings extends AbstractEntity {

	private List<ClubStanding> standings = new ArrayList<>();

	public List<ClubStanding> getStandings() {
		return standings;
	}

	public void addStandings(ClubStanding standing) {
		standings.add(standing);
	}
}
