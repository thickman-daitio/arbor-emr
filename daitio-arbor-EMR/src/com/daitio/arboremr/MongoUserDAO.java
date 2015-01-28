package com.daitio.arboremr;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoUserDAO {
	
	public final static String FIELD_USERNAME = "username";
	public final static String FIELD_PASSWORD = "password";
	public final static String FIELD_FIRSTNAME = "firstName";
	public final static String FIELD_LASTNAME = "lastName";	

	private DBCollection col;
	private final String MONGO_COLL_USERS = "users";
	
	public MongoUserDAO(MongoClient mongo) {
		this.col = mongo.getDB(MongoConnector.MONGO_DB_NAME).getCollection(MONGO_COLL_USERS);
	}

	public User createUser(User u) {
		DBObject doc = UserConverter.toDBObject(u);
		this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get(MongoConnector.MONGO_FIELD_ID);
		u.setId(id);
		return u;
	}

	public void updateUser(User u) {
		DBObject query = BasicDBObjectBuilder.start().append(MongoConnector.MONGO_FIELD_ID, u.getId())
				.get();
		this.col.update(query, UserConverter.toDBObject(u));
	}

	public void deleteUser(User u) {
		DBObject query = BasicDBObjectBuilder.start().append(MongoConnector.MONGO_FIELD_ID, u.getId())
				.get();
		this.col.remove(query);
	}

	public User readUser(User u) {
		DBObject query = BasicDBObjectBuilder.start().append(MongoConnector.MONGO_FIELD_ID, u.getId())
				.get();
		DBObject data = this.col.findOne(query);
		return UserConverter.toUser(data);
	}

	public List<User> readAllUsers() {
		List<User> data = new ArrayList<User>();
		DBCursor cursor = col.find();
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			User u = UserConverter.toUser(doc);
			data.add(u);
		}
		return data;
	}
}