package de.cweyermann.btc.server.boundary.rest;

import de.cweyermann.btc.server.entity.Group;
import io.vertx.core.MultiMap;

public class ShowGroup extends AbstractRestControl<Group> {

	@Override
	public Group route(MultiMap params) {
		return new Group();
	}

}
