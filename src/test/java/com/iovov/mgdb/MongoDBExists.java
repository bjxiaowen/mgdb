package com.iovov.mgdb;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * 查询数据是否存在
 * @author xiangxiaowen
 *
 */
public class MongoDBExists {
	
	/**
	 * 查询存在regno字段的数据
	 * @throws UnknownHostException
	 */
    public static void existstrue() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection coll = db.getCollection("Car");
        DBObject query = new BasicDBObject("regno", new BasicDBObject("$exists", true));
        DBCursor car = coll.find(query);
        try {
            while (car.hasNext()) {
                System.out.println(car.next());
            }
        } finally {
            car.close();
        }
      /*  { "_id" : { "$oid" : "5710526d4127b8203c40bf26"} , "regno" : [ 31 , 41 , 65 , 75]}*/
    }
    
    /**
     * 查询有speed字段的数据并且大于70
     * @throws UnknownHostException
     */
    public static void existstruewithcriteria() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection coll = db.getCollection("Car");
        DBObject query = new BasicDBObject("speed", new BasicDBObject("$exists", true).append("$gt", 70));
        DBCursor car = coll.find(query);
        System.out.println("-----------------------------------");
        try {
            while (car.hasNext()) {
                System.out.println(car.next());
            }
        } finally {
            car.close();
        }
    }
    
    /**
     * 查询不存在mfdcountry的字段
     * @throws UnknownHostException
     */
    public static void existsfalse() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection coll = db.getCollection("Car");
        DBObject query = new BasicDBObject("mfdcountry", new BasicDBObject("$exists", false));
        DBCursor car = coll.find(query);
        System.out.println("-------------------------------------------------");
        try {
            while (car.hasNext()) {
                System.out.println(car.next());
            }
        } finally {
            car.close();
        }
    }
    
    /**
	 * 查询存在regno字段的数据
	 * @throws UnknownHostException
	 */
    public static void existstruewithnullvalues() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection coll = db.getCollection("Car");
        DBObject query = new BasicDBObject("regno", new BasicDBObject("$exists", true));
        DBCursor car = coll.find(query);
 
        System.out.println("---------------------------------------------");
        try {
            while (car.hasNext()) {
                System.out.println(car.next());
            }
        } finally {
            car.close();
        }
    }
 

	public static void main(String[] args) {
		try {
			//existstrue();
			//existstruewithcriteria();
			//existsfalse();
			existstruewithnullvalues();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
