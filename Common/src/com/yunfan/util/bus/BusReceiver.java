package com.yunfan.util.bus;

import com.yunfan.util.model.JsonBean;
import com.yunfan.util.model.JsonBean.JsonType;

public class BusReceiver{
	
	public JsonType mJsonType; 
	
	public JsonType getJsonType() {
		return mJsonType;
	}

	public void setJsonType(JsonType jsonType) {
		mJsonType = jsonType;
	}

	public void receiveObject(JsonBean pJsonBean){
	}
	
}
