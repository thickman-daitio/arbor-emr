package com.daitio.arboremr;

import org.bson.types.ObjectId;
import org.springframework.util.DigestUtils;

public class User {
	
	private static final String MD5_KEY = "D41t10-4rB0r";
	
	private ObjectId id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
		
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
}
