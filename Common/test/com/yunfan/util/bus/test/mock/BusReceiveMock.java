package com.yunfan.util.bus.test.mock;

import com.yunfan.util.bus.BusReceiver;
import com.yunfan.util.model.JsonBean;
import com.yunfan.util.model.MessageBean;

public class BusReceiveMock extends BusReceiver {

	@Override
	public void receiveObject(JsonBean pJsonBean) {
		System.out.println(((MessageBean)pJsonBean).getId());
		// TODO Auto-generated method stub
		super.receiveObject(pJsonBean);
	}

}
