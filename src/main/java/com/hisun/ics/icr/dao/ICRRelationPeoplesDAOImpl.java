package com.hisun.ics.icr.dao;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hisun.ics.icr.model.ICRRelationPeoples;

public class ICRRelationPeoplesDAOImpl implements IICRRelationPeoplesDAO {
	MongoOperations mongoOperation = null;

	public MongoOperations getMongoOperations() {
		return mongoOperation;
	}

	public void setMongoOperations(MongoOperations mongoOperation) {
		this.mongoOperation = mongoOperation;
	}

	@Override
	public boolean save(ICRRelationPeoples entity) {
		mongoOperation.save(entity);
		return true;
	}

	@Override
	public boolean delete(String name) {
		mongoOperation.remove(ICRRelationPeoples.class, name);
		return false;
	}

	@Override
	public boolean exists(String name) {
		ICRRelationPeoples entity = findOne(name);
		return entity != null;
	}

	@Override
	public ICRRelationPeoples findOne(String name) {
		return mongoOperation.findOne(
				new Query(Criteria.where("name").is(name)),
				ICRRelationPeoples.class);
	}

	@Override
	public List<ICRRelationPeoples> findAll() {
		return mongoOperation.findAll(ICRRelationPeoples.class);
	}
}
