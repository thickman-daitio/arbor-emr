package com.daitio.arboremr.encounter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.daitio.arboremr.MongoConnector;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoEncounterDAO {
	
	public final static String FIELD_BLOOD_PRESSURE_SYSTOLIC = "bloodPressureSystolic";
	public final static String FIELD_BLOOD_PRESSURE_DIASTOLIC = "bloodPressureDiastolic";
	public final static String FIELD_HEART_RATE = "heartRate";
	public final static String FIELD_TEMP_FAHR = "tempFahr";
	public final static String FIELD_RESPIRATORY_RATE = "respiratoryRate";
//	public final static Date FIELD_DATE_TIME = 
//public List<Symptom> SYMPTOMS = List<Symptom> symptoms;

	private DBCollection col;
	
	private final String MONGO_COLL_RECORDS = "record";
	
	public MongoEncounterDAO(MongoClient mongo) {
		this.col = mongo.getDB(MongoConnector.MONGO_DB_NAME).getCollection(MONGO_COLL_RECORDS);
	}

	public Encounter createEncounter(Encounter r) {
		DBObject doc = Encounter.toDBObject(r);
		this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get(MongoConnector.MONGO_FIELD_ID);
		r.setId(id);
		return r;
	}

	public void updateEncounter(Encounter r) {
		DBObject query = BasicDBObjectBuilder.start().append(MongoConnector.MONGO_FIELD_ID, r.getId())
				.get();
		this.col.update(query, Encounter.toDBObject(r));
	}

	public void deleteEncounter(Encounter r) {
		DBObject query = BasicDBObjectBuilder.start().append(MongoConnector.MONGO_FIELD_ID, r.getId())
				.get();
		this.col.remove(query);
	}

	public Encounter getEncounterById(Encounter r) {
		DBObject query = BasicDBObjectBuilder.start().append(MongoConnector.MONGO_FIELD_ID, r.getId())
				.get();
		DBObject data = this.col.findOne(query);
		return Encounter.toEncounter(data);
	}
	
	public List<Encounter> readAllEncounters() {
		List<Encounter> data = new ArrayList<Encounter>();
		DBCursor cursor = col.find();
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			Encounter r = Encounter.toEncounter(doc);
			data.add(r);
		}
		return data;
	}
}