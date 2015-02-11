package com.daitio.arboremr.patient;

import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

public interface PatientListObject {
	
	public DBObject toDBObject();
	public DBObject toDBObject(Object o);
	public <T> BasicDBList toBasicDBList(List<T> list);
	public Object toObject(DBObject doc);
}
