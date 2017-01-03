package com.yunfan.agent;
import com.yunfan.agent.AgentCmd;

public class DemoProxyStopCmd extends AgentCmd {
	@Override
	public void execute() {
		
		try{
			
			DemoAgentProxy myAgent = (DemoAgentProxy)this.getPlugin();
			myAgent.stopProcessMessage();
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
		System.exit(0);
		// TODO Auto-generated method stub
		super.execute();
	}
}
