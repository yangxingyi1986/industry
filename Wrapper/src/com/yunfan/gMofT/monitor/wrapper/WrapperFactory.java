package com.yunfan.gMofT.monitor.wrapper;

public class WrapperFactory {
	private static final BasicOperation mBasicOperation = new BasicOperation();
	
	public static BasicOperation getBasicOperation(){
		return mBasicOperation;
	}

}
