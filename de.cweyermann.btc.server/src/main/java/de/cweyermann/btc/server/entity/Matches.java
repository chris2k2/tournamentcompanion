package de.cweyermann.btc.server.entity;

import java.util.ArrayList;
import java.util.List;

public class Matches extends AbstractEntity {

    private List<Match> matches = new ArrayList<>();

    public Matches(List<Match> matches) {
        super();
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;
    }
}
