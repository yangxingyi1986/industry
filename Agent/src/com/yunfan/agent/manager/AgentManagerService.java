package com.yunfan.agent.manager;

import com.yunfan.agent.AgentConfigure;
import com.yunfan.agent.util.Constant;
import com.yunfan.util.configure.PluginConfigure;
import com.yunfan.util.plugin.PluginManagerService;

public class AgentManagerService extends PluginManagerService {

	@Override
	protected Class<? extends PluginConfigure> getPluginConfigureClazz() {
		return AgentConfigure.class;
	}

	@Override
	protected String getConfigurePath() {
		return Constant.AGENT_CONFIGURE_PATH;
	}

}
