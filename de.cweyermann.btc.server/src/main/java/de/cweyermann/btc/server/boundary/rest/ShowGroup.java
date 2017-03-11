package de.cweyermann.btc.server.boundary.rest;

import de.cweyermann.btc.server.boundary.tpfile.GetGroup;
import de.cweyermann.btc.server.control.CalculateGroupStandings;
import de.cweyermann.btc.server.entity.Group;
import io.vertx.core.MultiMap;

public class ShowGroup extends AbstractRestControl<Group> {

	private CalculateGroupStandings calc;
	private GetGroup group;

	public ShowGroup(GetGroup group, CalculateGroupStandings calc) {
		this.group = group;
		this.calc = calc;
	}

	@Override
	public Group route(MultiMap params) {
		int groupId = Integer.parseInt(params.get("group"));
		
		Group group2 = group.get(groupId);
		calc.addCalculations(group2);
		
		return group2;
	}

}
