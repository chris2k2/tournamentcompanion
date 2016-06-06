package de.cweyermann.btc.server.entity;

import java.util.HashMap;
import java.util.Map;

public abstract class WithChilds extends AbstractEntity {

	private Map<Integer, String> id2Names = new HashMap<>();
	
	public Map<Integer, String> getChilds() {
		return id2Names;
	}

	public void addChilds(Integer id, String name) {
		id2Names.put(id, name);
	}
}
