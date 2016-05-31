package de.cweyermann.btc.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.cweyermann.btc.server.boundary.rest.AbstractRestControl;
import de.cweyermann.btc.server.boundary.rest.ShowClubStandings;
import de.cweyermann.btc.server.boundary.rest.ShowDisciplines;
import de.cweyermann.btc.server.boundary.rest.ShowGroup;
import de.cweyermann.btc.server.boundary.rest.ShowGroupOverview;
import de.cweyermann.btc.server.boundary.tpfile.GetDisciplines;
import de.cweyermann.btc.server.control.CalculateGroupStandings;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

// TODO should be refactored to Microservices
public class Startup extends AbstractVerticle {

	private ShowDisciplines showDisciplines;
	private ShowGroupOverview showgroupOverview;

	public void start() {
		String filePath = config().getString("tpfile.path");
		Integer port = config().getInteger("httpserver.port");

		init(filePath);
		
		startHttpServer(port);
	}

	private void startHttpServer(Integer port) {
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router(vertx);

		Map<String, AbstractRestControl<?>> handlerMap = getPath2Handlers();
		for (Entry<String, AbstractRestControl<?>> entry : handlerMap.entrySet()) {
			router.route(entry.getKey()).handler(entry.getValue());
		}

		server.requestHandler(router::accept).listen(port);
	}

	private Map<String, AbstractRestControl<?>> getPath2Handlers() {
		
		Map<String, AbstractRestControl<?>> mapping = new HashMap<>();

		mapping.put("/btc/disciplines/:discipline/groups", showgroupOverview);
		mapping.put("/btc/disciplines/", showDisciplines);
		mapping.put("/btc/disciplines/:discipline/groups/:group", new ShowGroup());
		mapping.put("/btc/clubstandings", new ShowClubStandings());

		return mapping;
	}

	private void init(String filePath) {
		GetDisciplines getDisciplines = new GetDisciplines(filePath);
		showDisciplines = new ShowDisciplines(getDisciplines);
	
		CalculateGroupStandings calculate = new CalculateGroupStandings();
		showgroupOverview = new ShowGroupOverview(calculate );
	}
}
