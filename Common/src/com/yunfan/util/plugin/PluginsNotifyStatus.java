package com.yunfan.util.plugin;

import java.util.HashSet;
import java.util.Set;

public class PluginsNotifyStatus {
	private Set<IPlugin> mPluginSet;
	
	public PluginsNotifyStatus() {
		mPluginSet = new HashSet<IPlugin>();
	}

	public boolean isComplete(){
		boolean result = true;
		PluginContainer pluginContainer = PluginContainer.getInstance();

		if (mPluginSet.size() != pluginContainer.getCount()){
			result = false;
		}else{
			for(IPlugin plugin:mPluginSet){
				if (!pluginContainer.getPluginSet().contains(plugin)){
					result = false;
					break;
				}
			}
		}
		return result;
	}
	
	public void notifyPluginStatus(IPlugin pPlugin){
		mPluginSet.add(pPlugin);
	}
}
