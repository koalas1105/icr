package com.hisun.ics.icr.model;

import java.io.Serializable;


public class ICRRelationPeople implements Serializable {
	private String name;
	private String relationType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	
	public String toString() {
		return name + ":" + relationType;
	}

}