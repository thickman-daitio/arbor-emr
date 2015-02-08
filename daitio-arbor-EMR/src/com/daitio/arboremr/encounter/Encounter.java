package com.daitio.arboremr.encounter;

import java.util.List;

import org.bson.types.ObjectId;
import java.util.Date;
import com.daitio.arboremr.MongoConnector;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class Encounter {

	private ObjectId id;
	private Date dateTime; 
	private int bloodPressureSystolic;
	private int bloodPressureDiastolic;
	private int heartRate;
	private float tempFahr;
	private int respiratoryRate;
	//private List<Symptom> symptoms;

	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public int getBloodPressureSystolic() {
		return bloodPressureSystolic;
	}

	public void setBloodPressureSystolic(int bloodPressureSystolic) {
		this.bloodPressureSystolic = bloodPressureSystolic;
	}

	public int getBloodPressureDiastolic() {
		return bloodPressureDiastolic;
	}

	public void setBloodPressureDiastolic(int bloodPressureDiastolic) {
		this.bloodPressureDiastolic = bloodPressureDiastolic;
	}

	public int getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

	public float getTempFahr() {
		return tempFahr;
	}

	public void setTempFahr(float tempFahr) {
		this.tempFahr = tempFahr;
	}

	public int getRespiratoryRate() {
		return respiratoryRate;
	}

	public void setRespiratoryRate(int respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}

//	public List<Symptom> getSymptoms() {
//		return symptoms;
//	}
//
//	public void setSymptoms(List<Symptom> symptoms) {
//		this.symptoms = symptoms;
//	}
	public static DBObject toDBObject(Encounter r) {

		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append(MongoEncounterDAO.FIELD_BLOOD_PRESSURE_SYSTOLIC, r.getBloodPressureDiastolic())
				.append(MongoEncounterDAO.FIELD_BLOOD_PRESSURE_DIASTOLIC, r.getBloodPressureDiastolic())
				.append(MongoEncounterDAO.FIELD_HEART_RATE, r.getHeartRate())
				.append(MongoEncounterDAO.FIELD_TEMP_FAHR, r.getTempFahr())
				.append(MongoEncounterDAO.FIELD_RESPIRATORY_RATE, r.getRespiratoryRate());

		if (r.getId() != null)
			builder = builder.append(MongoConnector.MONGO_FIELD_ID, r.getId());

		return builder.get();
	}

	public static Encounter toEncounter(DBObject doc) {
		Encounter r = new Encounter();
		r.setBloodPressureSystolic((int) doc.get(MongoEncounterDAO.FIELD_BLOOD_PRESSURE_SYSTOLIC));
		r.setBloodPressureDiastolic((int) doc.get(MongoEncounterDAO.FIELD_BLOOD_PRESSURE_DIASTOLIC));
		r.setHeartRate((int) doc.get(MongoEncounterDAO.FIELD_HEART_RATE));
		r.setTempFahr((int) doc.get(MongoEncounterDAO.FIELD_TEMP_FAHR));
		r.setTempFahr((int) doc.get(MongoEncounterDAO.FIELD_RESPIRATORY_RATE));
		r.setId((ObjectId) doc.get(MongoConnector.MONGO_FIELD_ID));
		return r;
	}
	
}
