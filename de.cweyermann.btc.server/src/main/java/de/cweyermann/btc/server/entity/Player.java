package de.cweyermann.btc.server.entity;

public class Player extends AbstractEntity {

    private String surname;

    private String club;

    private String firstName;

    private String tournamentId;

    public Player() {

    }

    public Player(String surname, String firstName, String tournamentId) {
        super();
        this.surname = surname;
        this.firstName = firstName;
        this.tournamentId = tournamentId;
    }

    public String getClub() {
        return club;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getName() {
        String name = firstName + " " + surname;
        if (surname == null) {
            name = firstName;
        }
        if (firstName == null) {
            name = surname;
        }

        return name;
    }

}
