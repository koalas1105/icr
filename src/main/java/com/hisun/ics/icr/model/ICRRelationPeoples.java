package com.hisun.ics.icr.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "icr_relation_peoples")
public class ICRRelationPeoples implements Serializable {
	private String name;
	private String lastUpdateTime;
	private ArrayList<ICRRelationPeople> relationPeoples = new ArrayList<ICRRelationPeople>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public ArrayList<ICRRelationPeople> getRelationPeoples() {
		return relationPeoples;
	}

	public void setRelationPeoples(ArrayList<ICRRelationPeople> relationPeoples) {
		this.relationPeoples = relationPeoples;
	}

	public void addRelationPeople(ICRRelationPeople people) {
		relationPeoples.add(people);
	}
}
