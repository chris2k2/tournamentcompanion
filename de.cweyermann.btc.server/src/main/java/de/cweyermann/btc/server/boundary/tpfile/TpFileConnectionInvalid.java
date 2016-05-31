package de.cweyermann.btc.server.boundary.tpfile;

public class TpFileConnectionInvalid extends RuntimeException {
	private static final long serialVersionUID = 5080757441888686502L;

	public TpFileConnectionInvalid(Throwable cause) {
		super(cause);
	}

	public TpFileConnectionInvalid(String string) {
		super(string);
	}

}
