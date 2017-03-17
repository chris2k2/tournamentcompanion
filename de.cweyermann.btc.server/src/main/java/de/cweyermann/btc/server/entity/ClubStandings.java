package de.cweyermann.btc.server.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClubStandings extends AbstractEntity {

    private List<ClubStanding> standings = new ArrayList<>();

    public List<ClubStanding> getStandings() {
        sort();

        setPositions();

        return standings;
    }

    private void sort() {
        Collections.sort(standings, (a, b) -> (int) Math.signum(b.getPoints() - a.getPoints()));
    }

    private void setPositions() {
        ClubStanding priorS = null;

        for (int i = 0; i < standings.size(); i++) {
            ClubStanding thisS = standings.get(i);

            double pointDiff = Double.MAX_VALUE;
            if (priorS != null) {
                pointDiff = Math.abs(priorS.getPoints() - thisS.getPoints());
            }
            
            if (pointDiff < 0.001) {
                thisS.setPosition(priorS.getPosition());
            } else {
                standings.get(i).setPosition(i + 1); // first element => 1. not
            }

            priorS = thisS;
        }
    }

    public void addStandings(ClubStanding standing) {
        standings.add(standing);
    }
}
