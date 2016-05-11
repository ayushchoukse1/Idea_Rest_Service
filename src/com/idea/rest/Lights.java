package com.idea.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

@Path("/lights")
public class Lights {

	@GET
	@Path("{lightName}")
	@Produces("application/json")
	public String getLightData(@PathParam("lightName") String lightName){
		DBCollection collection = MongoDBConnection.getDBConnection().getCollection("lights");
		List<String> list = new ArrayList<String>() ;
		
		return "TEST";
	}
}
