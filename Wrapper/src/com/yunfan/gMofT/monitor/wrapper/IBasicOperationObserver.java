package com.yunfan.gMofT.monitor.wrapper;

/**
 */
public interface IBasicOperationObserver {
	public void notifyAgentStart(boolean result);
	public void notifyTransferStart(boolean result);
	public void notifyPublisherStart(boolean result);
}
