package com.anko.ui;

public class LatestWeightDAO {
	
	private static LatestWeightDAO mInstance;
	private String weight;
	
	public static LatestWeightDAO getInstance(){
		if (mInstance==null){
			mInstance=new LatestWeightDAO();
		}
		
		return mInstance;
	}
	
	public String getLatestWeight() {
		return weight;
		}
	
	public void setLatestWeight(String id) {
		this.weight = weight;
	}

}
