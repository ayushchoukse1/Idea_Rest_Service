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

	@GET
	@Path("{lightName}")
	@Produces("application/json")
	public String getLightData(@PathParam("lightName") String lightName) {
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
}
