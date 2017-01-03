package com.yunfan.util.bus.test.mock;

import java.util.ArrayList;
import java.util.List;

import com.yunfan.util.bus.BusReceiver;
import com.yunfan.util.bus.ReceiveChannel;
import com.yunfan.util.bus.SendChannel;
import com.yunfan.util.bus.YunfanBus;
import com.yunfan.util.bus.YunfanBusConnection;
import com.yunfan.util.model.JsonBean.JsonType;
import com.yunfan.util.model.MessageBean;

public class ReceiverMock extends Thread {
	private static YunfanBus yunfanBus = new YunfanBus();
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		YunfanBusConnection con = yunfanBus.connectToBus();
		List<String> bindingKeyList = new ArrayList<String>();
		bindingKeyList.add("key1");
		ReceiveChannel receiveChannel =  con.createReceiveChannel("test1S1R", bindingKeyList);
		BusReceiveMock busReceiver = new BusReceiveMock();
		busReceiver.setJsonType(JsonType.MESSAGE);
		receiveChannel.setBusReceiver(busReceiver);
		super.run();
	}
	
}
