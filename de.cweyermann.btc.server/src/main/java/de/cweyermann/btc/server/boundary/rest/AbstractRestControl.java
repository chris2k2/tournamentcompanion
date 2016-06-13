package de.cweyermann.btc.server.boundary.rest;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Stopwatch;

import de.cweyermann.btc.server.entity.AbstractEntity;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public abstract class AbstractRestControl<T extends AbstractEntity> extends AbstractVerticle
		implements Handler<RoutingContext> {

	private static final Logger LOG = LogManager.getLogger(AbstractRestControl.class.getName());

	public abstract T route(MultiMap params);

	public void handle(RoutingContext context) {
		LOG.info("Request incomming: " + context.request().path());
		Stopwatch stopwatch = Stopwatch.createStarted();
		
		T entry = route(context.request().params());
		String encodePrettily = Json.encodePrettily(entry);
		context.response().putHeader("content-type", "application/json; charset=utf-8")
				.putHeader("cache-control", "public, max-age=300").end(encodePrettily);
		
		LOG.info("Took : {} ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
	}

}
