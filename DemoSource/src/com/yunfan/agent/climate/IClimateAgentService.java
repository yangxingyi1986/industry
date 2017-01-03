package com.yunfan.agent.climate;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 气温消息代理端
 * @author yunfan
 *
 */
public interface IClimateAgentService extends Remote {
	
	/**
	 * 发送消息接口，对于Agent本身来说相当于接收接口，而对于监控的数据源来说就是发送消息的接口
	 * @param messageId
	 * @param message
	 * @throws RemoteException
	 */
	public void sendMessage(String messageId, String message) throws RemoteException;
	
	/**
	 * 初始化业务数据传输总线
	 * @param topic
	 */
//	public void setYunfanbus(String topic) throws RemoteException;
//	
//	/**
//	 * 停止代理端服务，停止接收数据源端的数据接收
//	 */
//	public void stop() throws RemoteException;
//	
//	/**
//	 * 设置插件注册的全名称
//	 * @param pluginFullName
//	 */
//	public void setPluginName(String pluginFullName) throws RemoteException;
}
