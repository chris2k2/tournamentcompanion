package de.cweyermann.btc.server.boundary.tpfile;

import java.sql.Connection;
import java.sql.ResultSet;

import de.cweyermann.btc.server.entity.Groups;

public class GetGroups extends AbstractGetChilds<Groups> {

	public GetGroups(Connection connection) {
		super(connection);
	}

	public Groups withParent(int id) {
		ResultSet result = executeSql("select id, name from Draw where event=" + id + ";");

		return convert(result, new Groups());
	}

	public Groups all() {
		ResultSet result = executeSql("select id, name from Draw;");

		return convert(result, new Groups());
	}
}
