package com.yunfan.util.plugin;

import java.rmi.RemoteException;

public interface PluginManagerServiceMBean {
	public void dispatchCmdToAgent(String resName, String jsonBean) throws RemoteException;
	public boolean start() throws RemoteException;
	public boolean stop() throws RemoteException;
}
