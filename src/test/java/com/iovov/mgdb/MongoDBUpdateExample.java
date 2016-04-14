package com.iovov.mgdb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class MongoDBUpdateExample {

	/**
	 * 修改数据前后对比
	 */
	public static void modBeforeAndAfter() {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mymgdb");
		DBCollection col = db.getCollection("Persons");

		/*
		 * 更新前数据 db.Persons.find() "_id" : ObjectId("570f0ad14127b83c54e38d1a"),
		 * "name" : "Pankaj", "country" : "USA" } "_id" :
		 * ObjectId("570f0b724127b83f7c34c73e") } "_id" : 1234, "name" :
		 * "Pankaj", "country" : "USA" } "_id" : 1235, "name" : "Pankaj",
		 * "country" : "USA" }
		 */

		// Update single field in a single document
		DBObject query = new BasicDBObject("name", "Pankaj");
		DBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("country", "India"));

		WriteResult result = col.update(query, update);
		/*
		 * 修改后的数据 db.Persons.find() "_id" :
		 * ObjectId("570f0ad14127b83c54e38d1a"), "name" : "Pankaj", "country" :
		 * "India" } "_id" : ObjectId("570f0b724127b83f7c34c73e") } "_id" :
		 * 1234, "name" : "Pankaj", "country" : "USA" } "_id" : 1235, "name" :
		 * "Pankaj", "country" : "USA" }
		 */

		mongo.close();
	}

	/**
	 * append修改数据前后对比
	 */
	public static void appendModBeforeAndAfter() {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mymgdb");

		DBCollection col = db.getCollection("Persons");

		/*
		 * db.Persons.find() "_id" : ObjectId("570f0ad14127b83c54e38d1a"),
		 * "name" : "Pankaj", "country" : "India" } "_id" :
		 * ObjectId("570f0b724127b83f7c34c73e") } "_id" : 1234, "name" :
		 * "Pankaj", "country" : "USA" } "_id" : 1235, "name" : "Pankaj",
		 * "country" : "USA" }
		 */

		// 在一个文档中更新多个字段
		DBObject query = new BasicDBObject("name", "Pankaj");
		DBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("country", "India").append("name", "David New"));

		WriteResult result = col.update(query, update);
		/*
		 * db.Persons.find() "_id" : ObjectId("570f0ad14127b83c54e38d1a"),
		 * "name" : "David New", "country" : "India" } "_id" :
		 * ObjectId("570f0b724127b83f7c34c73e") } "_id" : 1234, "name" :
		 * "Pankaj", "country" : "USA" } "_id" : 1235, "name" : "Pankaj",
		 * "country" : "USA" }
		 */

		mongo.close();

	}

	/**
	 * 通过id来更新数据
	 */
	public static void byIdUpdate() {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mymgdb");
		DBCollection col = db.getCollection("Persons");

		/*
		 * db.Persons.find() "_id" : ObjectId("570f0ad14127b83c54e38d1a"),
		 * "name" : "David New", "country" : "India" } "_id" :
		 * ObjectId("570f0b724127b83f7c34c73e") } "_id" : 1234, "name" :
		 * "Pankaj", "country" : "USA" } "_id" : 1235, "name" : "Pankaj",
		 * "country" : "USA" }
		 */

		// Add a new field in a single document
		DBObject query = new BasicDBObject("_id", 1234);
		DBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("city", "San Jose"));

		WriteResult result = col.update(query, update);

		/*
		 * db.Persons.find() "_id" : ObjectId("570f0ad14127b83c54e38d1a"),
		 * "name" : "David New", "country" : "India" } "_id" :
		 * ObjectId("570f0b724127b83f7c34c73e") } "_id" : 1235, "name" :
		 * "Pankaj", "country" : "USA" } "_id" : 1234, "name" : "Pankaj",
		 * "country" : "USA", "city" : "San Jose" }
		 */

		mongo.close();
	}

	public static void updateChildren() {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mymgdb");

		DBCollection col = db.getCollection("Persons");

		/*
		 * db.Persons.find() "_id" : ObjectId("570f0ad14127b83c54e38d1a"),
		 * "name" : "David New", "country" : "India" } "_id" :
		 * ObjectId("570f0b724127b83f7c34c73e") } "_id" : 1235, "name" :
		 * "Pankaj", "country" : "USA" } "_id" : 1234, "name" : "Pankaj",
		 * "country" : "USA", "city" : "San Jose" }
		 */
		// Update sub-document in a single document
		DBObject query = new BasicDBObject("_id", 1235);
		DBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("address.city", "Santa Clara"));

		WriteResult result = col.update(query, update);

		/**
		 * db.Persons.find() "_id" : ObjectId("570f0ad14127b83c54e38d1a"),
		 * "name" : "David New", "country" : "India" } "_id" :
		 * ObjectId("570f0b724127b83f7c34c73e") } "_id" : 1234, "name" :
		 * "Pankaj", "country" : "USA", "city" : "San Jose" } "_id" : 1235,
		 * "name" : "Pankaj", "country" : "USA", "address" : { "city" :
		 * "Santa Clara" } }
		 */

		mongo.close();
	}

	public static void updateChildrenUnSet() {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mymgdb");

		DBCollection col = db.getCollection("Persons");

		/*
		 * db.Persons.find() "_id" : ObjectId("570f0ad14127b83c54e38d1a"),
		 * "name" : "David New", "country" : "India" } "_id" :
		 * ObjectId("570f0b724127b83f7c34c73e") } "_id" : 1234, "name" :
		 * "Pankaj", "country" : "USA", "city" : "San Jose" } "_id" : 1235,
		 * "name" : "Pankaj", "country" : "USA", "address" : { "city" :
		 * "Santa Clara" } }
		 */

		DBObject query = new BasicDBObject("_id", 1235);
		DBObject update = new BasicDBObject();
		update.put("$unset", new BasicDBObject("address.city", ""));

		WriteResult result = col.update(query, update);
		/*
		 * db.Persons.find() "_id" : ObjectId("570f0ad14127b83c54e38d1a"),
		 * "name" : "David New", "country" : "India" } "_id" :
		 * ObjectId("570f0b724127b83f7c34c73e") } "_id" : 1234, "name" :
		 * "Pankaj", "country" : "USA", "city" : "San Jose" } "_id" : 1235,
		 * "name" : "Pankaj", "country" : "USA", "address" : { } }
		 */

		mongo.close();

	}

	/**
	 * 如果数据存在就更新不存在就插入
	 */
	public static void updataOrSave() {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mymgdb");
		DBCollection col = db.getCollection("Persons");

		/*
		 * db.Persons.find() "_id" : ObjectId("570f0ad14127b83c54e38d1a"),
		 * "name" : "David New", "country" : "India" } "_id" :
		 * ObjectId("570f0b724127b83f7c34c73e") } "_id" : 1234, "name" :
		 * "Pankaj", "country" : "USA", "city" : "San Jose" } "_id" : 1235,
		 * "name" : "Pankaj", "country" : "USA", "address" : { } }
		 */

		// insert document if no match found
		DBObject query = new BasicDBObject("name", "Lisa");
		DBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("salary", "San Jose"));

		WriteResult result = col.update(query, update, true, false);

		/*
		 * db.Persons.find() "_id" : ObjectId("570f0ad14127b83c54e38d1a"),
		 * "name" : "David New", "country" : "India" } "_id" :
		 * ObjectId("570f0b724127b83f7c34c73e") } "_id" : 1234, "name" :
		 * "Pankaj", "country" : "USA", "city" : "San Jose" } "_id" : 1235,
		 * "name" : "Pankaj", "country" : "USA", "address" : { } } "_id" :
		 * ObjectId("570f5d70f59ca21a170efdd7"), "name" : "Lisa", "city" :
		 * "San Jose" }
		 */

		mongo.close();
	}

	/**
	 * 更新多个文档 MongoDB Java驱动程序API提供了updateMulti方法,我们可以使用它来更新多个文档,在以下示例中,
	 * 我们正在更新所有文档工资到1000年的不到1000。这也是公司美元更新选项的例子之一。
	 */
	public static void updateMultipleDocuments() {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mymgdb");

		DBCollection col = db.getCollection("Persons");
		/*
		 * db.Persons.find() "_id" : ObjectId("570f0ad14127b83c54e38d1a"),
		 * "name" : "David New", "country" : "India" } "_id" :
		 * ObjectId("570f0b724127b83f7c34c73e") } "_id" : 1234, "name" :
		 * "Pankaj", "country" : "USA", "city" : "San Jose" } "_id" : 1235,
		 * "name" : "Pankaj", "country" : "USA", "address" : { } } "_id" :
		 * ObjectId("570f5d70f59ca21a170efdd7"), "name" : "Lisa", "city" :
		 * "San Jose", "salary" : 2000 }
		 */

		DBObject query = new BasicDBObject("salary", new BasicDBObject("$lt", 9000));//小于9000的加+1000
		DBObject update = new BasicDBObject();
		update.put("$inc", new BasicDBObject("salary", 1000));

		WriteResult result = col.updateMulti(query, update);
		 /*
		  * db.Persons.find()
		 "_id" : ObjectId("570f0ad14127b83c54e38d1a"), "name" : "David New", "country" : "India" }
		 "_id" : ObjectId("570f0b724127b83f7c34c73e") }
		 "_id" : 1234, "name" : "Pankaj", "country" : "USA", "city" : "San Jose" }
		 "_id" : 1235, "name" : "Pankaj", "country" : "USA", "address" : {  } }
		 "_id" : ObjectId("570f5d70f59ca21a170efdd7"), "name" : "Lisa", "city" : "San Jose", "salary" : 3000 }
		  */
		mongo.close();
	}

	public static void main(String[] args) {
		// modBeforeAndAfter();
		// appendModBeforeAndAfter();
		// byIdUpdate();
		// updateChildren();
		// updateChildrenUnSet();
		// updataOrSave();
		updateMultipleDocuments();
	}

}
