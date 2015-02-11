package com.daitio.arboremr;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.util.DigestUtils;

public class User {
	
	private static final String MD5_KEY = "D41t10-4rB0r";
	
	private ObjectId id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
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
		//if (password.length() > 6)
			this.password = hashPassword(password);
		//else
		//	this.password = null;
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
		oReturn += "<table style=\"width:100%\">";
		
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
			oReturn += "<td><a href=\"edituser.html?id=" + uList.get(i).getUsername() + "\">Edit User</a>" + "</td>";
			oReturn += "</tr>";
		}
		
		oReturn += "</table>";
		
		return oReturn;
	}
}
