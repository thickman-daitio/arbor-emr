package com.daitio.arboremr.encounter;

import org.bson.types.ObjectId;

import com.daitio.arboremr.MongoConnector;
import com.daitio.arboremr.MongoUserDAO;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class EncounterConverter {
/*
	public static DBObject toDBObject(Encounter r) {

		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append(MongoEncounterDAO.FIELD_BLOODPRESSURE_S, r.getBloodPressureSystolic())
				.append(MongoEncounterDAO.FIELD_BLOODPRESSURE_D, r.getBloodPressureDiastolic())
				.append(MongoEncounterDAO.FIELD_RESPIRATORYRATE, r.getRespiratoryRate());

		if (r.getId() != null)
			builder = builder.append(MongoConnector.MONGO_FIELD_ID, r.getId());

		return builder.get();
	}

	public static Encounter toEncounter(DBObject doc) {
		Encounter r = new Encounter();
		//r.setComments((String) doc.get(MongoEncounterDAO.FIELD_COMMENTS));
		r.setId((ObjectId) doc.get(MongoConnector.MONGO_FIELD_ID));
		return r;
	}
*/
}