package com.anko.ui;

public class WeightDAO {
	
	private static WeightDAO mInstance;
	private String id;
	
	public static WeightDAO getInstance(){
		if (mInstance==null){
			mInstance=new WeightDAO();
		}
		
		return mInstance;
	}
	
	public String getUseId() {
		return id;
		}
	
	public void setUserId(String id) {
		this.id = id;
	}
	
	
}
