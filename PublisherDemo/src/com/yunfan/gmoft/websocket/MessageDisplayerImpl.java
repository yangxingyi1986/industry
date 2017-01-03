package com.yunfan.gmoft.websocket;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MessageDisplayerImpl extends UnicastRemoteObject implements  IMessageDisplayer {

	private static final long serialVersionUID = 1L;
	
	
	@Override
	public boolean pushMessage(String pMessage) throws RemoteException {
		return false;
	}

	@Override
	public boolean pushMessage2(String pMessage) throws RemoteException {
		return false;
	}

    public MessageDisplayerImpl() throws RemoteException {
	}

}

