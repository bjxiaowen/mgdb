package com.iovov.mgdb.dao.impl;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.iovov.mgdb.dao.IPersonDAO;
import com.iovov.mgdb.pojo.Person;
import com.mongodb.WriteResult;

public class PersonDAOImpl implements IPersonDAO {

	private MongoOperations mongoOps;

	private static final String PERSON_COLLECTION = "Person";

	public PersonDAOImpl(MongoOperations mongoOps) {
		this.mongoOps = mongoOps;
	}

	public void create(Person p) {
		this.mongoOps.insert(p, PERSON_COLLECTION);
	}

	public Person readById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoOps.findOne(query, Person.class, PERSON_COLLECTION);
	}

	public void update(Person p) {
		this.mongoOps.save(p, PERSON_COLLECTION);
	}

	public WriteResult deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoOps.remove(query, Person.class, PERSON_COLLECTION);
		return result;
	}

}
