package com.daitio.arboremr.patient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.scribe.model.Token;

import com.daitio.arboremr.MongoConnector;
import com.daitio.arboremr.encounter.Encounter;
import com.daitio.arboremr.user.User;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class Patient extends User {

	/*
	 * Not really working the way I want it to.
	 * 
	 * public enum Status { STATUS_GOOD("GOOD"), STATUS_NEUTRAL("NEUTRAL"),
	 * STATUS_BAD("BAD"), STATUS_NONE("");
	 * 
	 * private final String code;
	 * 
	 * Status(String code) { this.code = code; }
	 * 
	 * public String getStatusCode() { return this.code; }
	 * 
	 * public static Status convertStatus(String status) { if
	 * (status.equals("GOOD")) return STATUS_GOOD; else if
	 * (status.equals("BAD")) return STATUS_BAD; else if
	 * (status.equals("NEUTRAL")) return STATUS_NEUTRAL; return STATUS_NONE; } }
	 */

	public final static String FIELD_FIRST_NAME = "firstName";
	public final static String FIELD_MIDDLE_NAME = "middleName";
	public final static String FIELD_LAST_NAME = "lastName";
	public final static String FIELD_GENDER = "gender";
	public final static String FIELD_DATE_OF_BIRTH = "dateOfBirth";
	public final static String FIELD_ADDRESS_1 = "address1";
	public final static String FIELD_ADDRESS_2 = "address2";
	public final static String FIELD_CITY = "city";
	public final static String FIELD_STATE = "state";
	public final static String FIELD_ZIP = "zip";
	public final static String FIELD_PRIMARY_PHONE = "primaryPhone";
	public final static String FIELD_SECONDARY_PHONE = "secondaryPhone";
	public final static String FIELD_EMAIL = "email";
	public final static String FIELD_HEIGHT = "height";
	public final static String FIELD_INSURANCE_TYPE = "insuranceType";
	public final static String FIELD_EMERGENCY_CONTACT = "emergencyContact";
	public final static String FIELD_WEIGHT_LIST = "weightList";
	public final static String FIELD_ENCOUNTERS = "encounters";
	public final static String FIELD_FAMILY_CONDITIONS = "familyConditions";
	public final static String FIELD_ACTIVITY = "activity";
	public final static String FIELD_PRESCRIPTIONS = "prescriptions";
	public final static String FIELD_STATUS = "status";
	public final static String FIELD_TOKEN = "token";
	public final static String FIELD_SECRET = "secret";

	private ObjectId id;
	private String middleName;
	private String gender;
	private Date dateOfBirth;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String primaryPhone;
	private String secondaryPhone;
	private String email;
	private double height; // centimetres
	private String insuranceType;
	private EmergencyContact emergencyContact;
	private List<Weight> weightList;
	private List<Encounter> encounters;
	private List<String> familyConditions;
	private List<Activity> activity;
	private List<Prescription> prescriptions;
	private String weightListJSON;
	private String status; // Formerly type Status
	private String token;
	private String secret;

	public static final String STATUS_GOOD = "GOOD";
	public static final String STATUS_NEUTRAL = "NEUTRAL";
	public static final String STATUS_BAD = "BAD";

	public static final String GENDER_MALE = "MALE";
	public static final String GENDER_FEMALE = "FEMALE";

	private static final int BMI_UNDERWEIGHT = 0;
	private static final int BMI_NORMAL = 1;
	private static final int BMI_OVERWEIGHT = 2;
	private static final int BMI_OBESE = 3;

	public Patient() {
		this.firstName = "Test";
		this.lastName = "Patient";
		// this.status = Status.STATUS_NEUTRAL;
		this.status = STATUS_NEUTRAL;
		this.role = User.ROLE_PATIENT;
		this.emergencyContact = new EmergencyContact();
		this.weightList = new ArrayList<Weight>();
		this.prescriptions = new ArrayList<Prescription>();
		this.encounters = new ArrayList<Encounter>();
	}

	public static DBObject toDBObject(Patient p) {

		BasicDBObjectBuilder builder = BasicDBObjectBuilder
				.start()
				.append(FIELD_FIRST_NAME, p.getFirstName())
				.append(FIELD_MIDDLE_NAME, p.getMiddleName())
				.append(FIELD_LAST_NAME, p.getLastName())
				.append(FIELD_DATE_OF_BIRTH, p.getDateOfBirth())
				.append(FIELD_ADDRESS_1, p.getAddress1())
				.append(FIELD_ADDRESS_2, p.getAddress2())
				.append(FIELD_CITY, p.getCity())
				.append(FIELD_STATE, p.getState())
				.append(FIELD_ZIP, p.getZip())
				.append(FIELD_PRIMARY_PHONE, p.getPrimaryPhone())
				.append(FIELD_SECONDARY_PHONE, p.getSecondaryPhone())
				.append(FIELD_EMAIL, p.getEmail())
				.append(FIELD_HEIGHT, p.getHeight())
				.append(FIELD_INSURANCE_TYPE, p.getInsuranceType())
				.append(FIELD_EMERGENCY_CONTACT,
						p.getEmergencyContact().toDBObject())
				.append(FIELD_WEIGHT_LIST,
						new Weight().toBasicDBList(p.getWeightList()))
				.append(FIELD_PRESCRIPTIONS,
						new Prescription().toBasicDBList(p.getPrescriptions()))
				.append(FIELD_ENCOUNTERS,
						new Encounter().toBasicDBList(p.getEncounters()))
				.append(FIELD_STATUS, p.getStatus())
				.append(FIELD_TOKEN, p.getToken())
				.append(FIELD_SECRET, p.getSecret());

		System.out.println(p.getToken());

		if (p.getId() != null)
			builder = builder.append(MongoConnector.MONGO_FIELD_ID, p.getId());

		return builder.get();
	}

	public static Patient toPatient(DBObject doc) {
		Patient p = new Patient();

		p.setId((ObjectId) doc.get(MongoConnector.MONGO_FIELD_ID));
		p.setFirstName((String) doc.get(FIELD_FIRST_NAME));
		p.setMiddleName((String) doc.get(FIELD_MIDDLE_NAME));
		p.setLastName((String) doc.get(FIELD_LAST_NAME));
		p.setDateOfBirth((Date) doc.get(FIELD_DATE_OF_BIRTH));
		p.setAddress1((String) doc.get(FIELD_ADDRESS_1));
		p.setAddress2((String) doc.get(FIELD_ADDRESS_2));
		p.setCity((String) doc.get(FIELD_CITY));
		p.setState((String) doc.get(FIELD_STATE));
		p.setZip((String) doc.get(FIELD_ZIP));
		p.setPrimaryPhone((String) doc.get(FIELD_PRIMARY_PHONE));
		p.setSecondaryPhone((String) doc.get(FIELD_SECONDARY_PHONE));
		p.setEmail((String) doc.get(FIELD_EMAIL));
		p.setHeight((double) doc.get(FIELD_HEIGHT));
		p.setInsuranceType((String) doc.get(FIELD_INSURANCE_TYPE));
		p.setEmergencyContact(EmergencyContact
				.toEmergencyContact((DBObject) doc.get(FIELD_EMERGENCY_CONTACT)));
		p.setWeightList(Weight.toWeightList((DBObject) doc
				.get(FIELD_WEIGHT_LIST)));
		p.setPrescriptions(Prescription.toPrescriptionList((DBObject) doc
				.get(FIELD_PRESCRIPTIONS)));
		p.setEncounters(Encounter.toEncounterList((DBObject) doc
				.get(FIELD_ENCOUNTERS)));
		p.setWeightListJSON(((DBObject) doc.get(FIELD_WEIGHT_LIST)).toString());
		p.setStatus((String) doc.get(FIELD_STATUS));
		p.setToken((String) doc.get(FIELD_TOKEN));
		p.setSecret((String) doc.get(FIELD_SECRET));

		return p;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return this.gender;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public float getBMI() {
		try {
			return getBMI(weightList.get(weightList.size() - 1));
		} catch (Exception e) {
			return 0.0f;
		}
	}

	public float getBMI(Weight w) {
		try {
			return (float) ((w.getWeight() / Math.pow((this.height) / 100, 2)));
		} catch (Exception ex) {
			return 0.0f;
		}
	}

	public int getBMICode() {
		if (getBMI() < 18.5)
			return BMI_UNDERWEIGHT;
		else if (getBMI() < 24.9)
			return BMI_NORMAL;
		else if (getBMI() < 29.9)
			return BMI_OVERWEIGHT;
		return BMI_OBESE;
	}

	public String getBMICategory() {
		switch (getBMICode()) {
		case BMI_UNDERWEIGHT:
			return "Underweight";
		case BMI_NORMAL:
			return "Normal";
		case BMI_OVERWEIGHT:
			return "Overweight";
		default:
			return "Obese";
		}
	}

	public int getAge() {
		Calendar dob = Calendar.getInstance();
		dob.setTime(dateOfBirth);
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
		if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
			age--;
		} else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
				&& today.get(Calendar.DAY_OF_MONTH) < dob
						.get(Calendar.DAY_OF_MONTH)) {
			age--;
		}
		return age;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getHeight() {
		return height;
	}

	public double getHeightInches() {
		return height * 0.39370;
	}

	/**
	 * @param height
	 *            Enter height in centimetres
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public EmergencyContact getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(EmergencyContact emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public List<Weight> getWeightList() {
		return weightList;
	}

	public void setWeightList(List<Weight> weightList) {
		this.weightList = weightList;
	}

	public List<Encounter> getEncounters() {
		return encounters;
	}

	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}

	public List<String> getFamilyConditions() {
		return familyConditions;
	}

	public void setFamilyConditions(List<String> familyCondition) {
		this.familyConditions = familyCondition;
	}

	public List<Activity> getActivity() {
		return activity;
	}

	public void setActivity(List<Activity> activity) {
		this.activity = activity;
	}

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public static Patient getDummyPatient() {
		Patient p = new Patient();
		p.setFirstName("Test");
		p.setMiddleName("");
		p.setLastName("Patient");
		p.setDateOfBirth(new Date());
		p.setAddress1("123 Fake St.");
		p.setAddress2("Apt. 123");
		p.setCity("Southgate");
		p.setState(USStates.MI);
		p.setZip("48195");
		p.setPrimaryPhone("123-123-1234");
		p.setSecondaryPhone("123-123-1234");
		p.setEmail("test@daitio.com");
		p.setHeight(72);
		p.setInsuranceType("HAP PPO");
		p.setStatus(STATUS_GOOD);
		p.setEmergencyContact(new EmergencyContact(null, "Emergency",
				"Contact", "123-123-1234", "123-123-1234", "test@daitio.com",
				"Mother", "123 Fake St.", "Apt. 123", "Southgate", USStates.MI,
				"48195"));

		List<Weight> weightList = new ArrayList<Weight>();
		weightList.add(new Weight(new Date(), 180));
		weightList.add(new Weight(new Date(), 181));
		p.setWeightList(weightList);

		List<Prescription> rxList = new ArrayList<Prescription>();
		rxList.add(Prescription.getDummyPrescription());
		rxList.add(Prescription.getDummyPrescription());
		p.setPrescriptions(rxList);

		List<String> familyConditions = new ArrayList<String>();
		familyConditions.add("Diabetes");
		familyConditions.add("Alzheimer's Disease");
		p.setFamilyConditions(familyConditions);

		List<Activity> activity = new ArrayList<Activity>();
		activity.add(Activity.getDummyActivity());
		activity.add(Activity.getDummyActivity());
		p.setActivity(activity);

		List<Encounter> encounters = new ArrayList<Encounter>();
		encounters.add(Encounter.getDummyEncounter());
		encounters.add(Encounter.getDummyEncounter());
		p.setEncounters(encounters);

		return p;
	}

	public String getWeightListJSON() {
		return weightListJSON;
	}

	public void setWeightListJSON(String weightListJSON) {
		this.weightListJSON = weightListJSON;
	}

	public String getStatus() {

		// Add logic to determine non-use of Fitbit

		float currWeight = getCurrentWeight();
		float prevWeight = getWeightList().get(getWeightList().size() - 2).getWeight();
		
		switch (getBMICode()) {
		case BMI_UNDERWEIGHT:
			return STATUS_BAD;
		case BMI_NORMAL: {
			break;
		}
		case BMI_OVERWEIGHT:
		case BMI_OBESE: {
			if (getWeightList().size() > 1) {				
				if (currWeight < prevWeight)
					return STATUS_GOOD;
				else if (currWeight - 5.0 >= prevWeight)
					return STATUS_BAD;
			}
		}
		}
		return STATUS_NEUTRAL;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String accessToken) {
		this.token = accessToken;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String tokenSecret) {
		this.secret = tokenSecret;
	}

	public void setAccessToken(Token token) {
		setToken(token.getToken());
		setSecret(token.getSecret());
	}

	public Token getAccessToken() {
		return new Token(token, secret);
	}

	public float getCurrentWeight() {
		return (float) (weightList.get(weightList.size() - 1).getWeight());
	}

	public float getCurrentWeightPounds() {
		return (float) (getCurrentWeight() * 2.2046);
	}

	public void parseFitbitProfile(String json) {
		Patient p = this;
		try {
			JSONObject object = new JSONObject(json);
			JSONObject user = object.getJSONObject("user");

			p.setGender(user.getString(FIELD_GENDER));
			p.setHeight((float) user.getDouble(FIELD_HEIGHT));
			p.setFirstName(user.getString("displayName"));
			p.setLastName(user.getString("fullName")
					.replace(p.getFirstName(), "").replace(" ", ""));

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(user.getString(FIELD_DATE_OF_BIRTH));
			p.setDateOfBirth(date);

		} catch (Exception ex) {
			System.out.println("There was an error parsing JSON.");
			ex.printStackTrace();
		}
	}

	public void setWeightList(String json) {
		try {
			List<Weight> wList = new ArrayList<Weight>();

			JSONObject object = new JSONObject(json);
			JSONArray array = object.getJSONArray(Weight.FIELD_WEIGHT);

			for (int i = 0; i < array.length(); i++) {
				Weight w = new Weight();
				w.setWeight((float) array.getJSONObject(i).getDouble(
						Weight.FIELD_WEIGHT));

				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date = format.parse(array.getJSONObject(i).getString(
						Weight.FIELD_DATE));
				w.setDate(date);

				wList.add(w);

				setWeightList(wList);
			}
		} catch (Exception ex) {
			System.out.println("There was an error parsing JSON.");
			ex.printStackTrace();
		}
	}
}
