package com.daitio.arboremr.patient;

import org.bson.types.ObjectId;

import com.daitio.arboremr.MongoConnector;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class EmergencyContact {

	public final static String FIELD_FIRST_NAME = "firstName";
	public final static String FIELD_LAST_NAME = "lastName";
	public final static String FIELD_PRIMARY_PHONE = "primaryPhone";
	public final static String FIELD_SECONDARY_PHONE = "secondaryPhone";
	public final static String FIELD_EMAIL = "email";
	public final static String FIELD_RELATIONSHIP_TO_PATIENT = "relationshipToPatient";
	public final static String FIELD_ADDRESS_1 = "address1";
	public final static String FIELD_ADDRESS_2 = "address2";
	public final static String FIELD_CITY = "city";
	public final static String FIELD_STATE = "state";
	public final static String FIELD_ZIP = "zip";
	
	public ObjectId id;
	public String firstName;
	public String lastName;
	public String primaryPhone;
	public String secondaryPhone;
	public String email;
	public String relationshipToPatient;
	public String address1;
	public String address2;
	public String city;
	public String state;
	public String zip;

	public EmergencyContact() {

	}

	public EmergencyContact(ObjectId id, String firstName, String lastName,
			String primaryPhone, String secondaryPhone, String email,
			String relationshipToPatient, String address1, String address2,
			String city, String state, String zip) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.primaryPhone = primaryPhone;
		this.secondaryPhone = secondaryPhone;
		this.email = email;
		this.relationshipToPatient = relationshipToPatient;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public DBObject toDBObject() {
		return toDBObject(this);
	}
	
	public static DBObject toDBObject(EmergencyContact e) {

		BasicDBObjectBuilder builder = BasicDBObjectBuilder
				.start()
				.append(FIELD_FIRST_NAME, e.getFirstName())
				.append(FIELD_LAST_NAME, e.getLastName())
				.append(FIELD_PRIMARY_PHONE, e.getPrimaryPhone())
				.append(FIELD_SECONDARY_PHONE, e.getSecondaryPhone())
				.append(FIELD_EMAIL, e.getEmail())
				.append(FIELD_RELATIONSHIP_TO_PATIENT, e.getRelationshipToPatient())
				.append(FIELD_ADDRESS_1, e.getAddress1())
				.append(FIELD_ADDRESS_2, e.getAddress2())
				.append(FIELD_CITY, e.getCity())
				.append(FIELD_STATE, e.getState())
				.append(FIELD_ZIP, e.getZip());

		if (e.getId() != null)
			builder = builder.append(MongoConnector.MONGO_FIELD_ID, e.getId());

		return builder.get();
	}
	
	public static EmergencyContact toEmergencyContact(DBObject db) {
		EmergencyContact e = new EmergencyContact();
		
		e.setFirstName((String) db.get(FIELD_FIRST_NAME));
		e.setLastName((String) db.get(FIELD_LAST_NAME));
		e.setPrimaryPhone((String) db.get(FIELD_PRIMARY_PHONE));
		e.setSecondaryPhone((String) db.get(FIELD_SECONDARY_PHONE));
		e.setRelationshipToPatient((String) db.get(FIELD_RELATIONSHIP_TO_PATIENT));
		e.setAddress1((String) db.get(FIELD_ADDRESS_1));
		e.setAddress2((String) db.get(FIELD_ADDRESS_2));
		e.setCity((String) db.get(FIELD_CITY));
		e.setState((String) db.get(FIELD_STATE));
		e.setZip((String) db.get(FIELD_ZIP));

		return e;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getRelationshipToPatient() {
		return relationshipToPatient;
	}

	public void setRelationshipToPatient(String relationshipToPatient) {
		this.relationshipToPatient = relationshipToPatient;
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

	public void setState(String stateAbbrev) {
		this.state = stateAbbrev;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
