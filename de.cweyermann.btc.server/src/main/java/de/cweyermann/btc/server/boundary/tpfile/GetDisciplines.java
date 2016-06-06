package de.cweyermann.btc.server.boundary.tpfile;

import java.sql.Connection;
import java.sql.ResultSet;

import de.cweyermann.btc.server.entity.Disciplines;

public class GetDisciplines extends AbstractGetChilds<Disciplines> {

	public GetDisciplines(Connection filePath) {
		super(filePath);
	}

	public Disciplines all()
	{
		ResultSet result = executeSql("select id, name from Event;");
		
		return convert(result, new Disciplines());
	}

}
