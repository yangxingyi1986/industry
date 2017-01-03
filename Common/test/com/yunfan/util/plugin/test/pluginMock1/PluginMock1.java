package com.yunfan.util.plugin.test.pluginMock1;

import com.yunfan.util.command.ICmd;
import com.yunfan.util.plugin.Plugin;

public class PluginMock1 extends Plugin{
	@Override
	public ICmd getCmd(String pCmdName) {

		if ("Start".equals(pCmdName)){
			return new StartCmd();
		}else if("Stop".equals(pCmdName)){
			return new StopCmd();
		}else{
			return null;
		}
	}
	
}
