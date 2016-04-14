package com.iovov.mgdb;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

/**
 * 
 * @author xiangxiaowen
 * @deprecated 1.启动数据库： D:\mongodb\bin>mongo 2.创建数据库：  use mymgdb 3.创建表：db.createCollection("users")
 * 
 */
public class MongoDBExample {

	public static void main(String[] args) {
		User user = createUser();
        DBObject doc = createDBObject(user);
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("mymgdb");
        DBCollection col = db.getCollection("users");
        
        /**
         * 1.添加数据
         */
        WriteResult result = col.insert(doc);
        System.out.println(result.getUpsertedId());
        System.out.println(result.getN());
        System.out.println(result.isUpdateOfExisting());
        
        /**
         * 2.通过id来查询数据
         */
   /*     DBObject query = BasicDBObjectBuilder.start().add("_id", 2).get();
        DBCursor cursor = col.find(query);
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }*/
        
        /**
         * 3.修改数据
         */
      /*  user.setName("Pankaj Andy");
        DBObject query = BasicDBObjectBuilder.start().add("_id", 2).get();
        doc = createDBObject(user);
        WriteResult result = col.update(query, doc);
        System.out.println(result.getUpsertedId());
        System.out.println(result.getN());
        System.out.println(result.isUpdateOfExisting());*/
        
        /**
         * 4.删除
         */
       /* DBObject query = BasicDBObjectBuilder.start().add("_id", 2).get();
        WriteResult result = col.remove(query);
        System.out.println(result.getUpsertedId());
        System.out.println(result.getN());
        System.out.println(result.isUpdateOfExisting());*/
         
        mongo.close();
	}

	private static DBObject createDBObject(User user) {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
        docBuilder.append("_id", user.getId());
        docBuilder.append("name", user.getName());
        docBuilder.append("role", user.getRole());
        docBuilder.append("isEmployee", user.isEmployee());
        return docBuilder.get();
    }
 
    private static User createUser() {
        User u = new User();
        u.setId(2);
        u.setName("Pankaj");
        u.setEmployee(true);
        u.setRole("CEO");
        return u;
    }
}
