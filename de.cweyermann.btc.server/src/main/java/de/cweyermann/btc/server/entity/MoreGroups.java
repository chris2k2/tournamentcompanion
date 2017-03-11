package de.cweyermann.btc.server.entity;

import java.util.List;

public class MoreGroups extends AbstractEntity {

    private List<Group> groups;

    public MoreGroups(List<Group> groups) {
        this.setGroups(groups);
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
