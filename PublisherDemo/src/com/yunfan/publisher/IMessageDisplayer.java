package com.yunfan.publisher;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMessageDisplayer extends Remote{
	public boolean pushMessage(String pMessage) throws RemoteException;
	public boolean pushMessage2(String pMessage) throws RemoteException;
}