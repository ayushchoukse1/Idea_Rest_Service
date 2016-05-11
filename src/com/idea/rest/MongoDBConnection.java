package com.idea.rest;

import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDBConnection {
	public static MongoDBConnection databaseConnection;
	private static DB dbConn;

	private static void initializeDBConnection() {
		try {
			MongoClient mongoClient = new MongoClient("localhost",27017);
			DB db = mongoClient.getDB("ideadb");
			boolean auth = db.authenticate("abc", "123".toCharArray());
			dbConn = mongoClient.getDB("ideadb");
		} catch (Exception e) {
			System.out.println("Error in get database connection: " + e.getMessage());
		}
	}
	
	public static DB getDBConnection(){
		if(dbConn == null){
			initializeDBConnection();
		}
		return dbConn;
	}

	public static DBCollection getCollection(String collectionName) {
		DBCollection dbCollection = null;
		try {
			
			boolean collectionExists = getDBConnection().collectionExists(collectionName);
			if (collectionExists == false) {
				getDBConnection().createCollection(collectionName, null);
			}
			dbCollection = getDBConnection().getCollection(collectionName);
		} catch (Exception e) {
			System.out.println("Error in get database connection: " + e.getMessage());
		}
		return dbCollection;
	}
	
	public static void main(String [] args){
		

		try {
			MongoClient mongoClient = new MongoClient("localhost",27017);
			DB db1 = mongoClient.getDB("ideadb");
			boolean auth = db1.authenticate("abc", "123".toCharArray());
			//boolean collectionExists = db.collectionExists(collectionName);
			DBCollection coll = db1.getCollection("newlights");
			DBObject object = coll.findOne();
			System.out.println(object.toString());
		} catch (Exception e) {
			System.out.println("Error in get database connection: " + e.getMessage());
		}
	}
}
