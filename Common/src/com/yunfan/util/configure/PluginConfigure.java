package com.yunfan.util.configure;

import java.io.Serializable;

public class PluginConfigure implements Serializable{

	private String pluginFullName;
	private String pluginFilePath;
	private String pluginClass;

	public PluginConfigure() {
		super();
	}

	public String getPluginFullName() {
		return pluginFullName;
	}

	public void setPluginFullName(String agentFullName) {
		this.pluginFullName = agentFullName;
	}

	public String getPluginFilePath() {
		return pluginFilePath;
	}

	public void setPluginFilePath(String agentFilePath) {
		this.pluginFilePath = agentFilePath;
	}

	public String getPluginClass() {
		return pluginClass;
	}

	public void setPluginClass(String agentClass) {
		this.pluginClass = agentClass;
	}

	public boolean equalsAll(Object pObj) {
		if (pObj==null){
			return false;
		} else{
			if (pObj instanceof PluginConfigure){
				PluginConfigure pPluginConfigure = (PluginConfigure)pObj;
				if (pPluginConfigure.pluginClass.equals(this.pluginClass) && 
						pPluginConfigure.pluginFilePath.equals(this.pluginFilePath) && 
						pPluginConfigure.pluginFullName.equals(this.pluginFullName)){
		                     return true;
				}
			}
		}
		return false;
	}

	public boolean equalsInstance(Object pObj) {
		if (pObj==null){
			return false;
		} else{
			if (pObj instanceof PluginConfigure){
				PluginConfigure pPluginConfigure = (PluginConfigure)pObj;
				if (pPluginConfigure.pluginFullName.equals(this.pluginFullName)){
		                     return true;
				}
			}
		}
		return false;	
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return pluginFullName + pluginFilePath + pluginClass;
	}

}