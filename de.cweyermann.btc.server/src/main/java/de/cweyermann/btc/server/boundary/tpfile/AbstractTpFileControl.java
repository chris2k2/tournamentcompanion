package de.cweyermann.btc.server.boundary.tpfile;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
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

	protected String filePath;
	private Connection connection;

	public AbstractTpFileControl(String filePath) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			File accessFile = new File(filePath);
			if (!accessFile.exists()) {
				throw new TpFileConnectionInvalid("File " + filePath + " is invalid!");
			}

			this.filePath = filePath;
			this.connection = DriverManager.getConnection("jdbc:ucanaccess://" + filePath, "", "d4R2GY76w2qzZ");
		} catch (ClassNotFoundException | SQLException e) {
			throw new TpFileConnectionInvalid(e);
		}
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
