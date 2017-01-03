package com.yunfan.agent;

import com.yunfan.util.command.ICmd;
import com.yunfan.util.plugin.IPlugin;

public class AgentCmd implements ICmd {
	Agent mAgent; 
	
	@Override
	public void execute() {
	}
	
	public void setPlugin(IPlugin pPlugin){
		mAgent = (Agent)pPlugin;
	}
	
	public IPlugin getPlugin(){
		return mAgent;
	}
	
}
