package com.iovov.mgdb;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * 在数据库mymgdb新建car
 * 
 * @author xiangxiaowen
 *
 */
public class MongoDBBulkInsert {

	// 插入多个集合
	public static void insertmultipledocs() throws UnknownHostException {

		// 得到一个新的数据库连接假设它正在运行
		MongoClient mongoClient = new MongoClient("localhost");

		// 使用mymgdb数据库
		DB db = mongoClient.getDB("mymgdb");
		// 获取集合对象
		DBCollection coll = db.getCollection("Car");
		// 创建一个新的对象d1
		DBObject d1 = new BasicDBObject();

		d1.put("_id", 11);
		d1.put("name", "WagonR");
		d1.put("color", "MetallicSilver");
		d1.put("cno", "H141");
		d1.put("mfdcountry", "Australia");
		d1.put("speed", 66);

		DBObject d2 = new BasicDBObject();

		d2.put("_id", 12);
		d2.put("name", "Xylo");
		d2.put("color", "JetBlue");
		d2.put("cno", "H142");
		d2.put("mfdcountry", "Europe");
		d2.put("speed", 69);

		DBObject d3 = new BasicDBObject();

		d3.put("_id", 13);
		d3.put("name", "Alto800");
		d3.put("color", "JetGrey");
		d3.put("cno", "H143");
		d3.put("mfdcountry", "Austria");
		d3.put("speed", 74);

		// 创建一个新的列表
		List<DBObject> docs = new ArrayList<DBObject>();

		docs.add(d1);
		docs.add(d2);
		docs.add(d3);

		coll.insert(docs);

		// 将结果存储在游标
		DBCursor carmuldocs = coll.find();

		// 打印的内容游标
		try {
			while (carmuldocs.hasNext()) {
				System.out.println(carmuldocs.next());
			}
		} finally {
			carmuldocs.close();// 关闭游标
		}

	}

	// 添加不同的字段到数据文档中去
	public static void insertsomefieldsformultipledocs() throws UnknownHostException {

		MongoClient mongoClient = new MongoClient("localhost");
		DB db = mongoClient.getDB("mymgdb");
		DBCollection coll = db.getCollection("Car");
		DBObject d1 = new BasicDBObject();
		d1.put("name", "Indica");
		d1.put("color", "Silver");
		d1.put("cno", "H154");

		DBObject d2 = new BasicDBObject();
		d2.put("_id", 43);
		d2.put("name", "Astar");
		d2.put("speed", 79);

		List<DBObject> docs = new ArrayList<DBObject>();
		docs.add(d1);
		docs.add(d2);

		coll.insert(docs);

		DBCursor carmuldocs = coll.find();
		System.out.println("-----------------------------------------------");
		try {
			while (carmuldocs.hasNext()) {
				System.out.println(carmuldocs.next());
			}
		} finally {
			carmuldocs.close();
		}
	}

	// 添加重复的id到文档中 违反主键约束
	public static void insertduplicatedocs() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost");
		DB db = mongoClient.getDB("mymgdb");
		DBCollection coll = db.getCollection("Car");

		DBObject d1 = new BasicDBObject();
		d1.put("_id", 11);
		d1.put("name", "WagonR-Lxi");
		coll.insert(d1);
		DBCursor carmuldocs = coll.find();
		System.out.println("-----------------------------------------------");
		try {
			while (carmuldocs.hasNext()) {
				System.out.println(carmuldocs.next());
			}
		} finally {
			carmuldocs.close();
		}
	}

	// method that performs bulk insert for ordered list
	public static void insertbulkordereddocs() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost");
		DB db = mongoClient.getDB("mymgdb");
		DBCollection coll = db.getCollection("Car");
		DBObject d1 = new BasicDBObject();

		d1.put("name", "Palio");
		d1.put("color", "Purple");
		d1.put("cno", "H183");
		d1.put("mfdcountry", "Venice");
		d1.put("speed", 82);

		DBObject d2 = new BasicDBObject();

		d2.put("name", "Micra");
		d2.put("color", "Lime");
		d2.put("cno", "H186");
		d2.put("mfdcountry", "Ethopia");
		d2.put("speed", 84);

		// initialize and create ordered bulk
		BulkWriteOperation b1 = coll.initializeOrderedBulkOperation();

		b1.insert(d1);
		b1.insert(d2);

		// invoking execute
		BulkWriteResult r1 = b1.execute();

		DBCursor carmuldocs = coll.find();

		System.out.println("-----------------------------------");

		try {
			while (carmuldocs.hasNext()) {
				System.out.println(carmuldocs.next());
			}
		} finally {
			carmuldocs.close();// close the cursor
		}
	}

	// 插入批量无序的数据
	public static void insertbulkunordereddocs() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost");
		DB db = mongoClient.getDB("mymgdb");
		DBCollection coll = db.getCollection("Car");
		DBObject d1 = new BasicDBObject();

		d1.put("name", "Suzuki S-4");
		d1.put("color", "Yellow");
		d1.put("cno", "H167");
		d1.put("mfdcountry", "Italy");
		d1.put("speed", 54);

		DBObject d2 = new BasicDBObject();

		d2.put("name", "Santro-Xing");
		d2.put("color", "Cyan");
		d2.put("cno", "H164");
		d2.put("mfdcountry", "Holand");
		d2.put("speed", 76);

		BulkWriteOperation b1 = coll.initializeUnorderedBulkOperation();
		b1.insert(d1);
		b1.insert(d2);

		BulkWriteResult r1 = b1.execute();
		DBCursor carmuldocs = coll.find();

		System.out.println("-----------------------------------------------");
		try {
			while (carmuldocs.hasNext()) {
				System.out.println(carmuldocs.next());
			}
		} finally {
			carmuldocs.close();
		}

	}
	
	

	public static void main(String[] args) {
		try {
			// insertmultipledocs();
			// insertsomefieldsformultipledocs();
			// insertduplicatedocs();
			insertbulkunordereddocs();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
