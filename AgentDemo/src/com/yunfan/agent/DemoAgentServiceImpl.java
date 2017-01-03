package com.yunfan.agent;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import org.apache.log4j.Logger;

import com.yunfan.agent.demo.Constant;
import com.yunfan.util.bus.SendChannel;
import com.yunfan.util.bus.YunfanBus;
import com.yunfan.util.bus.YunfanBusConnection;
import com.yunfan.util.model.MessageBean;


public class DemoAgentServiceImpl extends UnicastRemoteObject implements IDemoAgentService{
	
	private static Logger sLog = Logger.getLogger("log.agent");
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	YunfanBus mYunfanBus;
	SendChannel mSendChannel;
	String mReceiveRoutekey;
	String mPluginFullName;
	
	protected DemoAgentServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setYunfanbus(String pSendChannel, String pSendRoutekey){
		mYunfanBus = new YunfanBus();
		YunfanBusConnection con = mYunfanBus.connectToBus();
		mSendChannel = con.createSendChannel(pSendChannel);
		mReceiveRoutekey = pSendRoutekey;
		System.out.println("SetYunFanBus!");
	}
	
	@Override
	public void setPluginFullName(String pPluginFullName) {
		mPluginFullName = pPluginFullName;
	}
	
	@Override
	public void sendMessage(String pMessageID, String pMessage) throws RemoteException {
		//sLog.debug("pMessageID" + pMessageID);
		
		MessageBean message = new MessageBean();
		message.setId(pMessageID);
		message.setName("forDemo");
		message.setValue(pMessage);
		message.setResourceCD("01");
		message.setAgentFullName(mPluginFullName);
		message.setTimeStamp(new Date());
		mSendChannel.sendObject(message, mReceiveRoutekey);
	}
	
	@Override
	public void stop() throws RemoteException {
		try {
			sLog.debug("Agent Demo Stop!");
			Naming.unbind(Constant.AGENT_DEMO_SERVICE_ADDRESS);
			System.exit(0);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
