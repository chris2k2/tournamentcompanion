package de.cweyermann.btc.server.entity;

import java.util.ArrayList;
import java.util.List;

public class Disciplines extends AbstractEntity {

	public List<String> discs = new ArrayList<>();

	public List<String> getDiscs() {
		return discs;
	}

	public void addDiscipline(String name) {
		this.discs.add(name);
	}

	public void setDiscs(List<String> disciplines) {
		discs = disciplines;
	}

}
