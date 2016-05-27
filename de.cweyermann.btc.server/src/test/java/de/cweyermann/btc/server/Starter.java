package de.cweyermann.btc.server;

import de.cweyermann.btc.server.boundary.rest.StartupRESTServer;
import io.vertx.core.Vertx;

public class Starter {
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new StartupRESTServer());
	}
}
