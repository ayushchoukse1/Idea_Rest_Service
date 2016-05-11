package com.idea.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

@Path("/")
public class RestService {

	/*
	 * { "_id" : ObjectId("57328d90dc055cd40c04556e"), "name" : "Family E",
	 * "onTimeInPercent" : 0.08933373604791472, "onTimeUsageInWatts" : 0,
	 * "onTimeInHours" : "00:01:00", "wasteTimeInPercent" : 0.08933373604791472,
	 * "wasteTimeUsageInWatts" : 0, "wasteTimeInHours" : "00:01:00", "date" :
	 * ISODate("2016-05-11T01:42:31.924Z"), "timestamp" :
	 * NumberLong("1462930942980") }
	 */

	@GET
	@Path("lights/{lightName}")
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
	@Path("lights{lightName}/{key}")
	@Produces("application/json")
	public String getLightDataByLocationAndKey(@PathParam("lightName") String lightName,@PathParam("key") String key) {
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
	
	@GET
	@Path("temperature/{location}")
	@Produces("application/json")
	public String getTemperatureByLocation(@PathParam("location") String location) {
		DBCollection collection = MongoDBConnection.getDBConnection().getCollection("tempRecomms");
		DBCursor cursor = null;
		BasicDBObject query = new BasicDBObject();
		BasicDBObject retreivalObj = null;
		query.put("location", location);
		cursor = collection.find(query);
		String err = " { \"Error\": \"Location Not Found\" }";
		if (cursor.count() == 0) {
			return err;
		} else {
			retreivalObj = (BasicDBObject) cursor.next();
		}
		return retreivalObj.toString();
	}
	
	@GET
	@Path("temperature/{location}/{key}")
	@Produces("application/json")
	public String getTemperatureByLocationAndKey(@PathParam("location") String location,@PathParam("key") String key) {
		DBCollection collection = MongoDBConnection.getDBConnection().getCollection("tempRecomms");
		DBCursor cursor = null;
		BasicDBObject query = new BasicDBObject();
		BasicDBObject retreivalObj = null;
		query.put("location", location);
		cursor = collection.find(query);
		String err = " { \"Error\": \"Location Not Found\" }";
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

	
	
}
