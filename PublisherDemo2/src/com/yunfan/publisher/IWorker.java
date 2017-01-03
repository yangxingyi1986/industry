package com.yunfan.publisher;

import java.rmi.Naming;
import java.rmi.Remote;

import com.yunfan.gmoft.websocket.IMessageDisplayer;
import com.yunfan.util.bus.YunfanBus;
import com.yunfan.util.model.SampleBean;

public interface IWorker {

	public void processMessage(SampleBean pSample);
	
}
