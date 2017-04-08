package de.cweyermann.btc.server.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerTest {

    @Test
    public void playerHasNoSurname_onlyFirstName() {
        Player p = new Player();
        p.setFirstName("first");
        
        assertEquals("first", p.getName());
    }

    @Test
    public void playerHasNoFirstName_onlySurName() {
        Player p = new Player();
        p.setSurname("first");
        
        assertEquals("first", p.getName());
    }
}
