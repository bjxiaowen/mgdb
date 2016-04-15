package com.iovov.mgdb;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * MongoDB查找例子
 * @author xiangxiaowen
 *
 */
public class MongoDBFindOperations {
	/**
	 * 查询所有的数据
	 * @throws UnknownHostException
	 */
    public static void findAll() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient("localhost");
        DB db = mongoClient.getDB("mymgdb");
 
        DBCollection coll = db.getCollection("Car");
        DBCursor carCursor = coll.find();
 
        try {
            while (carCursor.hasNext()) {
                System.out.println(carCursor.next());
            }
        } finally {
            carCursor.close();
        }
    }
     
    /**
     * 查询速度大于50的数据
     * @throws UnknownHostException
     */
    public static void findBYCriteria() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection col = db.getCollection("Car");
 
        DBObject query = new BasicDBObject("speed", new BasicDBObject("$gt", 50));//$gt:大于 ，$lt:小于
        DBCursor carCursor1 = col.find(query);
        System.out.println("­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­");
        try {
            while (carCursor1.hasNext()) {
                System.out.println(carCursor1.next());
            }
        } finally {
            carCursor1.close();
        }
    }
     
    /**
     * 查询id=3的数据
     * @throws UnknownHostException
     */
    public static void findByEquality() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection col = db.getCollection("Car");
        DBObject query = new BasicDBObject("_id", 11);
        DBCursor c1 = col.find(query);
        System.out.println("­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­");
        try {
            while (c1.hasNext()) {
                System.out.println(c1.next());
            }
        } finally {
            c1.close();
        }
    }
    
    /**
     * 查询大于40小于65
     * @throws UnknownHostException
     */
    public static void findByQueryOperators() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection col = db.getCollection("Car");
        DBObject query = new BasicDBObject("speed",new BasicDBObject("$gt", 40).append("$lt", 65));
        DBCursor carCursor1 = col.find(query);
        System.out.println("­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­");
        try {
            while (carCursor1.hasNext()) {
                System.out.println(carCursor1.next());
            }
        } finally {
            carCursor1.close();
        }
    }
    
    /**
     * 大于60并且包含name和speed
     * @throws UnknownHostException
     */
    public static void findByfields() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection col = db.getCollection("Car");
 
        DBObject query = new BasicDBObject("speed",new BasicDBObject("$gt", 60));
        BasicDBObject fields = new BasicDBObject("name", 1).append("speed", 1);
        DBCursor carCursor1 = col.find(query, fields);
        System.out.println("­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­");
        try {
            while (carCursor1.hasNext()) {
                System.out.println(carCursor1.next());
            }
        } finally {
            carCursor1.close();
        }
    }
    
    /**
     * 查询速度大于65并且不包含cno和mfdcountry
     * @throws UnknownHostException
     */
    public static void excludeByfields() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection col = db.getCollection("Car");
 
        DBObject query = new BasicDBObject("speed",new BasicDBObject("$gt", 65));
        // excluding mfdcountry and cno fields by setting to 0
        BasicDBObject fields = new BasicDBObject("mfdcountry", 0).append("cno", 0);
        DBCursor carCursor1 = col.find(query, fields);
        System.out.println("­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­");
        try {
            while (carCursor1.hasNext()) {
                System.out.println(carCursor1.next());
            }
        } finally {
            carCursor1.close();
        }
    }
    
    /**
     * 只显示id这一行数据
     * @throws UnknownHostException
     */
    public static void excludeByIdfield() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection col = db.getCollection("Car");
 
        DBObject query = new BasicDBObject("speed",new BasicDBObject("$gt", 65)); // excluding id field by setting
                                                // to 0
        BasicDBObject fields = new BasicDBObject("_id", 13);
        DBCursor carCursor1 = col.find(query, fields);
        System.out.println("­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­");
        try {
            while (carCursor1.hasNext()) {
                System.out.println(carCursor1.next());
            }
        } finally {
            carCursor1.close();
        }
    }
 
    /**
     * 通过name来排序
     * @throws UnknownHostException
     */
    public static void sortMongodb() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection col = db.getCollection("Car");
        DBCursor carCursor = col.find();
        // sort the car collection in ascending order
        carCursor.sort(new BasicDBObject("name", 1));
         
        try {
            while (carCursor.hasNext()) {
 
                System.out.println(carCursor.next());
            }
        } finally {
            carCursor.close();
        }
    }
     
    /**
     * 查询前2条数据
     * @throws UnknownHostException
     */
    public static void limitMongodb() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection col = db.getCollection("Car");
        // limits to only 2 records
        DBCursor carCursor = col.find().limit(2);
         
        try {
            while (carCursor.hasNext()) {
                System.out.println(carCursor.next());
            }
        } finally {
            carCursor.close();
        }
    }
    
    /**
     * 查询聚集中第2条以后的记录
     * @throws UnknownHostException
     */
    public static void skipMongodb() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection col = db.getCollection("Car");
        // skips the first 10 records
        DBCursor carCursor = col.find().skip(2);
        System.out.println("­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­");
        try {
            while (carCursor.hasNext()) {
 
                System.out.println(carCursor.next());
            }
        } finally {
            carCursor.close();
        }
    }
     
    /**
     * 查询聚集name排序以后的2条记录
     * @throws UnknownHostException
     */
    public static void sortLimitMongodb() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection col = db.getCollection("Car");
        DBCursor carCursor = col.find();
        // combining sort and limit methods
        carCursor.sort(new BasicDBObject("name", 1)).limit(2);
        System.out.println("­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­");
        try {
            while (carCursor.hasNext()) {
                System.out.println(carCursor.next());
            }
        } finally {
            carCursor.close();
        }
    }
     
    /**
     * 插入数组数据
     * @throws UnknownHostException
     */
    public static void insertArray() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection col = db.getCollection("Car"); 
        List<Integer> regno = new ArrayList<Integer>();
        regno.add(31);
        regno.add(41);
        regno.add(65);
        regno.add(75);
        BasicDBObject b1 = new BasicDBObject("regno", regno);
        col.insert(b1);
        DBCursor c3 = col.find();
        try {
            while (c3.hasNext()) {
                System.out.println(c3.next());
            }
        } finally {
            c3.close();
        }
    }
    
    /**
     * 查询数组中大于31小于65
     * @throws UnknownHostException
     */
    public static void queryArray() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection col = db.getCollection("Car");
 
        // querying for array values greater than 31 and less than 65
        DBObject query = new BasicDBObject("regno",new BasicDBObject("$gt", 31).append("$lt", 65));
        DBCursor c1 = col.find(query);
        try {
            while (c1.hasNext()) {
                System.out.println(c1.next());
            }
        } finally {
            c1.close();
        }
    }
    
    /**
     * 查询regno数组中有75的数据
     * @throws UnknownHostException
     */
    public static void queryArrayElement() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
        DBCollection col = db.getCollection("Car");
 
        DBObject query = new BasicDBObject("regno", 75);
        DBCursor c1 = col.find(query);
        try {
            while (c1.hasNext()) {
                System.out.println(c1.next());
            }
        } finally {
            c1.close();
        }
    }
	public static void main(String[] args) {
		try {
			//findAll();//查询所有
			//findBYCriteria();//速度大于50
			//findByEquality();//按id查询
			//findByQueryOperators();//查询大于40小于65
			//findByfields();
			//excludeByfields();
			//excludeByIdfield();
			//sortMongodb();
			//limitMongodb();
			//skipMongodb();
			//sortLimitMongodb();
			//insertArray();
			//queryArray();
			queryArrayElement();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
