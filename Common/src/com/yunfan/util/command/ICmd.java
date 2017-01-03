package com.yunfan.util.command;

import com.yunfan.util.plugin.IPlugin;

public interface ICmd {
	public void execute();
	public void setPlugin(IPlugin pPlugin);
	public IPlugin getPlugin();
}
