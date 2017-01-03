package com.yunfan.util.model;

import java.util.List;

public class CombineBean extends JsonBean{
	private String combineID;
	private List<SampleBean> sampleList;
	
	public List<SampleBean> getSampleList() {
		return sampleList;
	}

	public void setSampleList(List<SampleBean> sampleList) {
		this.sampleList = sampleList;
	}

	public String getCombineID() {
		return combineID;
	}

	public void setCombineID(String combineID) {
		this.combineID = combineID;
	}

	public CombineBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 
}
