package com.yunfan.publisher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.yunfan.util.bus.ReceiveChannel;
import com.yunfan.util.bus.YunfanBusConnection;
import com.yunfan.util.command.ICmd;
import com.yunfan.util.model.SampleBean;
import com.yunfan.util.model.JsonBean.JsonType;
import com.yunfan.util.plugin.PluginNotification;

public class Demo2Publisher extends Publisher implements IWorker{
	

	YunfanBusConnection mBusConnection;
	MongoCollection<Document> mCollection;
	MongoClient mMongoClient;
	
	Date mBeginDate ;
	Date mEndDate ;
	
	public ICmd getCmd(String pCmdName) {
		if ("Start".equals(pCmdName)){
			return new Demo2StartCmd();
		}else if("Stop".equals(pCmdName)){
			return new Demo2StopCmd();
		}else{
			return null;
		}
	}
	
	 /*
     * 为处理Bus中的消息做准备
     * */
	public void prepareProcessMessage(){
		//MongoDB连接
		mMongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mMongoClient.getDatabase("GmoftMonitor");
		mCollection = database.getCollection("Sample");
		
		//初始化接收数据参数
		mBusConnection = mYunfanBus.connectToBus();
		List<String> bindingKeyList = new ArrayList<String>();
		bindingKeyList.add("rmiDemo1");
		ReceiveChannel receiveChannel =  mBusConnection.createReceiveChannel("DemoTransfer1", bindingKeyList);
		Demo2PublisherReceiver demoPublisherReceiver = new Demo2PublisherReceiver();
		demoPublisherReceiver.setWorker(this);
		demoPublisherReceiver.setJsonType(JsonType.SAMPLE);
		receiveChannel.setBusReceiver(demoPublisherReceiver);
		
		//通知开始完毕
		PluginNotification pluginNotification = new PluginNotification();
		pluginNotification.setFrom(this);
		pluginNotification.setName("start");
		pluginNotification.setNotificationJson("");
		notify(pluginNotification);
		
		mBeginDate = null;
		mEndDate = null;
		
	}
	
	/*
     * 处理Bus中的消息
     * */
	public void processMessage(SampleBean pSample){
		try {
			System.out.println(pSample.getId());
			//调用远程对象，注意RMI路径与接口
//			IMessageDisplayer messageDisplayer = (IMessageDisplayer)Naming.lookup(Constant.WEB_SOCKET_ADDRESS);
//			messageDisplayer.pushMessage(pSample.getSampleID());

			if ("0".equals(pSample.getId())){
				mBeginDate = new Date();
			}else if  ("999999".equals(pSample.getId())){
				mEndDate = new Date();
				long beginTime = mBeginDate.getTime(); 
				long endTime = mEndDate.getTime(); 
				long betweenS = (long)((endTime - beginTime) / 1000); 
				System.out.println("beginDate = " + mBeginDate);
				System.out.println("endDate = " + mEndDate);
				System.out.println("timeSpan =" + betweenS);
			}
		
//			System.out.println("id = " + pSample.getSampleID());
//			System.out.println("timestamp = " + pSample.getTimestamp());
//			System.out.println("pos =" + pSample.getValue());
			
			Document doc = new Document("id", pSample.getId())
					.append("value", pSample.getValue())
					.append("meterName", pSample.getMeterName())
					.append("resourceCD", pSample.getResourceCD())
					.append("agentFullName", pSample.getAgentFullName())
					.append("transferFullName", pSample.getTransferFullName())
					.append("timestamp", pSample.getTimestamp());
					
			mCollection.insertOne(doc);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*
     * 停止处理Bus中的消息
     * */
	public void stopProcessMessage(){
		try{
			mMongoClient.close();
			mBusConnection.close();
			this.setStopFlag(true);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
