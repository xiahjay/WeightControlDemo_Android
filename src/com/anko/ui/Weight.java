package com.anko.ui;

public class Weight {
	private String userDate;
	private Float userWeight;
	
	public String getUserDate() {
		return userDate;
	}

	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}

	public Float getUserWeight() {
		return userWeight;
	}

	public void setUserWeight(Float userWeight) {
		this.userWeight = userWeight;
	}
	
	public Weight(Float userWeight, String userDate) {
		super();
		this.userWeight = userWeight;
		this.userDate = userDate;
	}

}
