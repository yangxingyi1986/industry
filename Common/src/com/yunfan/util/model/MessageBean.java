package com.yunfan.util.model;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageBean extends JsonBean {

	private String id;
	private String name;
	private String value;
	private String resourceCD;
	private String agentFullName;
	private Date timeStamp;

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public MessageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String objectToJsonlet() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.toJson(this, MessageBean.class);
	}

}
