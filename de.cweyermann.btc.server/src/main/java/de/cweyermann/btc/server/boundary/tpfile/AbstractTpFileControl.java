package de.cweyermann.btc.server.boundary.tpfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class parses the MS Access File. It provides all the entities. It does
 * not calculate standings or do anything else. This is done by the controls
 * 
 * @author chris
 *
 */
public abstract class AbstractTpFileControl {

	private Connection connection;

	public AbstractTpFileControl(Connection connection) {
		this.connection = connection;
	}

	protected ResultSet executeSql(String sql) {
		try {
			Statement statement = connection.createStatement();
			return statement.executeQuery(sql);
		} catch (SQLException e) {
			throw new TpFileConnectionInvalid(e);
		}
	}


}
