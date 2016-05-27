package de.cweyermann.btc.server.boundary.rest;

import de.cweyermann.btc.server.entity.Groups;
import io.vertx.core.MultiMap;

/**
 * Shows the Standings of all groups
 * 
 * @author chris
 *
 */
public class ShowGroupOverview extends AbstractRestControl<Groups> {

	@Override
	public Groups route(MultiMap params) {
		return new Groups();
	}


}