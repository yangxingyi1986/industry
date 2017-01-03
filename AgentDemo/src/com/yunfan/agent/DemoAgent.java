package com.yunfan.agent;

import java.rmi.Naming;
//import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;

import com.yunfan.agent.demo.Constant;

public class DemoAgent{
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		try {
			IDemoAgentService demoAgentService = new DemoAgentServiceImpl();
			
			//注册通讯端口
			LocateRegistry.createRegistry(Constant.AGENT_DEMO_SERVICE_PORT);

			//注册通讯路径
			Naming.rebind(Constant.AGENT_DEMO_SERVICE_ADDRESS, demoAgentService);
			System.out.println("Demo Agent Service Start!");		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

