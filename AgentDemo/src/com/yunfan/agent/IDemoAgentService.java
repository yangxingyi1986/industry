package com.yunfan.agent;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IDemoAgentService extends Remote{
	public void sendMessage(String pMessageID, String pMessage) throws RemoteException;
	public void setYunfanbus(String pSendChannel, String pSendRoutekey) throws RemoteException;
	public void stop() throws RemoteException;
	public void setPluginFullName(String pPluginFullName) throws RemoteException;
}
