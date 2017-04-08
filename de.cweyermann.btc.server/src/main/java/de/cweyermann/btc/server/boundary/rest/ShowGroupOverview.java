package de.cweyermann.btc.server.boundary.rest;

import java.util.ArrayList;
import java.util.List;

import de.cweyermann.btc.server.boundary.tpfile.GetGroup;
import de.cweyermann.btc.server.boundary.tpfile.GetGroups;
import de.cweyermann.btc.server.control.CalculateGroupStandings;
import de.cweyermann.btc.server.control.CalculateKoRounds;
import de.cweyermann.btc.server.entity.Group;
import de.cweyermann.btc.server.entity.MoreGroups;
import io.vertx.core.MultiMap;

/**
 * Shows the Standings of all groups
 * 
 * @author chris
 *
 */
public class ShowGroupOverview extends AbstractRestControl<MoreGroups> {

	private CalculateGroupStandings calculate;
	private GetGroup getGroup;
	private GetGroups getGroups;
	private CalculateKoRounds calcKo;

	public ShowGroupOverview(GetGroups getGroups, CalculateGroupStandings calculate, CalculateKoRounds calcKo, GetGroup getGroup) {
		this.calculate = calculate;
		this.calcKo = calcKo;
		this.getGroup = getGroup;
		this.getGroups = getGroups;
	}

	@Override
	public MoreGroups route(MultiMap params) {
		List<Group> groups = new ArrayList<>();
		int discId = Integer.parseInt(params.get("disciplines"));

		for (Integer key : getGroups.withParent(discId).getChilds().keySet()) {
			Group group = getGroup.get(key);
			calculate.addCalculations(group);
			calcKo.addKoRounds(group);
			groups.add(group);
		}

		return new MoreGroups(groups);
	}
}