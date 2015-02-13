package com.daitio.arboremr.patient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.daitio.arboremr.MongoConnector;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class Prescription implements PatientListObject {
	
	public static final String FIELD_PRESCRIPTION_NAME = "prescriptionName";
	public static final String FIELD_MASS = "mass";
	public static final String FIELD_AMT_DAILY_DOSES = "amountOfDailyDoses";
	public static final String FIELD_AMT_REFILLS = "amountOfRefills";
	public static final String FIELD_INSTRUCTIONS = "instructions";
	public static final String FIELD_MANUFACTURER = "manufacturer";
	public static final String FIELD_DESCRIPTION = "description";

	private ObjectId id;
	private String name;
	private int mass; // mg
	private int amountOfDailyDoses;
	private int amountOfRefills;
	private String instructions;
	private String manufacturer;
	private String description;

	public Prescription() {

	}

	public Prescription(ObjectId id, String name, int mass,
			int amountOfDailyDoses, int amountOfRefills, String instructions,
			String manufacturer, String description) {
		this.id = id;
		this.name = name;
		this.mass = mass;
		this.amountOfDailyDoses = amountOfDailyDoses;
		this.amountOfRefills = amountOfRefills;
		this.instructions = instructions;
		this.manufacturer = manufacturer;
		this.description = description;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMass() {
		return mass;
	}

	public void setMass(int mass) {
		this.mass = mass;
	}

	public int getAmountOfDailyDoses() {
		return amountOfDailyDoses;
	}

	public void setAmountOfDailyDoses(int amountOfDailyDoses) {
		this.amountOfDailyDoses = amountOfDailyDoses;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmountOfRefills() {
		return amountOfRefills;
	}

	public void setAmountOfRefills(int amountOfRefills) {
		this.amountOfRefills = amountOfRefills;
	}

	public static Prescription getDummyPrescription() {
		return new Prescription(
				null,
				"Ibuprofin",
				800,
				1,
				3,
				"Take by mouth once daily as recommended by doctor.",
				"MedCorp",
				"A white, oblong pill with 800 inscribed on the back and a notch down the center");
	}

	@Override
	public DBObject toDBObject() {
		return toDBObject(this);
	}
	
	@Override
	public DBObject toDBObject(Object o) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append(FIELD_PRESCRIPTION_NAME, ((Prescription) o).getName())
				.append(FIELD_MASS, ((Prescription) o).getMass())
				.append(FIELD_AMT_DAILY_DOSES, ((Prescription) o).getAmountOfDailyDoses())
				.append(FIELD_AMT_REFILLS, ((Prescription) o).getAmountOfRefills())
				.append(FIELD_INSTRUCTIONS, ((Prescription) o).getInstructions())
				.append(FIELD_MANUFACTURER, ((Prescription) o).getManufacturer())
				.append(FIELD_DESCRIPTION, ((Prescription) o).getDescription());

		if (((Prescription) o).getId() != null)
			builder = builder.append(MongoConnector.MONGO_FIELD_ID,
					((Prescription) o).getId());

		return builder.get();
	}
	
	@Override
	public <T> BasicDBList toBasicDBList(List<T> list) {
		BasicDBList dbList = new BasicDBList();
		
		for (int i = 0; i < list.size(); i++) {
			dbList.add(((Prescription) list.get(i)).toDBObject());
		}
		
		return dbList;
	}

	@Override
	public Object toObject(DBObject doc) {
		Prescription p = new Prescription();
		
		p.setName((String) doc.get(FIELD_PRESCRIPTION_NAME));
		p.setMass((int) doc.get(FIELD_MASS));
		p.setAmountOfDailyDoses((int) doc.get(FIELD_AMT_DAILY_DOSES));
		p.setAmountOfRefills((int) doc.get(FIELD_AMT_REFILLS));
		p.setInstructions((String) doc.get(FIELD_INSTRUCTIONS));
		p.setManufacturer((String) doc.get(FIELD_MANUFACTURER));
		p.setDescription((String) doc.get(FIELD_DESCRIPTION));
		
		return p;
	}

	@Override
	public String toString() {
		return "Prescription [id=" + id + ", name=" + name + ", mass=" + mass
				+ ", amountOfDailyDoses=" + amountOfDailyDoses
				+ ", amountOfRefills=" + amountOfRefills + ", instructions="
				+ instructions + ", manufacturer=" + manufacturer
				+ ", description=" + description + "]";
	}

	public static List<Prescription> toPrescriptionList(DBObject db) {
		List<Prescription> rxList = new ArrayList<Prescription>();

		try {
			JSONArray arr = new JSONArray(db.toString());

			for (int i = 0; i < arr.length(); i++) {
				JSONObject obj = arr.getJSONObject(i);
				
				Prescription rx = new Prescription();
				rx.setName(obj.getString(FIELD_PRESCRIPTION_NAME));
				rx.setMass(obj.getInt(FIELD_MASS));
				rx.setAmountOfDailyDoses(obj.getInt(FIELD_AMT_DAILY_DOSES));
				rx.setAmountOfRefills(obj.getInt(FIELD_AMT_REFILLS));
				rx.setInstructions(obj.getString(FIELD_INSTRUCTIONS));
				rx.setManufacturer(obj.getString(FIELD_MANUFACTURER));
				rx.setDescription(obj.getString(FIELD_DESCRIPTION));
							
				rxList.add(rx);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
			
		return rxList;
	}
}
