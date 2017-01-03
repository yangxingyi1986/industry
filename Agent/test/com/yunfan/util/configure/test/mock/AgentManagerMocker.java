package com.yunfan.util.configure.test.mock;

import com.yunfan.agent.AgentConfigure;
import com.yunfan.util.configure.PluginConfigure;
import com.yunfan.util.plugin.PluginManager;

import static org.junit.Assert.*;

public class AgentManagerMocker extends PluginManager {
	
	public Boolean isNotifyAddAgentCall;
	public Boolean isNotifyDeleteAgentCall;
	public Boolean isNotifyChangeAgentCall;

	@Override
	public void notifyAddConfigure(PluginConfigure pAgentConfigure) {
		isNotifyAddAgentCall  = true;
	}

	@Override
	public void notifyDeleteConfigure(PluginConfigure pAgentConfigure) {
		isNotifyDeleteAgentCall  = true;
	}

	@Override
	public void notifyChangeConfigure(PluginConfigure pAgentConfigure) {
		isNotifyChangeAgentCall  = true;
	}
}
