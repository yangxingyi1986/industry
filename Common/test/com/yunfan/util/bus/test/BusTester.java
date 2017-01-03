package com.yunfan.util.bus.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.yunfan.util.bus.SendChannel;
import com.yunfan.util.bus.YunfanBus;
import com.yunfan.util.bus.YunfanBusConnection;
import com.yunfan.util.bus.test.mock.ReceiverMock;
import com.yunfan.util.configure.ConfigureManager;
import com.yunfan.util.model.MessageBean;

public class BusTester {
	private static YunfanBus yunfanBus = new YunfanBus();
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1S1R() {
		try{
			ReceiverMock receiverMock = new ReceiverMock();
			receiverMock.start();
			Thread.sleep(1000);
			
			YunfanBusConnection con = yunfanBus.connectToBus();
			SendChannel sendChannel = con.createSendChannel("test1S1R");
			MessageBean message = new MessageBean();
			message.setId("test1");
			sendChannel.sendObject(message, "key1");
			
//		ReceiverMock receiverMock = new ReceiverMock();
//		receiverMock.start();
		
//			Thread.sleep(200000);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}
