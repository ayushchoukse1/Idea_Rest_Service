package com.idea.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

@Path("/lights")
public class Lights {

	/*
	 * { "_id" : ObjectId("57328d90dc055cd40c04556e"), "name" : "Family E",
	 * "onTimeInPercent" : 0.08933373604791472, "onTimeUsageInWatts" : 0,
	 * "onTimeInHours" : "00:01:00", "wasteTimeInPercent" : 0.08933373604791472,
	 * "wasteTimeUsageInWatts" : 0, "wasteTimeInHours" : "00:01:00", "date" :
	 * ISODate("2016-05-11T01:42:31.924Z"), "timestamp" :
	 * NumberLong("1462930942980") }
	 */

	@GET
	@Path("{lightName}")
	@Produces("application/json")
	public String getLightAsJsonStringByName(@PathParam("lightName") String lightName) {
		DBCollection collection = MongoDBConnection.getDBConnection().getCollection("newlights");
		DBCursor cursor = null;
		BasicDBObject query = new BasicDBObject();
		BasicDBObject retreivalObj = null;
		query.put("name", lightName);
		cursor = collection.find(query);
		String err = " { \"Error\": \"Light Not Found\" }";
		if (cursor.count() == 0) {
			return err;
		} else {
			retreivalObj = (BasicDBObject) cursor.next();
		}
		return retreivalObj.toString();
	}

	@GET
	@Path("{lightName}/{key}")
	@Produces("application/json")
	public String getLightOnTimeInPercent(@PathParam("lightName") String lightName,@PathParam("key") String key) {
		DBCollection collection = MongoDBConnection.getDBConnection().getCollection("newlights");
		DBCursor cursor = null;
		BasicDBObject query = new BasicDBObject();
		BasicDBObject retreivalObj = null;
		query.put("name", lightName);
		cursor = collection.find(query);
		String err = " { \"Error\": \"Light Not Found\" }";
		if (cursor.count() == 0) {
			return err;
		} else {
			retreivalObj = (BasicDBObject) cursor.next();
		}

		if (retreivalObj.containsKey(key)) {
			return retreivalObj.getString(key);
		} else {
			return err;
		}
	}
	
//	@GET
//	@Path("{lightName}/onTimeUsageInWatts")
//	@Produces("application/json")
//	public String getLightOnTimeUsageInWatts(@PathParam("lightName") String lightName) {
//		DBCollection collection = MongoDBConnection.getDBConnection().getCollection("newlights");
//		DBCursor cursor = null;
//		BasicDBObject query = new BasicDBObject();
//		BasicDBObject retreivalObj = null;
//		query.put("name", lightName);
//		cursor = collection.find(query);
//		String err = " { \"Error\": \"Light Not Found\" }";
//		if (cursor.count() == 0) {
//			return err;
//		} else {
//			retreivalObj = (BasicDBObject) cursor.next();
//		}
//		return retreivalObj.getString("onTimeUsageInWatts");
//	}
//	
//	
//	@GET
//	@Path("{lightName}/onTimeInHours")
//	@Produces("application/json")
//	public String getLightOnTimeInHours(@PathParam("lightName") String lightName) {
//		DBCollection collection = MongoDBConnection.getDBConnection().getCollection("newlights");
//		DBCursor cursor = null;
//		BasicDBObject query = new BasicDBObject();
//		BasicDBObject retreivalObj = null;
//		query.put("name", lightName);
//		cursor = collection.find(query);
//		String err = " { \"Error\": \"Light Not Found\" }";
//		if (cursor.count() == 0) {
//			return err;
//		} else {
//			retreivalObj = (BasicDBObject) cursor.next();
//		}
//		return retreivalObj.getString("onTimeInHours");
//	}
//	
//	@GET
//	@Path("{lightName}/wasteTimeInPercent")
//	@Produces("application/json")
//	public String getLightWasteTimeInPercent(@PathParam("lightName") String lightName) {
//		DBCollection collection = MongoDBConnection.getDBConnection().getCollection("newlights");
//		DBCursor cursor = null;
//		BasicDBObject query = new BasicDBObject();
//		BasicDBObject retreivalObj = null;
//		query.put("name", lightName);
//		cursor = collection.find(query);
//		String err = " { \"Error\": \"Light Not Found\" }";
//		if (cursor.count() == 0) {
//			return err;
//		} else {
//			retreivalObj = (BasicDBObject) cursor.next();
//		}
//		return retreivalObj.getString("wasteTimeInPercent");
//	}
//	
//	@GET
//	@Path("{lightName}/wasteTimeUsageInWatts")
//	@Produces("application/json")
//	public String getLightWasteTimeUsageInWatts(@PathParam("lightName") String lightName) {
//		DBCollection collection = MongoDBConnection.getDBConnection().getCollection("newlights");
//		DBCursor cursor = null;
//		BasicDBObject query = new BasicDBObject();
//		BasicDBObject retreivalObj = null;
//		query.put("name", lightName);
//		cursor = collection.find(query);
//		String err = " { \"Error\": \"Light Not Found\" }";
//		if (cursor.count() == 0) {
//			return err;
//		} else {
//			retreivalObj = (BasicDBObject) cursor.next();
//		}
//		return retreivalObj.getString("wasteTimeUsageInWatts");
//	}
//	
}
