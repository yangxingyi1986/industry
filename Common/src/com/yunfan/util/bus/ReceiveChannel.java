package com.yunfan.util.bus;

/**
 * 接受消息的通道的接口
 * @author yangxingyi
 *
 */
public interface ReceiveChannel {

	
	/*
	 * 回调接收者函数
	 */
	public void setBusReceiver(final BusReceiver busReceiver);
	
	/**
	 * 关闭通道方法
	 */
	public void close();
}
