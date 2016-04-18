package com.iovov.mgdb;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iovov.mgdb.dao.IPersonDAO;
import com.iovov.mgdb.pojo.Person;
import com.mongodb.WriteResult;

public class SpringMongoDBXMLMain {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("mongo.xml");
        IPersonDAO personDAO = ctx.getBean("personDAO", IPersonDAO.class);
        Person p = new Person(null, "PankajKr", "Bangalore, India");
        //create
        personDAO.create(p);
        System.out.println("Generated ID="+p.getId());
        //read
        Person p1 = personDAO.readById(p.getId());
        System.out.println("Retrieved Person="+p1);
        //update
        p1.setName("David");p1.setAddress("SFO, USA");
        personDAO.update(p1);
        
        Person temp = personDAO.readById(p1.getId());
        System.out.println("Retrieved Person after update="+temp);
        
        //delete
        WriteResult result = personDAO.deleteById(p1.getId());
        System.err.println("Number of records deleted="+result.toString());
        ctx.close();
	}
}
