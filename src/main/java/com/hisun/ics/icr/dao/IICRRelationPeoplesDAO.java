package com.hisun.ics.icr.dao;

import java.util.List;

import com.hisun.ics.icr.model.ICRRelationPeoples;

public interface IICRRelationPeoplesDAO {
	public boolean save(ICRRelationPeoples entity);
	public ICRRelationPeoples findOne(String name);
	public List<ICRRelationPeoples> findAll();
	public boolean delete(String name);
	public boolean exists(String name);
}
