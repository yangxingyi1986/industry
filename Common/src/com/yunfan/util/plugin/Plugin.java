package com.yunfan.util.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import com.yunfan.util.bus.YunfanBus;
import com.yunfan.util.command.ICmd;
import com.yunfan.util.command.CmdProcessQueue;
import com.yunfan.util.configure.PluginConfigure;

public class Plugin extends Thread implements IPlugin{

	private CmdProcessQueue mCmdProcessQueue;
	protected ExecutorService mExecutorService;
	protected YunfanBus mYunfanBus;
	protected PluginConfigure mPluginConfigure;
	private boolean stopFlag;
	private List<IPluginObserver> mObserverList;
	
	public boolean isStopFlag() {
		return stopFlag;
	}

	public void setStopFlag(boolean stopFlag) {
		this.stopFlag = stopFlag;
	}

	public Plugin() {
		super();
		stopFlag = false;
		mCmdProcessQueue = new CmdProcessQueue();
		mObserverList = new ArrayList<IPluginObserver>();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while (true){
			ICmd cmd = (ICmd)mCmdProcessQueue.dequeue();
			if (null == cmd){
				try{
					Thread.sleep(100);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			} else {
				cmd.execute();
				if (stopFlag){
					break;
				}
			}
		}
	}

	@Override
	public void pushCmd(ICmd pCmd) {
		// TODO Auto-generated method stub
		mCmdProcessQueue.enqueue(pCmd);
		pCmd.setPlugin(this);
	}

	@Override
	public void setThreadPool(ExecutorService pPool) {
		// TODO Auto-generated method stub
		mExecutorService = pPool;
		
	}

	@Override
	public void setBus(YunfanBus pYunfanBus) {
		// TODO Auto-generated method stub
		mYunfanBus = pYunfanBus;
		
	}

	@Override
	public void setConfigure(PluginConfigure pAgentConfigure) {
		// TODO Auto-generated method stub
		mPluginConfigure = pAgentConfigure;
	}

	@Override
	public ICmd getCmd(String pCmdName) {
			// TODO Auto-generated method stub
			//子Agent类返回
	//		if ("Start".equals(pCmdName)){
	//			
	//		}else if("Stop".equals(pCmdName)){
	//			
	//		}else if ..... 
			return null;
	}
	
	public CmdProcessQueue getmCmdProcessQueue() {
		return mCmdProcessQueue;
	}

	public YunfanBus getmYunfanBus() {
		return mYunfanBus;
	}

	public PluginConfigure getPluginConfigure() {
		return mPluginConfigure;
	}
	
	/*  @pattern 观察者模式
	 * */
	public void attach(IPluginObserver pPluginObserver){
		 mObserverList.add(pPluginObserver);
	}
	
	/* @pattern 观察者模式
	 * */
	public void detach(IPluginObserver pPluginObserver){
		mObserverList.remove(pPluginObserver);
	}


	@Override
	public void notify(PluginNotification pPluginNotification) {
		// TODO Auto-generated method stub
		for(IPluginObserver pluginObserver:mObserverList){
			pluginObserver.notifyPluginInfo(pPluginNotification);
		}
	}
	
}