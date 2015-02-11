package com.daitio.arboremr.patient;

import java.util.Date;

public class Activity {

	private String type;
	private Date date;
	private long startTimeMillis;
	private long endTimeMillis;
	private int steps;

	public Activity() {

	}

	public Activity(String type, Date date, long startTimeMillis,
			long endTimeMillis, int steps) {
		this.type = type;
		this.date = date;
		this.startTimeMillis = startTimeMillis;
		this.endTimeMillis = endTimeMillis;
		this.steps = steps;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getStartTimeMillis() {
		return startTimeMillis;
	}

	public void setStartTimeMillis(long startTimeMillis) {
		this.startTimeMillis = startTimeMillis;
	}

	public long getEndTimeMillis() {
		return endTimeMillis;
	}

	public void setEndTimeMillis(long endTimeMillis) {
		this.endTimeMillis = endTimeMillis;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	public static Activity getDummyActivity() {
		Activity a = new Activity();
		a.setType("Walking");
		a.setDate(new Date());
		a.setStartTimeMillis(new Date().getTime());
		a.setEndTimeMillis(new Date().getTime() + 3600000);
		a.setSteps(5000);
		return a;
	}

}
