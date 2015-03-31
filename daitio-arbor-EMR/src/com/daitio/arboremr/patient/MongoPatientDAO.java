package com.daitio.arboremr.patient;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.daitio.arboremr.MongoConnector;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoPatientDAO {

	private DBCollection col;

	private final String MONGO_COLL_PATIENTS = "patients";

	public MongoPatientDAO(MongoClient mongo) {
		this.col = mongo.getDB(MongoConnector.MONGO_DB_NAME).getCollection(
				MONGO_COLL_PATIENTS);
	}

	public Patient getPatient(ObjectId id) {
		DBObject query = BasicDBObjectBuilder.start()
				.append(MongoConnector.MONGO_FIELD_ID, id).get();
		DBObject data = this.col.findOne(query);

		return Patient.toPatient(data);
	}

	public Patient createPatient(Patient p) {
		DBObject doc = Patient.toDBObject(p);

		this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get(MongoConnector.MONGO_FIELD_ID);
		p.setId(id);

		return p;
	}

	public void deletePatient(ObjectId id) {
		DBObject query = BasicDBObjectBuilder.start()
				.append(MongoConnector.MONGO_FIELD_ID, id).get();
		System.out.println(query.toString());
		this.col.remove(query);
	}

	public void updatePatient(Patient p) {
		DBObject query = BasicDBObjectBuilder.start()
				.append(MongoConnector.MONGO_FIELD_ID, p.getId()).get();
		this.col.update(query, Patient.toDBObject(p));
	}

	public List<Patient> getAllPatients() {
		List<Patient> data = new ArrayList<Patient>();
		DBCursor cursor = col.find();
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			Patient u = Patient.toPatient(doc);
			data.add(u);
		}
		return data;
	}

	public void checkInWeight(ObjectId objectId, Weight w) {
		DBObject query = BasicDBObjectBuilder.start()
				.append(MongoConnector.MONGO_FIELD_ID, objectId).get();

		DBObject listItem = new BasicDBObject(Patient.FIELD_WEIGHT_LIST,
				new BasicDBObject(Weight.FIELD_DATE, w.getDate()).append(
						Weight.FIELD_WEIGHT, w.getWeight()));
		DBObject updateQuery = new BasicDBObject(MongoConnector.MONGO_PUSH, listItem);

		this.col.update(query, updateQuery);
	}
}
