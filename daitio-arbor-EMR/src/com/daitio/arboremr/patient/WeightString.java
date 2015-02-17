package com.daitio.arboremr.patient;

public class WeightString {
	
	private String strDate;
	private String strWeight;

	public WeightString(String date, String weight) {
		setStrDate(date);
		setStrWeight(weight);
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getStrWeight() {
		return strWeight;
	}

	public void setStrWeight(String strWeight) {
		this.strWeight = strWeight;
	}
}