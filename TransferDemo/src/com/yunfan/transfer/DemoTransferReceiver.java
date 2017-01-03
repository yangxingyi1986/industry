package com.yunfan.transfer;

import com.yunfan.util.bus.BusReceiver;
import com.yunfan.util.model.JsonBean;
import com.yunfan.util.model.MessageBean;

public class DemoTransferReceiver extends BusReceiver{
	private IWorker mWorker;
	
	public void setWorker(IWorker pWorker){
		mWorker = pWorker;
	}
	
	@Override
	public void receiveObject(JsonBean pJsonBean) {
		MessageBean message = (MessageBean)pJsonBean;
		mWorker.processMessage(message);
		super.receiveObject(pJsonBean);
	}
	
}
