package com.iovov.mgdb;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDBSort {

	// 根据名称按升序排序
    public static void sortAscending() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB d1 = m1.getDB("mymgdb");
        DBCollection coll = d1.getCollection("Car");
        DBCursor car = coll.find();
        car.sort(new BasicDBObject("name", 1));
        try {
            while (car.hasNext()) {
                System.out.println(car.next());
            }
        } finally {
            car.close();
        }
    }
 
    // 基于速度在降序排序
    public static void sortDescending() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB d1 = m1.getDB("mymgdb");
        DBCollection coll = d1.getCollection("Car");
        DBCursor car = coll.find();
        car.sort(new BasicDBObject("speed", -1));
        System.out.println("Sorts in Descending order-------------------------------------------");
        try {
            while (car.hasNext()) {
                System.out.println(car.next());
            }
        } finally {
            car.close();
        }
 
    }
 
    // 在基于速度和升序降序排序
    public static void sortDescendingAscending() throws UnknownHostException {
        MongoClient m1 = new MongoClient("localhost");
        DB d1 = m1.getDB("mymgdb");
        DBCollection coll = d1.getCollection("Car");
 
        DBCursor car = coll.find();
        car.sort(new BasicDBObject("speed", -1).append("name", 1));
        System.out.println("Combining two fields to sort in ascending and descending orders-----------------");
        try {
            while (car.hasNext()) {
                System.out.println(car.next());
            }
        } finally {
            car.close();
        }
    }
 
    public static void sortlimit() throws UnknownHostException {
 
        MongoClient m1 = new MongoClient("localhost");
        DB d1 = m1.getDB("mymgdb");
 
        DBCollection coll = d1.getCollection("Car");
        DBObject q1 = new BasicDBObject("speed", new BasicDBObject("$gt", 15));
 
        BasicDBObject fields = new BasicDBObject("name", 1).append("speed", 1);
        DBCursor car = coll.find(q1, fields);
        car.sort(new BasicDBObject("name", -1)).limit(2);
        System.out.println("limit--------------------------");
        try {
            while (car.hasNext()) {
                System.out.println(car.next());
            }
        } finally {
            car.close();
        }
 
    }
 
    public static void sortProjectionfields() throws UnknownHostException {
 
        MongoClient m1 = new MongoClient("localhost");
        DB db = m1.getDB("mymgdb");
 
        DBCollection col = db.getCollection("Car");
 
        DBObject query = new BasicDBObject("speed",
                new BasicDBObject("$gt", 40));
        BasicDBObject fields = new BasicDBObject("name", 1).append("speed", 1);
        DBCursor carCursor1 = col.find(query, fields);
        System.out.println("------------------------------------------------------");
        try {
            while (carCursor1.hasNext()) {
                System.out.println(carCursor1.next());
            }
        } finally {
            carCursor1.close();
        }
    }
 
    public static void sortnaturalordering() throws UnknownHostException {
 
        MongoClient m1 = new MongoClient("localhost");
        DB d1 = m1.getDB("mymgdb");
 
        DBCollection coll = d1.getCollection("Car");
        DBObject q1 = new BasicDBObject("speed", new BasicDBObject("$gt", 15));
        BasicDBObject fields = new BasicDBObject("name", 1).append("speed", 1);
 
        DBCursor car = coll.find(q1, fields);
        car.sort(new BasicDBObject("$natural", -1));
        System.out.println("natural ordering---------------");
        try {
            while (car.hasNext()) {
                System.out.println(car.next());
            }
        } finally {
            car.close();
        }
 
    }
 
    public static void createIndex(String on, int type) throws UnknownHostException {
 
        MongoClient m1 = new MongoClient("localhost");
        DB d1 = m1.getDB("mymgdb");
 
        DBCollection coll = d1.getCollection("Car");
        coll.createIndex(new BasicDBObject(on, type));
 
        System.out.println("created index---------------------");
        List<DBObject> list = coll.getIndexInfo();
        for (DBObject o : list) {
            System.out.println(o);
        }
    }
 
    public static void main(String[] args) throws UnknownHostException {
 
        // invoking methods for performing sorting
        //sortAscending();
        //sortDescending();
        //sortDescendingAscending();
       // sortlimit();
       // sortProjectionfields();
       // sortnaturalordering();
        createIndex("name", 1);
    }

}
