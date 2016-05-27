package de.cweyermann.btc.server.boundary.rest;

import static com.jayway.restassured.RestAssured.get;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.RestAssured;

import de.cweyermann.btc.server.boundary.rest.StartupRESTServer;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class StartupRESTServerIT {

	private Vertx vertx;

	@BeforeClass
	public static void configureRestAssured() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@AfterClass
	public static void unconfigureRestAssured() {
		RestAssured.reset();
	}
	

	@Before
	public void setUp(TestContext context) {
		vertx = Vertx.vertx();
		vertx.deployVerticle(StartupRESTServer.class.getName(), context.asyncAssertSuccess());
	}

	@After
	public void tearDown(TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}

	@Test
	public void checkPaths_Works(TestContext context) {
		assertOk("/btc/disciplines/dda/groups");
		assertOk("/btc/disciplines/mxb/groups/a");
		assertOk("/btc/disciplines/hda/groups/ko");
		assertOk("/btc/disciplines/mxa/groups/loosersko");
		assertOk("/btc/disciplines");

		assertNotFound("/btASDF");
		assertNotFound("/btc/disciplines/HDA/funny");
	}

	private void assertOk(String path) {
		assertStatusCode(path, 200);
	}

	private void assertNotFound(String path) {
		assertStatusCode(path, 404);
	}

	private void assertStatusCode(String path, int statusCode) {
		get(path).then()
	      .assertThat()
	      .statusCode(statusCode);
	}

}
