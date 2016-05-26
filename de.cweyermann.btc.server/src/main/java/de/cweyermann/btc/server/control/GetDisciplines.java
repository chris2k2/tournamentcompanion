package de.cweyermann.btc.server.control;

import de.cweyermann.btc.server.entity.Group;
import io.vertx.core.MultiMap;

public class GetDisciplines extends AbstractRestControl<Group>
{

	@Override
	public Group route(MultiMap params) {
		return new Group();
	}
	
}

