package de.cweyermann.btc.server.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TeamTest {

    @Test
    public void simpleTeam_teamNameIsBuiltCorrectly() {
        Player p1 = new Player();
        p1.setSurname("first");
        Player p2 = new Player();
        p2.setSurname("second");

        Team t = new Team();
        t.setPlayer1(p1);
        t.setPlayer2(p2);
        
        assertEquals("first/second", t.getTeamname());
    }
    

    @Test
    public void simpleTeamSingles_teamNameIsBuiltCorrectly() {
        Player p1 = new Player();
        p1.setSurname("first");

        Team t = new Team();
        t.setPlayer1(p1);
        
        assertEquals("first", t.getTeamname());
    }
    

    @Test
    public void teamWithNoPlayers_teamNameIsBuiltCorrectly() {
        assertEquals("", new Team().getTeamname());
    }
}
