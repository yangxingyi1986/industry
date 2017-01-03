package com.yunfan.gmoft.websocket;

import java.rmi.RemoteException;

import org.springframework.web.socket.TextMessage;

//public class MessageDisplayerImpl extends UnicastRemoteObject implements  IMessageDisplayer {
public class MessageDisplayerImpl implements  IMessageDisplayer {
	SystemWebSocketHandler mSystemWebSocketHandler;
	
	/**
	 * 并发情况下可能会出现[java.lang.IllegalStateException: The remote endpoint was in state [TEXT_PARTIAL_WRITING] which is an invalid state for called method]
	 * 故调整为同步发送消息
	 */
	@Override
	public boolean pushMessage(String pMessage) throws RemoteException {
		synchronized (MessageDisplayerImpl.class) {
			mSystemWebSocketHandler.sendMessageToUsers(new TextMessage(pMessage));
		}
		return false;
	}

	/**
	 * 并发情况下可能会出现[java.lang.IllegalStateException: The remote endpoint was in state [TEXT_PARTIAL_WRITING] which is an invalid state for called method]
	 * 故调整为同步发送消息
	 */
	@Override
	public boolean pushMessage2(String pMessage) throws RemoteException {
		synchronized (MessageDisplayerImpl.class) {
			mSystemWebSocketHandler.sendMessageToUsers(new TextMessage(pMessage));
		}
		return false;
	}

    public MessageDisplayerImpl() throws RemoteException {
    	mSystemWebSocketHandler = new SystemWebSocketHandler();
	}

}

