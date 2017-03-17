package de.cweyermann.btc.server.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClubStandings extends AbstractEntity {

    private List<ClubStanding> standings = new ArrayList<>();

    public List<ClubStanding> getStandings() {
        Collections.sort(standings, (a, b) -> (int) Math.signum(b.getPoints() - a.getPoints()));
        for (int i = 0; i < standings.size(); i++) {
            standings.get(i).setPosition(i + 1); //first element => 1. not 0.
        }
        return standings;
    }

    public void addStandings(ClubStanding standing) {
        standings.add(standing);
    }
}
