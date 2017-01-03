package com.yunfan.source;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.math.NumberUtils;

import com.yunfan.agent.climate.IClimateAgentService;

public class ClimateDemoSource {

	public static void main(String[] args) {
		Integer concurentCount = 10;//Integer.parseInt(args[0]);
		
		try{
			//调用远程对象，注意RMI路径与接口
			System.setProperty("java.rmi.server.hostname","123.56.252.82");
			System.setProperty("java.rmi.server.codebase", "file:/C:/Users/Administrator/Desktop/com/ClimateAgent.jar");
			IClimateAgentService demoAgentService = (IClimateAgentService)Naming.lookup("rmi://123.56.252.82:5555/IClimateAgentService");
			Date beginDate = new Date();
//			concurentCount = Integer.parseInt(args[0]);
			Random noderandom = new Random(20);
			Random climaterandom = new Random(200);
			List<String> city = new ArrayList<String>();
			city.add("大连");
			city.add("沈阳");
			city.add("北京");
			city.add("上海");
			city.add("广州");
			city.add("郑州");
			city.add("天津");
			city.add("南京");
			city.add("哈尔滨");
			city.add("乌鲁木齐");
			city.add("石家庄");
			for(Integer i = 0; i < concurentCount; i++){
				int id = noderandom.nextInt(10);
				StringBuilder message = new StringBuilder();
				message.append("{");
				message.append("id:" + id);
				message.append(",");
				message.append("position:\"" + city.get(id)+"\"");
				message.append(",");
				message.append("c:" + (climaterandom.nextInt(256) - 50));
				message.append("}");
				demoAgentService.sendMessage(i.toString(), message.toString());
				Thread.sleep(50);
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
//		test(null);
	}
	public static void test(final Object test){
		
		System.out.println(test.toString());
	}
		
}