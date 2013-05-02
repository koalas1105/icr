package com.hisun.ics.icr.dao;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hisun.ics.icr.model.ICRCrawlData;
import com.hisun.ics.icr.model.ICRRelationPeoples;

public class ICRCrawlDataDAOImpl implements IICRCrawlDataDAO {
	MongoOperations mongoOperation = null;

	public MongoOperations getMongoOperations() {
		return mongoOperation;
	}

	public void setMongoOperations(MongoOperations mongoOperation) {
		this.mongoOperation = mongoOperation;
	}

	@Override
	public boolean save(ICRCrawlData entity) {
		mongoOperation.save(entity);
		return true;
	}

	@Override
	public List<ICRCrawlData> findAll(String name) {
		return mongoOperation.find(new Query(Criteria.where("name").is(name)), ICRCrawlData.class);
	}

	@Override
	public boolean delete(String name) {
		mongoOperation.remove(ICRCrawlData.class, name);
		return false;
	}

	@Override
	public boolean exists(String name) {
		ICRCrawlData entity = findOne(name);
		return entity != null;
	}

	@Override
	public ICRCrawlData findOne(String name) {
		return mongoOperation.findOne(
				new Query(Criteria.where("name").is(name)),
				ICRCrawlData.class);
	}
	
	@Override
	public ICRCrawlData findOneById(String id) {
		return mongoOperation.findOne(
				new Query(Criteria.where("_id").is(id)),
				ICRCrawlData.class);
	}
}
