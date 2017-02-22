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
import de.cweyermann.btc.server.boundary.tpfile.GetGroups;
import de.cweyermann.btc.server.boundary.tpfile.TpFileConnectionInvalid;
import de.cweyermann.btc.server.control.CalculateClubStandings;
import de.cweyermann.btc.server.control.CalculateGroupStandings;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

// TODO should be refactored to Microservices
public class Startup extends AbstractVerticle {

	private ShowDisciplines showDisciplines;
	private ShowGroupOverview showgroupOverview;
	private ShowClubs showClubs;
	private ShowGroup showGroup;

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		String filePath = config().getString("tpfile.path");
		Integer port = config().getInteger("httpserver.port");

		Connection dbConnection = makeConnection(filePath);
		init(dbConnection);

		startHttpServer(port, startFuture);
	}

	private Connection makeConnection(String filePath) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			File accessFile = new File(filePath);
			if (!accessFile.exists()) { throw new TpFileConnectionInvalid("File " + filePath + " is invalid!"); }

			return DriverManager.getConnection("jdbc:ucanaccess://" + filePath, "", "d4R2GY76w2qzZ");
		} catch (ClassNotFoundException | SQLException e) {
			throw new TpFileConnectionInvalid(e);
		}
	}

	private void startHttpServer(Integer port, Future<Void> startFuture) {
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router(vertx);

		router.route("/btc/clubs/").handler(showClubs);
		router.route("/btc/disciplines/").handler(showDisciplines);
		router.route("/btc/disciplines/:disciplines").handler(showgroupOverview);
		router.route("/btc/groups/:group").handler(showGroup);

		server.requestHandler(router::accept).listen(port, x -> startFuture.complete());
	}

	private void init(Connection dbConnection) {
		GetDisciplines getDisciplines = new GetDisciplines(dbConnection);
		GetGroup getGroup = new GetGroup(dbConnection);
		GetGroups getGroups = new GetGroups(dbConnection);
		CalculateGroupStandings calculate = new CalculateGroupStandings();
		CalculateClubStandings calculateClub = new CalculateClubStandings(10, 6, 4, 2, 1);

		showDisciplines = new ShowDisciplines(getDisciplines);
		showgroupOverview = new ShowGroupOverview(calculate, getGroup, getGroups);
		showClubs = new ShowClubs(calculateClub, getGroups, getGroup);
		showGroup = new ShowGroup(getGroup, calculate);
	}
}
