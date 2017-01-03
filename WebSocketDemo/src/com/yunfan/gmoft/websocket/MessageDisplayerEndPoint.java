package com.yunfan.gmoft.websocket;

public class MessageDisplayerEndPoint {
	
	private static boolean firstFlag = true; 
	
	public static void startListener() {
		try{
			//只创建一次，实现不严谨！！！！
			if (firstFlag){
				firstFlag = false;
				
//				IMessageDisplayer messageDisplayer = new MessageDisplayerImpl();
				//注册通讯端口
//				LocateRegistry.createRegistry(8000);
				//注册通讯路径
//				Naming.rebind("rmi://127.0.0.1:8888/IMessageDisplayer", messageDisplayer);
			
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
