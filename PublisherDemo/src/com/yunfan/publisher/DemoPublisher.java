package com.yunfan.publisher;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import com.yunfan.gmoft.websocket.IMessageDisplayer;
import com.yunfan.publisher.demo.util.Constant;
import com.yunfan.util.bus.ReceiveChannel;
import com.yunfan.util.bus.YunfanBusConnection;
import com.yunfan.util.command.ICmd;
import com.yunfan.util.model.SampleBean;
import com.yunfan.util.model.JsonBean.JsonType;
import com.yunfan.util.plugin.PluginNotification;

public class DemoPublisher extends Publisher implements IWorker{
	YunfanBusConnection mBusConnection;
	
	//@override
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
     * 为处理Bus中的消息做准备
     * */
	public void prepareProcessMessage(){
		mBusConnection = mYunfanBus.connectToBus();
		List<String> bindingKeyList = new ArrayList<String>();
		bindingKeyList.add("rmiDemo1");
		ReceiveChannel receiveChannel =  mBusConnection.createReceiveChannel("DemoTransfer1", bindingKeyList);
		DemoPublisherReceiver demoPublisherReceiver = new DemoPublisherReceiver();
		demoPublisherReceiver.setWorker(this);
		demoPublisherReceiver.setJsonType(JsonType.SAMPLE);
		receiveChannel.setBusReceiver(demoPublisherReceiver);
		
		PluginNotification pluginNotification = new PluginNotification();
		pluginNotification.setFrom(this);
		pluginNotification.setName("start");
		pluginNotification.setNotificationJson("");
		notify(pluginNotification);
	}
	
	/*
     * 处理Bus中的消息
     * */
	public void processMessage(SampleBean pSample){
		try {
			System.out.println(pSample.getId());
			//调用远程对象，注意RMI路径与接口
			IMessageDisplayer messageDisplayer = (IMessageDisplayer)Naming.lookup(Constant.WEB_SOCKET_ADDRESS);
			messageDisplayer.pushMessage(pSample.getId());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*
     * 停止处理Bus中的消息
     * */
	public void stopProcessMessage(){
		try{
			mBusConnection.close();
			this.setStopFlag(true);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
