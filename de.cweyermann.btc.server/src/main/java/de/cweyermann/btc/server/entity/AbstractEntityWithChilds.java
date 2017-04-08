package de.cweyermann.btc.server.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractEntityWithChilds extends AbstractEntity {

    private Map<Integer, String> id2Names = new HashMap<>();

    public Map<Integer, String> getChilds() {
        return id2Names;
    }

    public void addChilds(Integer id, String name) {
        id2Names.put(id, name);
    }

    public List<IdName> getIdNames() {
        List<IdName> idnames = new ArrayList<>();

        for (Entry<Integer, String> entry : id2Names.entrySet()) {
            IdName current = new IdName();
            current.setId(entry.getKey());
            current.setName(entry.getValue());
            idnames.add(current);
        }

        return idnames;
    }
}
