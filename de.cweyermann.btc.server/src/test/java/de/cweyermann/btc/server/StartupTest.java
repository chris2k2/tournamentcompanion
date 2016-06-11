package de.cweyermann.btc.server;

import static com.jayway.restassured.RestAssured.get;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.RestAssured;

import de.cweyermann.btc.server.boundary.tpfile.TpFileUtils;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class StartupTest {

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
	}

	@After
	public void tearDown(TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}

	@Test
	public void checkPaths_Works(TestContext context) throws InterruptedException {
		start(context, "demo.tp", true);

		assertOk("/btc/disciplines/mxb/groups/a");

		assertNotFound("/btASDF");
		assertNotFound("/btc/disciplines/HDA/funny");
	}

	private void start(TestContext context, String name, boolean starts) throws InterruptedException {
		Async async = context.async();

		DeploymentOptions options = new DeploymentOptions()
				.setConfig(new JsonObject().put("httpserver.port", 8080).put("tpfile.path", TpFileUtils.getPath(name)));
		vertx.deployVerticle(Startup.class.getName(), options, ar -> {
			context.assertEquals(starts, ar.succeeded());
			async.complete();
		});

		async.awaitSuccess();
	}

	@Test
	public void noTpFile_startupFails(TestContext context) throws InterruptedException {
		start(context, "asdf.txt", false);
	}

	@Test
	public void invalidFile_failsOnStartup(TestContext context) throws InterruptedException {
		start(context, "doesNotExist.txt", false);
	}

	private void assertOk(String path) {
		assertStatusCode(path, 200);
	}

	private void assertNotFound(String path) {
		assertStatusCode(path, 404);
	}

	private void assertStatusCode(String path, int statusCode) {
		get(path).then().assertThat().statusCode(statusCode);
	}

}
