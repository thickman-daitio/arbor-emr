package com.daitio.arboremr.encounter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.daitio.arboremr.MongoConnector;
import com.daitio.arboremr.patient.Patient;
import com.daitio.arboremr.patient.PatientListObject;
import com.daitio.arboremr.patient.Prescription;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class Encounter implements PatientListObject {

	public final static String FIELD_BLOOD_PRESSURE_SYSTOLIC = "bloodPressureSystolic";
	public final static String FIELD_BLOOD_PRESSURE_DIASTOLIC = "bloodPressureDiastolic";
	public final static String FIELD_HEART_RATE = "heartRate";
	public final static String FIELD_TEMP_FAHR = "tempFahr";
	public final static String FIELD_RESPIRATORY_RATE = "respiratoryRate";
	public final static String FIELD_DATE_TIME = "dateTime";
	public final static String FIELD_SYMPTOMS = "symptoms";

	private ObjectId id;
	private Date dateTime;
	private int bloodPressureSystolic;
	private int bloodPressureDiastolic;
	private int heartRate;
	private double tempFahr;
	private int respiratoryRate;
	private List<String> symptoms;

	public Encounter() {

	}

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

	public double getTempFahr() {
		return tempFahr;
	}

	public void setTempFahr(double tempFahr) {
		this.tempFahr = tempFahr;
	}

	public int getRespiratoryRate() {
		return respiratoryRate;
	}

	public void setRespiratoryRate(int respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}

	public List<String> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<String> symptoms) {
		this.symptoms = symptoms;
	}

	public static Encounter getDummyEncounter() {
		Encounter e = new Encounter();

		e.setDateTime(new Date());
		e.setBloodPressureSystolic(120);
		e.setBloodPressureDiastolic(80);
		e.setHeartRate(60);
		e.setTempFahr(98.6f);
		e.setRespiratoryRate(13);

		List<String> s = new ArrayList<String>();
		s.add("Frequent urination");
		s.add("Acid reflux");
		e.setSymptoms(s);

		return e;
	}

	@Override
	public DBObject toDBObject() {
		return toDBObject(this);
	}

	@Override
	public DBObject toDBObject(Object o) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder
				.start()
				.append(FIELD_DATE_TIME, ((Encounter) o).getDateTime())
				.append(FIELD_BLOOD_PRESSURE_SYSTOLIC, ((Encounter) o).getBloodPressureSystolic())
				.append(FIELD_BLOOD_PRESSURE_DIASTOLIC, ((Encounter) o).getBloodPressureDiastolic())
				.append(FIELD_HEART_RATE, ((Encounter) o).getHeartRate())
				.append(FIELD_TEMP_FAHR, ((Encounter) o).getTempFahr())
				.append(FIELD_RESPIRATORY_RATE, ((Encounter) o).getRespiratoryRate())
				.append(FIELD_SYMPTOMS, ((Encounter) o).getSymptoms());

		if (((Encounter) o).getId() != null)
			builder = builder.append(MongoConnector.MONGO_FIELD_ID,
					((Encounter) o).getId());

		return builder.get();
	}

	@Override
	public <T> BasicDBList toBasicDBList(List<T> list) {
		BasicDBList dbList = new BasicDBList();

		for (int i = 0; i < list.size(); i++) {
			dbList.add(((Encounter) list.get(i)).toDBObject());
		}

		return dbList;
	}

	@Override
	public Encounter toObject(DBObject doc) {
		Encounter r = new Encounter();
		
		r.setBloodPressureSystolic((int) doc.get(FIELD_BLOOD_PRESSURE_SYSTOLIC));
		r.setBloodPressureDiastolic((int) doc.get(FIELD_BLOOD_PRESSURE_DIASTOLIC));
		r.setHeartRate((int) doc.get(FIELD_HEART_RATE));
		//r.setTempFahr((double) doc.get(FIELD_TEMP_FAHR));
		r.setRespiratoryRate((int) doc.get(FIELD_RESPIRATORY_RATE));
		r.setDateTime((Date) doc.get(FIELD_DATE_TIME));

		return r;
	}

	public static List<Encounter> toEncounterList(DBObject db) {
		List<Encounter> encounters = new ArrayList<Encounter>();

		try {
			JSONArray arr = new JSONArray(db.toString());

			for (int i = 0; i < arr.length(); i++) {
				JSONObject obj = arr.getJSONObject(i);
				
				Encounter e = new Encounter();
				e.setBloodPressureSystolic(obj.getInt(FIELD_BLOOD_PRESSURE_SYSTOLIC));
				e.setBloodPressureDiastolic(obj.getInt(FIELD_BLOOD_PRESSURE_DIASTOLIC));
				e.setHeartRate(obj.getInt(FIELD_HEART_RATE));
				e.setRespiratoryRate(obj.getInt(FIELD_RESPIRATORY_RATE));
				
				encounters.add(e);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
			
		return encounters;
	}
}
