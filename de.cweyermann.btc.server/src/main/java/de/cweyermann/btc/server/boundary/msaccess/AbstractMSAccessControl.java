package de.cweyermann.btc.server.boundary.msaccess;

import java.io.File;

/**
 * This class parses the MS Access File. It provides all the entities. It does
 * not calculate standings or do anything else. This is done by the controls
 * 
 * @author chris
 *
 */
public class AbstractMSAccessControl {

	public AbstractMSAccessControl(String filePath) {
		File accessFile = new File(filePath);
		if (!accessFile.exists()) {
			throw new IllegalArgumentException("File " + filePath + " is invalid!");
		}
	}

}
