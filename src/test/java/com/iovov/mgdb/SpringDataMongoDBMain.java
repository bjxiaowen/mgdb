package com.iovov.mgdb;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.iovov.mgdb.pojo.Person;
import com.mongodb.MongoClient;

public class SpringDataMongoDBMain {

	public static final String DB_NAME = "mymgdb";
	public static final String PERSON_COLLECTION = "Person";
	public static final String MONGO_HOST = "localhost";
	public static final int MONGO_PORT = 27017;

	public static void main(String[] args) {
		MongoClient mongo = new MongoClient(MONGO_HOST, MONGO_PORT);
		MongoOperations mongoOps = new MongoTemplate(mongo, DB_NAME);
		Person p = new Person("113", "PankajKr", "Bangalore, India");
		mongoOps.insert(p, PERSON_COLLECTION);
		Person p1 = mongoOps.findOne(new Query(Criteria.where("name").is("PankajKr")), Person.class, PERSON_COLLECTION);
		System.out.println(p1);
		mongoOps.dropCollection(PERSON_COLLECTION);
		mongo.close();

	}

}
