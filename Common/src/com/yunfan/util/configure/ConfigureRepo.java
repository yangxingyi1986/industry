package com.yunfan.util.configure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConfigureRepo {
	
	private Map<String, PluginConfigure> mConfigureMap;
	
	public enum ChangeState{
		DELETE_ADD,
		CHANGE,
		NO_CHANGE
	}
	
	public ConfigureRepo() {
		super();
		mConfigureMap = new HashMap<String, PluginConfigure>();
		// TODO Auto-generated constructor stub
	}
	
	public void add(PluginConfigure pConfigure, String pConfigureFileName){
		mConfigureMap.put(pConfigureFileName, pConfigure);
	}
	
	public ChangeState isChange(PluginConfigure pConfigure, String pConfigureFileName){
		PluginConfigure oldConfigure = mConfigureMap.get(pConfigureFileName);
		ChangeState ret = ChangeState.NO_CHANGE;
		if (!oldConfigure.equalsAll(pConfigure)){
			ret = ChangeState.CHANGE;
		}
		if (!oldConfigure.equalsInstance(pConfigure)){
			ret = ChangeState.DELETE_ADD;
		}
		return ret;
	}
	
	public PluginConfigure remove(String pConfigureFileName){
		return mConfigureMap.remove(pConfigureFileName);
	}
	
	public void replace(PluginConfigure pConfigure, String pConfigureFileName){
		remove(pConfigureFileName);
		add(pConfigure, pConfigureFileName);
	}
	
	/*
	 * 删除所有的配置文件，并返回删除的配置信息
	 */
	public List<PluginConfigure> clear(){
		List<PluginConfigure> configureList  = new ArrayList<PluginConfigure>();
		Set<String> keySet = mConfigureMap.keySet();
		for(String key: keySet){
			configureList.add(mConfigureMap.get(key));
		}
		mConfigureMap.clear();
		return configureList;
	}
	
}
