package com.yunfan.transfer;

import java.util.ArrayList;
import java.util.List;

import com.yunfan.transfer.Transfer;
import com.yunfan.util.bus.ReceiveChannel;
import com.yunfan.util.bus.SendChannel;
import com.yunfan.util.bus.YunfanBusConnection;
import com.yunfan.util.command.ICmd;
import com.yunfan.util.model.MessageBean;
import com.yunfan.util.model.SampleBean;
import com.yunfan.util.model.JsonBean.JsonType;
import com.yunfan.util.plugin.PluginNotification;

public class DemoTransfer extends Transfer implements IWorker{
	YunfanBusConnection mBusConReceive;
	YunfanBusConnection mBusConSend;
	SendChannel mSendChannel;
	
	@Override
	public ICmd getCmd(String pCmdName) {

		if ("Start".equals(pCmdName)){
			return new DemoStartCmd();
		}else if("Stop".equals(pCmdName)){
			return new DemoStopCmd();
		}else{
			return null;
		}
		
	}
	
	/*
     * 准备处理来自Bus的消息
     * */
	public void prepareProcessMessage(){
		mBusConReceive = mYunfanBus.connectToBus();
		List<String> bindingKeyList = new ArrayList<String>();
		bindingKeyList.add("rmiDemo");
		ReceiveChannel receiveChannel =  mBusConReceive.createReceiveChannel("Demo1", bindingKeyList);
		DemoTransferReceiver demoTransferReceiver = new DemoTransferReceiver();
		demoTransferReceiver.setJsonType(JsonType.MESSAGE);
		demoTransferReceiver.setWorker(this);
		receiveChannel.setBusReceiver(demoTransferReceiver);
		
		mBusConSend = mYunfanBus.connectToBus();
		mSendChannel = mBusConSend.createSendChannel("DemoTransfer1");
		PluginNotification pluginNotification = new PluginNotification();
		pluginNotification.setFrom(this);
		pluginNotification.setName("start");
		pluginNotification.setNotificationJson("");
		notify(pluginNotification);
	}
	
	/*
     * 处理来自Bus的消息
     * */
	public void processMessage(MessageBean pMessage){
		System.out.println("DemoTransfer" + pMessage.getId());
		SampleBean sample = new SampleBean();
		sample.setId(pMessage.getId());
		sample.setValue(pMessage.getValue()); // 通过Sample转换引擎 转换
		sample.setMeterName(null); // 设置MeterName
		sample.setResourceCD(pMessage.getResourceCD());
		sample.setAgentFullName(pMessage.getAgentFullName());
		sample.setTransferFullName(this.getPluginConfigure().getPluginFullName());
		sample.setTimestamp(pMessage.getTimeStamp());
		
//		String message = pMessage.getValue();
//		String messages[] = message.split(";");
//		System.out.println("message = " + pMessage.getMessage());
		
//		System.out.println("messages[0] = " + messages[0]);
//		System.out.println("messages[1] = " + messages[1]);
		mSendChannel.sendObject(sample, "rmiDemo1");
	}
	
	/*
     * 停止处理Bus中的消息
     * */
	public void stopProcessMessage(){
		try{
			mBusConReceive.close();
			mBusConSend.close();
			this.setStopFlag(true);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
