package de.cweyermann.btc.server.entity;

import java.util.ArrayList;
import java.util.List;

public class Groups extends AbstractEntity {

	private List<Group> groups = new ArrayList<>();

	public List<Group> getGroups() {
		return groups;
	}

	public void addGroup(Group group) {
		groups.add(group);
	}
}
