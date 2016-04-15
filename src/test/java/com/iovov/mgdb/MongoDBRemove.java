package com.iovov.mgdb;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
/**
 * 按条件删除数据
 * @author xiangxiaowen
 *
 */
public class MongoDBRemove {

	/**
	 * 删除数据
	 * @throws UnknownHostException
	 */
	public static void removeByQuery() throws UnknownHostException {
        MongoClient m1 = new MongoClient();
        DB db = m1.getDB("mymgdb");
        DBCollection coll = db.getCollection("Car");
        BasicDBObject b1 = new BasicDBObject("speed", new BasicDBObject("$lt",55));
        WriteResult c1 = coll.remove(b1);
        System.out.println("Number of documents removed:" + c1.getN());
    }
 
    public static void removeSingleDoc() throws UnknownHostException {
        MongoClient m1 = new MongoClient();
        DB db = m1.getDB("mymgdb");
        DBCollection coll = db.getCollection("Car");
        BasicDBObject b1 = new BasicDBObject("speed", new BasicDBObject("$gt", 75));
        DBObject doc = coll.findOne(); // get first document
        WriteResult r1 = coll.remove(doc);
        System.out.println("------------------------------------");
 
        System.out.println("Number of documents removed:" + r1.getN());
    }
 
    public static void removeAllDocs() throws UnknownHostException {
        MongoClient m1 = new MongoClient();
        DB db = m1.getDB("mymgdb");
        DBCollection coll = db.getCollection("Car");
        WriteResult r1 = coll.remove(new BasicDBObject());
        System.out.println("------------------------------------");
        System.out.println("Number of documents removed:" + r1.getN());
    }
	public static void main(String[] args) {
		try {
			//removeByQuery();
			//removeSingleDoc();
			removeAllDocs();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
