package com.iovov.mgdb;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
/**
 * 
 * @author xiangxiaowen
 *
 */
public class MongoDBFindOne {

	/**
	 * 查询第一条数据
	 * 
	 * @throws UnknownHostException
	 */
	public static void emptyFindOne() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost");
		DB db = mongoClient.getDB("mymgdb");
		DBCollection coll = db.getCollection("Car");
		DBObject doc = coll.findOne();
		mongoClient.close();
		System.out.println(doc);
	}

	public static void querySpecification() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost");
		DB db = mongoClient.getDB("mymgdb");
		DBCollection coll = db.getCollection("Car");
		DBObject query = new BasicDBObject("name", "Astar").append("speed", new BasicDBObject("$gt", 30));

		DBObject d1 = coll.findOne(query);
		mongoClient.close();
		System.out.println(d1);
	}

	/**
	 * 查询有字段name和color
	 * @throws UnknownHostException
	 */
	public static void projectionFields() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost");
		DB db = mongoClient.getDB("mymgdb");
		DBCollection coll = db.getCollection("Car");
		BasicDBObject b1 = new BasicDBObject();
		BasicDBObject fields = new BasicDBObject("name", 1).append("color", 1);
		DBObject d1 = coll.findOne(b1, fields);
		mongoClient.close();
		System.out.println(d1);
	}
	
	/**
	 * 查询name=WagonR并且不包含字段mfdcountry，cno
	 * @throws UnknownHostException
	 */
	public static void excludeByfields() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection col = db.getCollection("Car");
        DBObject query = new BasicDBObject("name", "WagonR");
        BasicDBObject fields = new BasicDBObject("mfdcountry", 0).append("cno", 0).append("_id", 0);
 
        DBObject d1 = col.findOne(query, fields);
        System.out.println(d1);
    }
	
	/**
	 * 查询第一条数据获取字段name
	 * @throws UnknownHostException
	 */
	public static void printDoc() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection col = db.getCollection("Car");
        DBObject d1 = col.findOne();
        m1.close();
        System.out.println((d1.get("name")));
    }

	public static void main(String[] args) {
		try {
			// emptyFindOne();
			// querySpecification();
			//projectionFields();
			//excludeByfields();
			printDoc();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
