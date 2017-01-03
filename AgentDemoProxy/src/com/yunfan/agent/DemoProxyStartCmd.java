package com.yunfan.agent;

import com.yunfan.agent.AgentCmd;

public class DemoProxyStartCmd extends AgentCmd{
	@Override
	public void execute() {
		
		// TODO Auto-generated method stub
		try {
			System.out.println("test");
			DemoAgentProxy myAgent = (DemoAgentProxy)this.getPlugin();
			myAgent.prepareReceiveMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
