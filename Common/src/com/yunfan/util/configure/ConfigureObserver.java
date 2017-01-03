package com.yunfan.util.configure;

public interface ConfigureObserver {
	public void notifyChangeConfigure(PluginConfigure pConfigure);
	public void notifyAddConfigure(PluginConfigure pConfigure);
	public void notifyDeleteConfigure(PluginConfigure pConfigure);
}
