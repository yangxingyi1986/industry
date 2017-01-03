package com.yunfan.util.plugin;

import java.util.concurrent.ExecutorService;

import com.yunfan.util.bus.YunfanBus;
import com.yunfan.util.command.CmdProcessQueue;
import com.yunfan.util.command.ICmd;
import com.yunfan.util.configure.ConfigureObserver;
import com.yunfan.util.configure.PluginConfigure;

public interface IPlugin {
	public void pushCmd(ICmd pComd);
	public void setThreadPool(ExecutorService pPool);	
	public void setBus(YunfanBus pYunfanBus);
	public void setConfigure(PluginConfigure pAgentConfigure);
	public ICmd getCmd(String pCmdName);
	public CmdProcessQueue getmCmdProcessQueue() ;
	public YunfanBus getmYunfanBus();	
	public PluginConfigure getPluginConfigure();
	public boolean isStopFlag();
	public void setStopFlag(boolean stopFlag);
	public void attach(IPluginObserver pConfigureDetectorObserver);
	public void detach(IPluginObserver pConfigureDetectorObserver);
	public void notify(PluginNotification pPluginNotification);
}
