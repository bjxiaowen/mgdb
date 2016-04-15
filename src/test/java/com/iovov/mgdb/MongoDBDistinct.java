package com.iovov.mgdb;

import java.net.UnknownHostException;
import java.util.List;

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
public class MongoDBDistinct {
	
	/**
	 * 按速度去重
	 * @throws UnknownHostException
	 */
	public static void distinct() throws UnknownHostException{
        MongoClient m1 = new MongoClient();
        DB db = m1.getDB("mymgdb");
        DBCollection coll = db.getCollection("Car");
        List<DBObject> cl1= coll.distinct("speed");
        for(int i=0;i<cl1.size();i++){
            System.out.println(cl1.get(i));
        }
    }
	
	/**
	 * 速度大于50按名称去重
	 * @throws UnknownHostException
	 */
	 public static void distinctquery() throws UnknownHostException{
         MongoClient m1 = new MongoClient();
         DB db = m1.getDB("mymgdb");
         DBCollection coll = db.getCollection("Car");
         DBObject o1 = new BasicDBObject("speed",new BasicDBObject("$gt",50));
         List l1= coll.distinct("name", o1);
         System.out.println("-----------------------");
         for(int i=0;i<l1.size();i++){
             System.out.println(l1.get(i));
         }
     }
	
	public static void main(String[] args) {
		try {
			distinct();
			distinctquery();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
