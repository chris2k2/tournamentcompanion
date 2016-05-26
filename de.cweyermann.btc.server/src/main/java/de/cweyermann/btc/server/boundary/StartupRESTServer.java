package de.cweyermann.btc.server.boundary;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.cweyermann.btc.server.control.AbstractRestControl;
import de.cweyermann.btc.server.control.*;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

// TODO should be refactored to Microservices
public class StartupRESTServer extends AbstractVerticle {

	public void start() {
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router(vertx);

		for (Entry<String, AbstractRestControl<?>> entry : getPath2Handler().entrySet()) {
			router.route(entry.getKey()).handler(entry.getValue());
		}

		server.requestHandler(router::accept).listen(8080);
	}

	private Map<String, AbstractRestControl<?>> getPath2Handler() {
		Map<String, AbstractRestControl<?>> mapping = new HashMap<>();

		mapping.put("/btc/disciplines/:discipline/groups", new ShowGroupOverview());
		mapping.put("/btc/disciplines/", new GetDisciplines());
		mapping.put("/btc/disciplines/:discipline/groups/:group", new ShowGroup());

		return mapping;
	}
}
