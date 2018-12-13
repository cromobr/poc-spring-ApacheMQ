/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Cromo
 */
public class MongoBD {

	public static void insert(String mensagem) throws UnknownHostException {


		MongoClient mongoClient = new MongoClient("172.18.0.2", 27017);
		DB db = mongoClient.getDB("POC");

		DBCollection table = db.getCollection("MENSAGEM");

		BasicDBObject document = new BasicDBObject();
		document.put("dados", mensagem);
		table.insert(document);

		Set<String> colls = db.getCollectionNames();
		for (String s : colls) {
			System.out.println(s);
		}

		DBObject pessoa = table.findOne();
		System.out.println(pessoa);
	}
}
