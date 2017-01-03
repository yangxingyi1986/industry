package com.yunfan.util.bus;

import java.io.Serializable;

import com.yunfan.util.model.JsonBean;

/**
 * 发送消息的通道的接口
 * @author yangxingyi
 *
 */
public interface SendChannel extends Serializable {
	
	/**
	 * 通过消息信道发送消息
	 * @param pJsonBean
	 * @param pRoutingKey
	 */
	public void sendObject(JsonBean pJsonBean, String pRoutingKey);
	
	/**
	 * 关闭当前链接的通道
	 */
	public void close();
}
