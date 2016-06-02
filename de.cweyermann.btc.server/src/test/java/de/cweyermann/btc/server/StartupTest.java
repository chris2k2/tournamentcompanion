package de.cweyermann.btc.server;

import static com.jayway.restassured.RestAssured.get;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.RestAssured;

import de.cweyermann.btc.server.boundary.tpfile.TestUtils;
import de.cweyermann.btc.server.boundary.tpfile.TpFileConnectionInvalid;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
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
	public void checkPaths_Works(TestContext context) {
		start(context, "demo.tp");

		
		assertOk("/btc/disciplines/mxb/groups/a");

		assertNotFound("/btASDF");
		assertNotFound("/btc/disciplines/HDA/funny");
	}

	private void start(TestContext context, String name) {
		DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("httpserver.port", 8080)
				.put("tpfile.path", TestUtils.getPath(name)));
		vertx.deployVerticle(Startup.class.getName(), options, context.asyncAssertSuccess());
	}

	@Test(expected=TpFileConnectionInvalid.class)
	public void noTpFile_startupFails(TestContext context) {
		start(context, "asdf.txt");
	}
	

	@Test(expected=TpFileConnectionInvalid.class)
	public void invalidFile_failsOnStartup(TestContext context) {
		start(context, "doesNotExist.txt");
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
