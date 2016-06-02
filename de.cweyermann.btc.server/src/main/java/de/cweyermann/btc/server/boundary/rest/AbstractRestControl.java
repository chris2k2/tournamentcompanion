package de.cweyermann.btc.server.boundary.rest;

import de.cweyermann.btc.server.entity.AbstractEntity;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public abstract class AbstractRestControl<T extends AbstractEntity> extends AbstractVerticle
		implements Handler<RoutingContext> {

	public abstract T route(MultiMap params);

	public void handle(RoutingContext context) {
		T entry = route(context.request().params());

		String encodePrettily = Json.encodePrettily(entry);
		context.response().putHeader("content-type", "application/json; charset=utf-8")
				.putHeader("cache-control", "public, max-age=300").end(encodePrettily);
	}

}
