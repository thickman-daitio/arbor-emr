package com.daitio.arboremr;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

public class MongoConnector {
	
	private MongoClient mongo;
	public final static String MONGO_DB_NAME = "db_daitio-arbor-emr"; 
	public final static String MONGO_FIELD_ID = "_id";
	
	public MongoConnector() throws UnknownHostException {
		
		List<ServerAddress> addrs = new ArrayList<ServerAddress>();
		addrs.add(new ServerAddress("localhost", 27017));
		
		MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
		builder.connectionsPerHost(5);
		builder.threadsAllowedToBlockForConnectionMultiplier(10);
		builder.writeConcern(WriteConcern.MAJORITY);
		builder.readPreference(ReadPreference.secondaryPreferred());
		
		MongoClientOptions options = builder.build();
				
		mongo = new MongoClient(addrs, options);
	}
	
	public void addUser() {
		User user = new User();
		user.setFirstName("Tom");
		user.setLastName("Hickman");
		user.setUsername("thickman");
		user.setPassword("password");
		
		MongoUserDAO mDAO = new MongoUserDAO(mongo);
		mDAO.createUser(user);
	}
}