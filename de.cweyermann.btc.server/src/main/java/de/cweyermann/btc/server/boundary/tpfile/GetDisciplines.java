package de.cweyermann.btc.server.boundary.tpfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.cweyermann.btc.server.entity.Disciplines;

public class GetDisciplines extends AbstractTpFileControl {

	public GetDisciplines(Connection filePath) {
		super(filePath);
	}

	public Disciplines getAll() {
		ResultSet resultSet = executeSql("select name from Event");

		return convert(resultSet);
	}

	private Disciplines convert(ResultSet resultSet) {
		Disciplines discs = new Disciplines();
		try {
			while (resultSet.next()) {
				discs.addDiscipline(resultSet.getString(1));
			}
		} catch (SQLException e) {
			throw new TpFileConnectionInvalid(e);
		}

		return discs;
	}

}
