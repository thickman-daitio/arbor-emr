package com.daitio.arboremr.user;

import org.bson.types.ObjectId;
import org.springframework.util.DigestUtils;

import com.daitio.arboremr.MongoConnector;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class User {

	private static final String MD5_KEY = "D41t10-4rB0r";

	public static final String ROLE_DOCTOR = "DOCTOR";
	public static final String ROLE_PATIENT = "PATIENT";

	public ObjectId id;
	public String username;
	public String password;
	public String firstName;
	public String lastName;
	public String role;

	public User() {
		id = new ObjectId();
		username = "";
		password = "";
		firstName = "";
		lastName = "";
		role = ROLE_DOCTOR;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		setPassword(password, true);
	}

	public void setPassword(String password, boolean hash) {
		if (!password.equals("")) {
			if (hash)
				this.password = hashPassword(password);
			else
				this.password = password;
		}
	}

	public void setHashedPassword(String hashedPassword) {
		this.password = hashedPassword;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		if (role == null)
			this.role = ROLE_PATIENT;
		else
			this.role = role;
	}

	public static String hashPassword(String password) {
		password += MD5_KEY;
		String hash = DigestUtils.md5DigestAsHex(password.getBytes());
		hash = DigestUtils.md5DigestAsHex(password.getBytes());
		return hash;
	}

	public static DBObject toDBObject(User u) {

		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append(MongoUserDAO.FIELD_USERNAME, u.getUsername())
				.append(MongoUserDAO.FIELD_PASSWORD, u.getPassword())
				.append(MongoUserDAO.FIELD_FIRSTNAME, u.getFirstName())
				.append(MongoUserDAO.FIELD_LASTNAME, u.getLastName())
				.append(MongoUserDAO.FIELD_ROLE, u.getRole());

		if (u.getId() != null)
			builder = builder.append(MongoConnector.MONGO_FIELD_ID, u.getId());

		return builder.get();
	}

	public static User toUser(DBObject doc) {
		User u = new User();

		u.setUsername((String) doc.get(MongoUserDAO.FIELD_USERNAME));
		u.setPassword((String) doc.get(MongoUserDAO.FIELD_PASSWORD));
		u.setFirstName((String) doc.get(MongoUserDAO.FIELD_FIRSTNAME));
		u.setLastName((String) doc.get(MongoUserDAO.FIELD_LASTNAME));
		u.setId((ObjectId) doc.get(MongoConnector.MONGO_FIELD_ID));
		u.setRole((String) doc.get(MongoUserDAO.FIELD_ROLE));

		return u;
	}
}
