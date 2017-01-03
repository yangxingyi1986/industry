package com.yunfan.util.plugin.test.pluginMock1;

import com.yunfan.util.command.ICmd;
import com.yunfan.util.plugin.IPlugin;

public class StartCmd implements ICmd {
	
	@Override
	public void execute() {
		System.out.println("Mock1.StartCmd.execute");
		// TODO Auto-generated method stub
	}

	@Override
	public void setPlugin(IPlugin pPlugin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPlugin getPlugin() {
		// TODO Auto-generated method stub
		return null;
	}
	
}