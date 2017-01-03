package com.yunfan.gMofT.monitor.wrapper.test;

import static org.junit.Assert.assertTrue;

import com.yunfan.gMofT.monitor.wrapper.BasicOperation;
import com.yunfan.gMofT.monitor.wrapper.WrapperFactory;

public class BasicOperationTest {

	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}

	public static void main(String[] args) {
		try {
			
			BasicOperation basicOperation = WrapperFactory.getBasicOperation();
			BasicOperationNotifyMock basicOperationOberver = new BasicOperationNotifyMock();
			basicOperation.attach(basicOperationOberver);
			
			assertTrue(basicOperation.startAgent());
			System.out.println(basicOperationOberver.mAgentStart);
//			assertTrue(basicOperationOberver.mAgentStart);
			
			assertTrue(basicOperation.startTransfer());
			System.out.println(basicOperationOberver.mTransferStart);
			Thread.sleep(2000);	//异步调用
//			assertTrue(basicOperationOberver.mTransferStart);
			
			assertTrue(basicOperation.startPublisher());
			System.out.println(basicOperationOberver.mPublisherStart);
			Thread.sleep(2000);	//异步调用
//			assertTrue(basicOperationOberver.mPublisherStart);
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
}
