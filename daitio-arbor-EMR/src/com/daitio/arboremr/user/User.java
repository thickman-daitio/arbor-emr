package com.daitio.arboremr.user;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.util.DigestUtils;

import com.daitio.arboremr.MongoConnector;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class User {
	
	private static final String MD5_KEY = "D41t10-4rB0r";
	
	public ObjectId id;
	public String username;
	public String password;
	public String firstName;
	public String lastName;
	
	public User() {
		super();
		id = new ObjectId();
		username = "";
		password = "";
		firstName = "";
		lastName = "";
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
		this.password = hashPassword(password);
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
	public static String hashPassword(String password) {
		password += MD5_KEY;
		String hash = DigestUtils.md5DigestAsHex(password.getBytes());
		hash = DigestUtils.md5DigestAsHex(password.getBytes());
		return hash;
	}
	public static String getAllUsersRepeater(List<User> uList) {
		String oReturn = "";
		oReturn += "<table style=\"width:100%\" class=\"table table-striped table-advance table-hover\">";
		
		oReturn += "<tr><th>First Name</th><th>Last Name</th><th>Username</th></tr>";
		
		for (int i = 0; i < uList.size(); i++) {
			oReturn += "<tr>";
			oReturn += "<td>" 
					+ uList.get(i).getFirstName() 
					+ "</td><td>" 
					+ uList.get(i).getLastName() 
					+ "</td><td>" 
					+ uList.get(i).getUsername() 
					+ "</td>";
			oReturn += "<td><a href=\"edituser.html?id=" + uList.get(i).getId().toString() + "\">Edit User</a>" + "</td>";
			oReturn += "</tr>";
		}
		
		oReturn += "</table>";
		
		return oReturn;
	}
	
	public static DBObject toDBObject(User u) {

		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append(MongoUserDAO.FIELD_USERNAME, u.getUsername())
				.append(MongoUserDAO.FIELD_PASSWORD, u.getPassword())
				.append(MongoUserDAO.FIELD_FIRSTNAME, u.getFirstName())
				.append(MongoUserDAO.FIELD_LASTNAME, u.getLastName());

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
		
		return u;
	}
}
