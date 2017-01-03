package com.yunfan.util.model;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SampleBean extends JsonBean {

	private String id;
	private String value;
	private String meterName;
	private String resourceCD;
	private String agentFullName;
	private String transferFullName;
	private Date timestamp;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getMeterName() {
		return meterName;
	}

	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}

	public String getResourceCD() {
		return resourceCD;
	}

	public void setResourceCD(String resourceCD) {
		this.resourceCD = resourceCD;
	}

	public String getAgentFullName() {
		return agentFullName;
	}

	public void setAgentFullName(String agentFullName) {
		this.agentFullName = agentFullName;
	}

	public String getTransferFullName() {
		return transferFullName;
	}

	public void setTransferFullName(String transferFullName) {
		this.transferFullName = transferFullName;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public SampleBean() {
		super();
	}

	
	public String objectToJsonlet(){
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.toJson(this, SampleBean.class);
	}
	

}
