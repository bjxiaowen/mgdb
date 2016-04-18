package com.iovov.mgdb;

import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoDBAuthenticationExample {

	public static void main(String[] args) {
		//创建用户和用户名、密码并指定数据库名称
		MongoCredential credential = MongoCredential.createCredential("andy", "mymgdb", "andy".toCharArray());
		//mongoclient创建一个实例
		MongoClient mongoClient = new MongoClient(new ServerAddress(),Arrays.asList(credential));
		
		// 这里的测试db useyour自己的
        DB db = mongoClient.getDB("mymgdb");
        
        DBCollection coll = db.getCollection("Car");
        
        BasicDBObject b1 = new BasicDBObject("name", "Qualis");
        
        DBObject d1 = new BasicDBObject("usersInfo", new BasicDBObject("user","andy").append("db", "mydatabase")).append("showPrivileges", true);
        coll.insert(b1);
 
        System.out.println(db.command(d1));
 
        DBCursor c = coll.find();
        try {
            while (c.hasNext()) {
 
                System.out.println(c.next());
            }
        } finally {
            c.close();
        }
	}

}
