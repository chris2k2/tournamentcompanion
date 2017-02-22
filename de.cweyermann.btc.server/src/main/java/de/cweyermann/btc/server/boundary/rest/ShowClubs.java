package de.cweyermann.btc.server.boundary.rest;

import java.util.List;
import java.util.stream.Collectors;

import de.cweyermann.btc.server.boundary.tpfile.GetGroup;
import de.cweyermann.btc.server.boundary.tpfile.GetGroups;
import de.cweyermann.btc.server.control.CalculateClubStandings;
import de.cweyermann.btc.server.entity.ClubStandings;
import de.cweyermann.btc.server.entity.Group;
import io.vertx.core.MultiMap;

public class ShowClubs extends AbstractRestControl<ClubStandings> {

    private GetGroup getGroup;
    private GetGroups getGroups;
    private CalculateClubStandings calculateClub;

    public ShowClubs(CalculateClubStandings calculateClub, GetGroups getGroups, GetGroup getGroup) {
        this.calculateClub = calculateClub;
        this.getGroups = getGroups;
        this.getGroup = getGroup;
    }

    @Override
    public ClubStandings route(MultiMap params) {
        List<Group> groups = getGroups.all()
                .getChilds()
                .keySet()
                .stream()
                .map(key -> getGroup.get(key))
                .collect(Collectors.toList());

        return calculateClub.execute(groups);
    }
}
