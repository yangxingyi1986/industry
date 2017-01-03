package com.yunfan.publisher;

import com.yunfan.util.bus.BusReceiver;
import com.yunfan.util.model.JsonBean;
import com.yunfan.util.model.MessageBean;
import com.yunfan.util.model.SampleBean;

public class Demo2PublisherReceiver extends BusReceiver{
	private IWorker mWorker;
	
	public void setWorker(IWorker pWorker){
		mWorker = pWorker;
	}
	
	@Override
	public void receiveObject(JsonBean pJsonBean) {
		SampleBean sample = (SampleBean)pJsonBean;
		System.out.println("DemoPublisher" + sample.getId());
		mWorker.processMessage(sample);
		// TODO Auto-generated method stub
		super.receiveObject(pJsonBean);
	}
	
}
