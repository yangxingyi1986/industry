package com.yunfan.agent;

import java.rmi.Naming;
import com.yunfan.agent.Agent;
import com.yunfan.agent.proxy.util.Constant;
import com.yunfan.util.command.ICmd;
import com.yunfan.util.plugin.PluginNotification;

public class DemoAgentProxy extends Agent{

	@Override
	public ICmd getCmd(String pCmdName) {

		if ("Start".equals(pCmdName)){
			return new DemoProxyStartCmd();
		}else if("Stop".equals(pCmdName)){
			return new DemoProxyStopCmd();
		}else{
			return null;
		}
		
	}
	
	/*
     * 启动AgentDemo并接收消息
     * */
	public void prepareReceiveMessage(){
		try {
			Runtime runtime = Runtime.getRuntime();
			runtime.exec("java -jar " + Constant.AGENT_DEMO_FILE_PATH);
			Thread.sleep(1000);
			System.out.println("java -jar " + Constant.AGENT_DEMO_FILE_PATH);
			IDemoAgentService demoAgentService = (IDemoAgentService)Naming.lookup(Constant.AGENT_DEMO_SERVICE_ADDRESS);
			demoAgentService.setYunfanbus("Demo1", "rmiDemo");
			demoAgentService.setPluginFullName(this.getPluginConfigure().getPluginFullName());
			
			PluginNotification pluginNotification = new PluginNotification();
			pluginNotification.setFrom(this);
			pluginNotification.setName("start");
			pluginNotification.setNotificationJson("");
			notify(pluginNotification);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*
     * 停止AgentDemo
     * */
	public void stopProcessMessage(){
		try{
			IDemoAgentService demoAgentService = (IDemoAgentService)Naming.lookup(Constant.AGENT_DEMO_SERVICE_ADDRESS);
			demoAgentService.stop();
			this.setStopFlag(true);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}

