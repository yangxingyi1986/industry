package com.yunfan.util.plugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PluginContainer {
	private static final PluginContainer pluginContainer = new PluginContainer();
	
	public static PluginContainer getInstance(){
		return pluginContainer;
	}
	
	Map<String, IPlugin> mAgentMap; 
	
	public PluginContainer() {
		super();
		mAgentMap = new HashMap<String, IPlugin>();
		// TODO Auto-generated constructor stub
	}
	
	public void add(String agentFullName, IPlugin pAgent){
		mAgentMap.put(agentFullName, pAgent);
	}
	
	public IPlugin delete(String agentFullName){
		IPlugin deleteAgent = mAgentMap.get(agentFullName);
		mAgentMap.remove(agentFullName);
		return deleteAgent;
	}
	
	public Integer getCount(){
		return mAgentMap.size();
	}
	
	public Collection<IPlugin> getPluginSet(){
		return mAgentMap.values();
	}
	
}
