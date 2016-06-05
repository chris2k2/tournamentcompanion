package de.cweyermann.btc.server.boundary.tpfile;

import org.junit.Test;

public class AbstractTpFileControlTest {

	private class SpecificMsAccessControl extends AbstractTpFileControl {

		public SpecificMsAccessControl(String filePath) {
			super(TestUtils.getConnection(filePath));
		}

		public void fails() {
			executeSql("this is definetly not valid sql...");
		}
	}

	@Test(expected = TpFileConnectionInvalid.class)
	public void notExistingFileGiven_InvalidArgumentException() {
		new SpecificMsAccessControl("doesnotexist.TP");
	}

	@Test(expected = TpFileConnectionInvalid.class)
	public void incorrectFileGiven_InvalidArgumentException() {
		new SpecificMsAccessControl("test.txt");
	}

	@Test
	public void correctFileGiven_opened() {
		new SpecificMsAccessControl("minimum.TP");
	}
	

	@Test(expected=TpFileConnectionInvalid.class)
	public void correctFileGiven_sqlFailsd() {
		new SpecificMsAccessControl("src/test/resources/msaccess/examples/minimum.TP").fails();
	}
}
