package com.hisun.ics.icr.dao;

import java.util.List;

import com.hisun.ics.icr.model.ICRCrawlData;
import com.hisun.ics.icr.model.ICRRelationPeoples;

public interface IICRCrawlDataDAO {
	public boolean save(ICRCrawlData entity);
	public ICRCrawlData findOne(String name);
	public ICRCrawlData findOneById(String id);
	public List<ICRCrawlData> findAll(String name);
	public boolean delete(String name);
	public boolean exists(String name);
}
