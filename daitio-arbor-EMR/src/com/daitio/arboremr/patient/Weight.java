package com.daitio.arboremr.patient;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.daitio.arboremr.MongoConnector;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class Weight implements PatientListObject {

	public static final String FIELD_DATE = "date";
	public static final String FIELD_WEIGHT = "weight";

	private ObjectId id;
	private Date date;
	private int weight; // lbs

	public Weight() {

	}

	public Weight(ObjectId id, Date date, int weight) {
		this.id = id;
		this.date = date;
		this.weight = weight;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param height
	 *            Enter weight in lbs
	 */
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public DBObject toDBObject() {
		return toDBObject(this);
	}

	@Override
	public DBObject toDBObject(Object o) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append(FIELD_DATE, ((Weight) o).getDate())
				.append(FIELD_WEIGHT, ((Weight) o).getWeight());

		if (((Weight) o).getId() != null)
			builder = builder.append(MongoConnector.MONGO_FIELD_ID,
					((Weight) o).getId());

		return builder.get();
	}

	@Override
	public <T> BasicDBList toBasicDBList(List<T> list) {
		BasicDBList dbList = new BasicDBList();

		for (int i = 0; i < list.size(); i++) {
			dbList.add(((Weight) list.get(i)).toDBObject());
		}

		return dbList;
	}

	@Override
	public Object toObject(DBObject doc) {
		Weight w = new Weight();
		
		w.setDate((Date) doc.get(FIELD_DATE));
		w.setWeight((int) doc.get(FIELD_WEIGHT));
		
		return w;
	}
}