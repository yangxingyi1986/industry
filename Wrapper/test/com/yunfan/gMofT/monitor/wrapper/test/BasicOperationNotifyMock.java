package com.yunfan.gMofT.monitor.wrapper.test;

import com.yunfan.gMofT.monitor.wrapper.IBasicOperationObserver;

/**
 * 通知模拟的基本操作
 * @author yunfan
 *
 */
public class BasicOperationNotifyMock implements IBasicOperationObserver{
	public boolean mAgentStart;
	public boolean mTransferStart;
	public boolean mPublisherStart;
	
	@Override
	public void notifyAgentStart(boolean result) {
		mAgentStart = result;
		// TODO Auto-generated method stub
	}

	@Override
	public void notifyTransferStart(boolean result) {
		mTransferStart = result;
		// TODO Auto-generated method stub
	}

	@Override
	public void notifyPublisherStart(boolean result) {
		mPublisherStart = result;
		// TODO Auto-generated method stub
	}
	
}
