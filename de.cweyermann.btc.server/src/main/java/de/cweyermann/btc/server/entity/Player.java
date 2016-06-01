package de.cweyermann.btc.server.entity;

public class Player extends AbstractEntity {

	private String surname;

	public Player() {

	}

	public Player(String surname, String firstName, String tournamentId) {
		super();
		this.surname = surname;
		this.firstName = firstName;
		this.tournamentId = tournamentId;
	}

	private String firstName;

	private String tournamentId;

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(String tournamentId) {
		this.tournamentId = tournamentId;
	}

}
