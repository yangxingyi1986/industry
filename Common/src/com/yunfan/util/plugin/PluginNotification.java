package com.yunfan.util.plugin;

public class PluginNotification {
	
	private String name;
	private IPlugin from;
	private String notificationJson;
	
	public String getNotificationJson() {
		return notificationJson;
	}
	public void setNotificationJson(String notificationJson) {
		this.notificationJson = notificationJson;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IPlugin getFrom() {
		return from;
	}
	public void setFrom(IPlugin from) {
		this.from = from;
	}
	
}
