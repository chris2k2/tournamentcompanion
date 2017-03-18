package de.cweyermann.btc.server.boundary.tpfile;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import de.cweyermann.btc.server.entity.Match;

public class GetMatchesTest {
    @Test
    public void demo_worksOnDoubles() {
        GetMatches uut = new GetMatches(TpFileUtils.getConnection("demo.tp"));

        List<Match> matches = uut.all().getMatches();

        assertEquals(26, matches.size());
    }
}
