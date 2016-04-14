package com.iovov.mgdb;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

/**
 *  db.createCollection("Persons")
 * @author xiangxiaowen
 *
 */
public class MongoDBInsertExample {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("mymgdb");
         
        DBCollection col = db.getCollection("Persons");
         
        BasicDBObject document = new BasicDBObject();
        
        /**
         * 1
         */
       /* document.append("name", "Pankaj");
        document.append("country", "USA");
        WriteResult result = col.insert(document);
         System.out.println("ID Generated="+document.getObjectId("_id").toString());
        */
        
        /**
         * 2
         */
      /*  document.append("_id",123); //it will be transformed to Integer object.
        document.append("_id","123");
        document.append("_id",new ObjectId());
        WriteResult result = col.insert(document);
        System.out.println("ID Generated="+document.getObjectId("_id").toString());*/
        
        /**
         * 3
         */
        /*document.append("_id",123); //it will be transformed to Integer object.
        document.append("_id","123");
        document.append("_id",new ObjectId());
        WriteResult result = col.insert(document);
        System.out.println("ID Generated="+document.getObjectId("_id").toString());*/
        
        /**
         * 4
         */
       /* BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
        builder.append("name", "Pankaj");
        builder.append("country", "USA");
        builder.append("_id",1234);
        WriteResult result = col.insert(builder.get());
        mongo.close();*/
        
        /**
         * 5.map
         */
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("name", "Pankaj");
        dataMap.put("country", "USA");
        dataMap.put("_id",1235);
        //notice the use of BasicDBObject constructor with Map parameter
        WriteResult result = col.insert(new BasicDBObject(dataMap));
	}
}
