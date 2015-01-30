package com.daitio.arboremr;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoEncounterDAO {
	
	public final static String FIELD_COMMENTS = "comments";

	private DBCollection col;
	
	private final String MONGO_COLL_RECORDS = "record";
	
	public MongoEncounterDAO(MongoClient mongo) {
		this.col = mongo.getDB(MongoConnector.MONGO_DB_NAME).getCollection(MONGO_COLL_RECORDS);
	}

	public Encounter createRecord(Encounter r) {
		DBObject doc = RecordConverter.toDBObject(r);
		this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get(MongoConnector.MONGO_FIELD_ID);
		r.setId(id);
		return r;
	}

	public void updateRecord(Encounter r) {
		DBObject query = BasicDBObjectBuilder.start().append(MongoConnector.MONGO_FIELD_ID, r.getId())
				.get();
		this.col.update(query, RecordConverter.toDBObject(r));
	}

	public void deleteRecord(Encounter r) {
		DBObject query = BasicDBObjectBuilder.start().append(MongoConnector.MONGO_FIELD_ID, r.getId())
				.get();
		this.col.remove(query);
	}

	public User getRecordById(Encounter r) {
		DBObject query = BasicDBObjectBuilder.start().append(MongoConnector.MONGO_FIELD_ID, r.getId())
				.get();
		DBObject data = this.col.findOne(query);
		return UserConverter.toUser(data);
	}
	
	public List<User> readAllRecords() {
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