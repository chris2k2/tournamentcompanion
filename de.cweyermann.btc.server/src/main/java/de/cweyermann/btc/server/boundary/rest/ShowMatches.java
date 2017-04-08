package de.cweyermann.btc.server.boundary.rest;

import de.cweyermann.btc.server.boundary.tpfile.GetMatches;
import de.cweyermann.btc.server.entity.Matches;
import io.vertx.core.MultiMap;

public class ShowMatches extends AbstractRestControl<Matches> {

    private GetMatches getMatches;

    public ShowMatches(GetMatches get) {
        this.getMatches = get;
    }

    @Override
    public Matches route(MultiMap params) {
        return getMatches.all();
    }

}
