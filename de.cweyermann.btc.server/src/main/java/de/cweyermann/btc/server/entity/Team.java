package de.cweyermann.btc.server.entity;

public class Team extends AbstractEntryWithId {

    private Player player1;
    private Player player2;

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    @Override
    public String toString() {
        return getId() + "";
    }

    public String getTeamname() {
        String teamName = "";

        if (player1 != null) {
            teamName += player1.getName();
        }

        if (player2 != null) {
            teamName += "/" + player2.getName();
        }
        
        return teamName;
    }

}
