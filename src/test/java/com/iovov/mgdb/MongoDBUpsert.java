package com.iovov.mgdb;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
/**
 * 
 * @author xiangxiaowen
 *
 */
public class MongoDBUpsert {

	/**
	 * 
	 * @throws UnknownHostException
	 */
	public static void upsertTrue() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection coll = db.getCollection("Car");
        BasicDBObject o1 = new BasicDBObject();
        o1.append("$set", new BasicDBObject("name", "Innova"));
        BasicDBObject query = new BasicDBObject().append("speed", 55);
        WriteResult c1 = coll.update(query, o1, true, false);
        DBCursor carcursor = coll.find();
        try {
            while (carcursor.hasNext()) {
                System.out.println(carcursor.next());
            }
        } finally {
            carcursor.close();
        }
    }
 
    public static void upsertBulkUnorderedDocsForUpdate()throws UnknownHostException {
        MongoClient mongoClient = new MongoClient("localhost");
        DB db = mongoClient.getDB("mymgdb");
        DBCollection coll = db.getCollection("Car");
        BulkWriteOperation b1 = coll.initializeUnorderedBulkOperation();
        BasicDBObject o1 = new BasicDBObject();
        o1.append("$setOnInsert",new BasicDBObject("name", "innova").append("speed", 56));
        o1.append("$set", new BasicDBObject("cno", "H456"));
        b1.find(new BasicDBObject("name", "Zen")).upsert().update(o1);
        b1.execute();
        DBCursor c1 = coll.find();
        System.out.println("---------------------------------");
        try {
            while (c1.hasNext()) {
                System.out.println(c1.next());
            }
        } finally {
            c1.close();
        }
 
    }
 
    public static void upsertBulkUnordereDocsForUpdateOne()throws UnknownHostException {
        MongoClient mongoClient = new MongoClient("localhost");
        DB db = mongoClient.getDB("mymgdb");
        DBCollection coll = db.getCollection("Car");
        BulkWriteOperation b1 = coll.initializeUnorderedBulkOperation();
        BasicDBObject o1 = new BasicDBObject();
        o1.append("$setOnInsert",new BasicDBObject("name", "Xylo").append("speed", 67).append(
                        "cno", "H654"));
 
        b1.find(new BasicDBObject("name", "Xylo")).upsert().updateOne(o1);
        b1.execute();
        DBCursor c1 = coll.find();
        System.out.println("---------------------------------");
        try {
            while (c1.hasNext()) {
                System.out.println(c1.next());
            }
        } finally {
            c1.close();
        }
 
    }
 
    public static void upsertBulkForUpdateOneWithOperators()throws UnknownHostException {
        MongoClient mongoClient = new MongoClient("localhost");
        DB db = mongoClient.getDB("mymgdb");
        DBCollection coll = db.getCollection("Car");
        BulkWriteOperation b1 = coll.initializeOrderedBulkOperation();
        BasicDBObject o1 = new BasicDBObject();
        o1.append("$setOnInsert", new BasicDBObject("cno", "H123"));
        o1.append("$set", new BasicDBObject("speed", "63"));
        b1.find(new BasicDBObject("name", "Santro").append("speed", 654)).upsert().updateOne(o1);
        b1.execute();
        DBCursor c1 = coll.find();
        System.out.println("---------------------------------");
        try {
            while (c1.hasNext()) {
                System.out.println(c1.next());
            }
        } finally {
            c1.close();
        }
 
    }
 
    public static void upsertBulkUnorderedDocsForReplaceOne()throws UnknownHostException {
        MongoClient mongoClient = new MongoClient("localhost");
        DB db = mongoClient.getDB("mymgdb");
        DBCollection coll = db.getCollection("Car");
        BulkWriteOperation b1 = coll.initializeOrderedBulkOperation();
        BasicDBObject o1 = new BasicDBObject("name", "Qualis").append("speed",68).append("color", "Palebrown");
        b1.find(new BasicDBObject("name", "Qualis")).upsert().replaceOne(o1);
        b1.execute();
        DBCursor c1 = coll.find();
        System.out.println("---------------------------------");
        try {
            while (c1.hasNext()) {
                System.out.println(c1.next());
            }
        } finally {
            c1.close();
        }
 
    }
 
    public static void main(String[] args) throws UnknownHostException {
 
        //upsertTrue();
    	//upsertBulkUnorderedDocsForUpdate();
        //upsertBulkUnordereDocsForUpdateOne();
    	//upsertBulkForUpdateOneWithOperators();
        upsertBulkUnorderedDocsForReplaceOne();
 
    }

}
