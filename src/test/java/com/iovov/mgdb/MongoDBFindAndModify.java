package com.iovov.mgdb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
/**
 * 先插入之后在根据条件更新数据只更新一条数据
 * @author xiangxiaowen
 *
 */
public class MongoDBFindAndModify {

	public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost");
        DB db = mongoClient.getDB("mymgdb");
        DBCollection coll = db.getCollection("Car");
        BasicDBObject obj = new BasicDBObject();
        obj.append("name", "Volkswagen");
        obj.append("color", "JetBlue");
        obj.append("cno", "H672");
        obj.append("speed", 62);
        obj.append("mfdcountry", "Italy");
        coll.insert(obj);
        DBObject query = new BasicDBObject("speed",new BasicDBObject("$lt", 80));
        DBObject update = new BasicDBObject();
        update.put("$set", new BasicDBObject("color", "Blue"));
        DBCursor cursor = coll.find();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } finally {
            cursor.close();
        }
        coll.findAndModify(query, update);
	}
}
