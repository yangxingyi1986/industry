package com.yunfan.source;

import java.rmi.Naming;
import java.util.Date;
import java.util.Random;

import com.yunfan.agent.IDemoAgentService;
import com.yunfan.agent.climate.IClimateAgentService;

public class DemoSource {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer concurentCount = 0;//Integer.parseInt(args[0]);
		System.setProperty("java.rmi.server.hostname","192.168.1.121");
		System.setProperty("java.rmi.server.codebase", "file:/C:/Users/Administrator/Desktop/ClimateAgent.jar");
		try{
			//调用远程对象，注意RMI路径与接口
			IClimateAgentService demoAgentService = (IClimateAgentService)Naming.lookup("rmi://192.168.1.121:5555/IClimateAgentService");
			Date beginDate = new Date();
			concurentCount = 1000000;
			Random random = new Random(10000);
			for(Integer i = 0; i < concurentCount; i++){
				Date currentDate = new Date();
				String message = random.nextInt() + ";" + currentDate.getTime();
				demoAgentService.sendMessage(i.toString(), message);
				//demoAgentService.sendMessage(i.toString(), "h");
				if (i%100== 0) {
					System.out.println("SourceMessageID = " + i);
				}
				//Thread.sleep(100);
			}
			Date endDate = new Date();
			long beginTime = beginDate.getTime(); 
			long endTime = endDate.getTime(); 
			long betweenS = (long)((endTime - beginTime) / 1000); 
			System.out.println("beginDate = " + beginDate);
			System.out.println("endDate = " + endDate);
			System.out.println("timeSpan =" + betweenS);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
