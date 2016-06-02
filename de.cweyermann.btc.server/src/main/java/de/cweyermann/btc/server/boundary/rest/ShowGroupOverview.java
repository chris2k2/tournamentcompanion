package de.cweyermann.btc.server.boundary.rest;

import de.cweyermann.btc.server.boundary.tpfile.GetGroup;
import de.cweyermann.btc.server.control.CalculateGroupStandings;
import de.cweyermann.btc.server.entity.Groups;
import io.vertx.core.MultiMap;

/**
 * Shows the Standings of all groups
 * 
 * @author chris
 *
 */
public class ShowGroupOverview extends AbstractRestControl<Groups> {

	private CalculateGroupStandings calculate;
	private GetGroup getGroup;

	public ShowGroupOverview(CalculateGroupStandings calculate, GetGroup getGroup) {
		this.calculate = calculate;
		this.getGroup = getGroup;
	}

	@Override
	public Groups route(MultiMap params) {
		return new Groups();
	}
}