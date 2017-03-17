package de.cweyermann.btc.server.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MatchTest {

    @Test
    public void twoSets_WinnerIs1() {
        assertEquals("p1", getWinner("21-13", "21-12", null));
    }

    @Test
    public void threeSets_WinnerIs1() {
        assertEquals("p1", getWinner("21-23", "23-21", "21-19"));
    }

    @Test
    public void threeSets_WinnerIs2() {
        assertEquals("p2", getWinner("21-0", "21-23", "21-23"));
    }

    @Test
    public void threeSets_LooserIs2() {
        assertEquals("p2", getLooser("21-0", "21-23", "30-29"));
    }

    @Test
    public void twoSets1LoosesButWalkover_oneWins() {
        assertEquals("p1",
                play("21-23", "21-23", null, false, true).getWinner().getPlayer1().getSurname());
    }

    @Test
    public void twoSets2LoosesButWalkover_2Wins() {
        assertEquals("p2",
                play("21-23", "21-23", null, true, false).getWinner().getPlayer1().getSurname());
    }

    @Test
    public void twoSets_LooserIs1() {
        assertEquals("p1", getLooser("21-23", "21-23", null));
    }

    @Test
    public void twoSets_Walkover2() {
        assertEquals("p1", getLooser("21-23", "21-23", null));
    }

    @Test
    public void twoSetsPlayed_Done() {
        assertTrue(play("21-5", "21-18", null).isDone());
    }

    @Test
    public void noSetsPlayed_Done() {
        assertFalse(play(null, null, null).isDone());
    }

    @Test
    public void walkover1_done() {
        assertTrue(play(null, null, null, true, false).isDone());
    }

    @Test
    public void walkover2_done() {
        assertTrue(play(null, null, null, false, true).isDone());
    }

    @Test
    public void twoSetsWon_getSets2() {
        Match match = play("21-5", "21-9", null);

        assertEquals(2, match.getSets(match.getTeam1()));
    }

    @Test
    public void oneSetsWon_getSets1() {
        Match match = play("21-5", "21-23", "21-10");

        assertEquals(1, match.getSets(match.getTeam2()));
    }

    @Test
    public void oneSetsWo1n_getSets1() {
        Match match = play("21-12", "21-23", "21-23");

        assertEquals(1, match.getSets(match.getTeam1()));
    }

    @Test
    public void twoSets_noSetsWonForLooser() {
        Match match = play("21-12", "25-23", null);

        assertEquals(0, match.getSets(match.getTeam2()));
    }

    @Test
    public void threeSets_twoSetsWonForWinner() {
        Match match = play("21-12", "21-23", "21-8");

        assertEquals(2, match.getSets(match.getTeam1()));
    }

    @Test
    public void threeSetsGetPoints_correct() {
        Match match = play("4-21", "25-23", "21-3");

        assertEquals(4 + 25 + 21, match.getPoints(match.getTeam1()));
        assertEquals(21 + 23 + 3, match.getPoints(match.getTeam2()));
    }

    @Test
    public void twoSets_pointsCorrect() {
        Match match = play("4-21", "21-23", null);

        assertEquals(4 + 21, match.getPoints(match.getTeam1()));
        assertEquals(21 + 23, match.getPoints(match.getTeam2()));
    }

    @Test
    public void matchNoTeams_NoWinner() {
        Match m = new Match(null, null, null, null, null, false, false, 0, 0);

        assertNull(m.getWinner());
        assertNull(m.getLooser());
    }

    @Test
    public void getLooser_worksWithNull() {
        Match m = new Match(new Team(), null, "21-0", null, null, false, false, 1, 1);

        assertNull(m.getLooser());
    }

    @Test
    public void simpleTeam_teamNameIsBuiltCorrectly() {
        Player p1 = new Player();
        p1.setFirstName("first");
        Player p2 = new Player();
        p2.setFirstName("second");

        Team t1 = new Team();
        t1.setPlayer1(p1);
        t1.setPlayer2(p2);
        
        Team t2 = new Team();
        t2.setPlayer1(p1);
        t2.setPlayer2(p2);

        Match m = new Match();
        m.setTeam1(t1);
        m.setTeam2(t2);
        
        assertEquals("first/second - first/second", m.getMatchString());
    }

    @Test
    public void simpleTeamSingles_teamNameIsBuiltCorrectly() {
        Player p1 = new Player();
        p1.setFirstName("first");
        Player p2 = new Player();
        p2.setFirstName("second");

        Team t1 = new Team();
        t1.setPlayer1(p1);
        t1.setPlayer2(p2);

        Match m = new Match();
        m.setTeam1(t1);

        assertEquals("first/second - <unbekannt>", m.getMatchString());
    }

    @Test
    public void noTeams_allUnknown() {
        Match m = new Match();

        assertEquals("<unbekannt> - <unbekannt>", m.getMatchString());
    }
    
    @Test
    public void noSet_correctlyBuilt() {
        assertResult("", null, null, null);
    }
    
    @Test
    public void oneSet_correctlyBuilt() {
        assertResult("21-12", "21-12", null, null);
    }

    @Test
    public void twoSets_correctlyBuilt() {
        assertResult("21-12 21-8", "21-12", "21-8", null);
    }
    
    @Test
    public void threeSets_correctlyBuilt() {
        assertResult("21-12 21-8 21-21", "21-12", "21-8", "21-21");
    }
    
    private void assertResult(String expected, String set1, String set2, String set3) {
        Match m = new Match();
        m.setSet1(set1);
        m.setSet2(set2 );
        m.setSet3(set3);
        assertEquals(expected, m.getResult());
    }
    
    @Test
    public void teamWithNoPlayers_teamNameIsBuiltCorrectly() {
        assertEquals("", new Team().getTeamname());
    }

    private Match play(String set1, String set2, String set3) {
        return play(set1, set2, set3, false, false);
    }

    private Match play(String set1, String set2, String set3, boolean walkover1,
            boolean walkover2) {
        Team p1 = new Team();
        Player p11 = new Player();
        p11.setSurname("p1");
        p1.setPlayer1(p11);

        Team p2 = new Team();
        Player p21 = new Player();
        p21.setSurname("p2");
        p2.setPlayer1(p21);

        Match match = new Match();
        match.setTeam1(p1);
        match.setTeam2(p2);
        match.setSet1(set1);
        match.setSet2(set2);
        match.setSet3(set3);
        match.setWalkoverTeam1(walkover1);
        match.setWalkoverTeam2(walkover2);
        return match;
    }

    private String getWinner(String set1, String set2, String set3) {
        return play(set1, set2, set3).getWinner().getPlayer1().getSurname();
    }

    private String getLooser(String set1, String set2, String set3) {
        return play(set1, set2, set3).getLooser().getPlayer1().getSurname();
    }

}
