package com.daitio.arboremr.user;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.daitio.arboremr.MongoConnector;
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
		List<User> compare = getAllUsers();
		for (int i = 0; i < compare.size(); i++) {
			if (compare.get(i).getUsername().equals(u.getUsername()))
				return new User();
		}
		
		DBObject doc = User.toDBObject(u);
		this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get(MongoConnector.MONGO_FIELD_ID);
		u.setId(id);
		return u;
	}

	public void updateUser(User u) {
		DBObject query = BasicDBObjectBuilder.start().append(MongoConnector.MONGO_FIELD_ID, u.getId())
				.get();
		this.col.update(query, User.toDBObject(u));
	}

	public void deleteUser(ObjectId id) {
		DBObject query = BasicDBObjectBuilder.start().append(MongoConnector.MONGO_FIELD_ID, id)
				.get();
		this.col.remove(query);
	}

	public User getUserById(ObjectId id) {
		DBObject query = BasicDBObjectBuilder.start().append(MongoConnector.MONGO_FIELD_ID, id)
				.get();
		DBObject data = this.col.findOne(query);
		return User.toUser(data);
	}
	
	public User getUserByUsername(String username) {
		DBObject query = BasicDBObjectBuilder.start().append(FIELD_USERNAME, username)
				.get();
		DBObject data = this.col.findOne(query);
		if (data != null)
			return User.toUser(data);
		return new User();
	}

	public List<User> getAllUsers() {
		List<User> data = new ArrayList<User>();
		DBCursor cursor = col.find();
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			User u = User.toUser(doc);
			data.add(u);
		}
		return data;
	}
}