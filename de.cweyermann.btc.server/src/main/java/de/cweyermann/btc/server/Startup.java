package de.cweyermann.btc.server;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import de.cweyermann.btc.server.boundary.rest.AbstractRestControl;
import de.cweyermann.btc.server.boundary.rest.ShowClubs;
import de.cweyermann.btc.server.boundary.rest.ShowDisciplines;
import de.cweyermann.btc.server.boundary.rest.ShowGroupOverview;
import de.cweyermann.btc.server.boundary.rest.ShowMatches;
import de.cweyermann.btc.server.boundary.tpfile.GetDisciplines;
import de.cweyermann.btc.server.boundary.tpfile.GetGroup;
import de.cweyermann.btc.server.boundary.tpfile.GetGroups;
import de.cweyermann.btc.server.boundary.tpfile.GetMatches;
import de.cweyermann.btc.server.boundary.tpfile.TpFileConnectionInvalid;
import de.cweyermann.btc.server.control.CalculateClubStandings;
import de.cweyermann.btc.server.control.CalculateGroupStandings;
import de.cweyermann.btc.server.control.CalculateKoRounds;
import de.cweyermann.btc.server.entity.AbstractEntity;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;

// TODO should be refactored to Microservices
public class Startup extends AbstractVerticle {

    private ShowDisciplines showDisciplines;
    private ShowGroupOverview showgroupOverview;
    private ShowClubs showClubs;
    private ShowMatches showMatches;

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
            if (!accessFile.exists()) {
                throw new TpFileConnectionInvalid("File " + filePath + " is invalid!");
            }

            return DriverManager.getConnection("jdbc:ucanaccess://" + filePath, "",
                    "d4R2GY76w2qzZ");
        } catch (ClassNotFoundException | SQLException e) {
            throw new TpFileConnectionInvalid(e);
        }
    }

    private void startHttpServer(Integer port, Future<Void> startFuture) {
        Router router = Router.router(vertx);

        CorsHandler corsHandler = CorsHandler.create("*");
        corsHandler.allowedMethod(HttpMethod.OPTIONS);
        corsHandler.allowedMethod(HttpMethod.GET);
        corsHandler.allowedHeader("Content-Type");

        registerRoute("clubs", showClubs, router, corsHandler);
        registerRoute("disciplines", showDisciplines, router, corsHandler);
        registerRoute("disciplines/:disciplines", showgroupOverview, router, corsHandler);
        registerRoute("matches", showMatches, router, corsHandler);

        HttpServer server = vertx.createHttpServer();
        server.requestHandler(router::accept).listen(port, x -> startFuture.complete());
    }

    private void registerRoute(String name, AbstractRestControl<? extends AbstractEntity> handler,
            Router router, CorsHandler corsHandler) {
        router.route("/btc/" + name).handler(corsHandler);
        router.route("/btc/" + name).handler(handler);
    }

    private void init(Connection dbConnection) {
        GetDisciplines getDisciplines = new GetDisciplines(dbConnection);
        GetGroup getGroup = new GetGroup(dbConnection);
        GetGroups getGroups = new GetGroups(dbConnection);
        GetMatches getMatches = new GetMatches(dbConnection);
        CalculateGroupStandings calculateGroup = new CalculateGroupStandings();
        CalculateKoRounds calculateKo = new CalculateKoRounds();
        CalculateClubStandings calculateClub = new CalculateClubStandings(10, 6, 4, 2, 1, calculateGroup);
        
        showDisciplines = new ShowDisciplines(getDisciplines);
        showgroupOverview = new ShowGroupOverview(getGroups, calculateGroup, calculateKo, getGroup);
        showClubs = new ShowClubs(getGroups, calculateClub, getGroup);
        showMatches = new ShowMatches(getMatches);
    }
}
