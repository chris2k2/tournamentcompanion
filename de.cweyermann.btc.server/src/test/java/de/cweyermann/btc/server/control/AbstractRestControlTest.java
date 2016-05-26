package de.cweyermann.btc.server.control;

import static com.jayway.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.matcher.ResponseAwareMatcher;
import com.jayway.restassured.response.Response;

import de.cweyermann.btc.server.boundary.StartupRESTServer;
import de.cweyermann.btc.server.entity.AbstractEntity;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.Router;

@RunWith(VertxUnitRunner.class)
public class AbstractRestControlTest {

	public class DummyEntity extends AbstractEntity {
		public String text;

		public DummyEntity child;
	}

	public class DummyControl extends AbstractRestControl<AbstractEntity> {

		@Override
		public AbstractEntity route(MultiMap params) {
			DummyEntity dummy = new DummyEntity();
			dummy.text = "father";
			dummy.child = new DummyEntity();
			dummy.child.text = "child";
			return dummy;
		}
	}

	private Vertx vertx;

	@Before
	public void setUp(TestContext context) {
		vertx = Vertx.vertx();
	}

	@After
	public void tearDown(TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}

	@Test
	public void mockedDummy_IsCalled() {
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router(vertx);
		router.route("/asdf").handler(new DummyControl());

		server.requestHandler(router::accept).listen(8080);

		get("/asdf").then().assertThat().statusCode(200).and().header("content-type", "application/json; charset=utf-8");
	}
}
