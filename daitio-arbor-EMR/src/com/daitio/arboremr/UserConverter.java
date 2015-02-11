package com.daitio.arboremr;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class UserConverter {

	public static DBObject toDBObject(User u) {

		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append(MongoUserDAO.FIELD_USERNAME, u.getUsername())
				.append(MongoUserDAO.FIELD_PASSWORD, u.getPassword())
				.append(MongoUserDAO.FIELD_FIRSTNAME, u.getFirstName())
				.append(MongoUserDAO.FIELD_LASTNAME, u.getLastName());

		if (u.getId() != null)
			builder = builder.append(MongoConnector.MONGO_FIELD_ID, u.getId());

		return builder.get();
	}

	public static User toUser(DBObject doc) {
		User u = new User();
		u.setUsername((String) doc.get(MongoUserDAO.FIELD_USERNAME));
		u.setPassword((String) doc.get(MongoUserDAO.FIELD_PASSWORD));
		u.setFirstName((String) doc.get(MongoUserDAO.FIELD_FIRSTNAME));
		u.setLastName((String) doc.get(MongoUserDAO.FIELD_LASTNAME));
		u.setId((ObjectId) doc.get(MongoConnector.MONGO_FIELD_ID));
		return u;
	}
}