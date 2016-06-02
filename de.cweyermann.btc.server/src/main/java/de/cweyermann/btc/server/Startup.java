package de.cweyermann.btc.server;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import de.cweyermann.btc.server.boundary.rest.ShowClubs;
import de.cweyermann.btc.server.boundary.rest.ShowDisciplines;
import de.cweyermann.btc.server.boundary.rest.ShowGroup;
import de.cweyermann.btc.server.boundary.rest.ShowGroupOverview;
import de.cweyermann.btc.server.boundary.tpfile.GetDisciplines;
import de.cweyermann.btc.server.boundary.tpfile.GetGroup;
import de.cweyermann.btc.server.boundary.tpfile.TpFileConnectionInvalid;
import de.cweyermann.btc.server.control.CalculateGroupStandings;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

// TODO should be refactored to Microservices
public class Startup extends AbstractVerticle {

	private ShowDisciplines showDisciplines;
	private ShowGroupOverview showgroupOverview;
	private ShowClubs showClubs;
	private ShowGroup showGroup;

	public void start() {
		String filePath = config().getString("tpfile.path");
		Integer port = config().getInteger("httpserver.port");

		Connection dbConnection = makeConnection(filePath);
		init(dbConnection);

		startHttpServer(port);
	}

	private Connection makeConnection(String filePath) {
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

	private void startHttpServer(Integer port) {
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router(vertx);

		router.route("/btc/clubs/").handler(showClubs);
		router.route("/btc/disciplines/").handler(showDisciplines);
		router.route("/btc/disciplines/:disciplines/groups/").handler(showgroupOverview);
		router.route("/btc/disciplines/:disciplines/groups/:group").handler(showGroup);

		server.requestHandler(router::accept).listen(port);
	}

	private void init(Connection dbConnection) {
		GetDisciplines getDisciplines = new GetDisciplines(dbConnection);
		GetGroup getGroup = new GetGroup(dbConnection);
		CalculateGroupStandings calculate = new CalculateGroupStandings();

		showDisciplines = new ShowDisciplines(getDisciplines);
		showgroupOverview = new ShowGroupOverview(calculate, getGroup);
		showClubs = new ShowClubs();
		showGroup = new ShowGroup();
	}
}
