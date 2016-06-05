package de.cweyermann.btc.server.boundary.tpfile;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class TestUtils {

	public static Connection getConnection(String name) {
		String filePath = getPath(name);
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			File accessFile = new File(filePath);
			if (!accessFile.exists()) {
				throw new TpFileConnectionInvalid("File " + filePath + " is invalid!");
			}

			return DriverManager.getConnection("jdbc:ucanaccess://" + filePath, "", "d4R2GY76w2qzZ");
		} catch (ClassNotFoundException | SQLException e) {
			throw new TpFileConnectionInvalid(e);
		}
	}

	public static String getPath(String name) {
		return "src/test/resources/msaccess/examples/" + name;
	}
}
