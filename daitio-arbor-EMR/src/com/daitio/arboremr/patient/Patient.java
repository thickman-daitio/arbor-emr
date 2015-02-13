package com.daitio.arboremr.patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.daitio.arboremr.MongoConnector;
import com.daitio.arboremr.encounter.Encounter;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class Patient {

	public final static String FIELD_FIRST_NAME = "firstName";
	public final static String FIELD_MIDDLE_NAME = "middleName";
	public final static String FIELD_LAST_NAME = "lastName";
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

	private ObjectId id;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date dateOfBirth;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String primaryPhone;
	private String secondaryPhone;
	private String email;
	private int height; // inches
	private String insuranceType;
	private EmergencyContact emergencyContact;
	private List<Weight> weightList;
	private List<Encounter> encounters;
	private List<String> familyConditions;
	private List<Activity> activity;
	private List<Prescription> prescriptions;

	public Patient() {

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
				.append(FIELD_EMERGENCY_CONTACT, p.getEmergencyContact().toDBObject())
				.append(FIELD_WEIGHT_LIST, new Weight().toBasicDBList(p.getWeightList()))
				.append(FIELD_PRESCRIPTIONS, new Prescription().toBasicDBList(p.getPrescriptions()))
				.append(FIELD_ENCOUNTERS, new Encounter().toBasicDBList(p.getEncounters()));
							
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
		p.setHeight((int) doc.get(FIELD_HEIGHT));
		p.setInsuranceType((String) doc.get(FIELD_INSURANCE_TYPE));
		p.setEmergencyContact(EmergencyContact.toEmergencyContact((DBObject) doc.get(FIELD_EMERGENCY_CONTACT)));
		p.setWeightList(Weight.toWeightList((DBObject) doc.get(FIELD_WEIGHT_LIST)));
		p.setPrescriptions(Prescription.toPrescriptionList((DBObject) doc.get(FIELD_PRESCRIPTIONS)));
		p.setEncounters(Encounter.toEncounterList((DBObject) doc.get(FIELD_ENCOUNTERS)));

		return p;
	}
	
	public static String getAllPatientsRepeater(List<Patient> pList) {
		String oReturn = "";
		oReturn += "<table style=\"width:100%\">";
		
		oReturn += "<tr><th>First Name</th><th>Middle Name</th><th>Last Name</th></tr>";
		
		for (int i = 0; i < pList.size(); i++) {
			oReturn += "<tr>";
			oReturn += "<td>" 
					+ pList.get(i).getFirstName() 
					+ "</td><td>" 
					+ pList.get(i).getMiddleName() 
					+ "</td><td>" 
					+ pList.get(i).getLastName() 
					+ "</td>";
			oReturn += "<td><a href=\"viewpatient.html?id=" + pList.get(i).getId() + "\">View Patient</a>" + "</td>";
			oReturn += "</tr>";
		}
		
		oReturn += "</table>";
		
		return oReturn;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            Enter height in inches
	 */
	public void setHeight(int height) {
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
		p.setMiddleName("A");
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
		p.setEmergencyContact(new EmergencyContact(null, "Emergency",
				"Contact", "123-123-1234", "123-123-1234", "test@daitio.com",
				"Mother", "123 Fake St.", "Apt. 123", "Southgate", USStates.MI,
				"48195"));

		List<Weight> weightList = new ArrayList<Weight>();
		weightList.add(new Weight(null, new Date(), 180));
		weightList.add(new Weight(null, new Date(), 181));
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
}
