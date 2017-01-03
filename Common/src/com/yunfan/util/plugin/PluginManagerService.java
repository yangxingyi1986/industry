package com.yunfan.util.plugin;

import java.util.concurrent.ExecutorService;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import com.yunfan.util.configure.ConfigureManager;
import com.yunfan.util.configure.PluginConfigure;
import com.yunfan.util.thread.ThreadBuilder;

public class PluginManagerService extends NotificationBroadcasterSupport implements PluginManagerServiceMBean, IPluginObserver{
	
	private ConfigureManager mConfigureManager;
	private boolean mInitFlag = false;
	private ExecutorService mPool;
	private PluginsNotifyStatus mStartStatus;
	
	@Override
	public synchronized void notifyPluginInfo(PluginNotification pPluginNotification) {
		if ("start".equals(pPluginNotification.getName())){
			mStartStatus.notifyPluginStatus(pPluginNotification.getFrom());
			//判断是否全部的Start Notify都已经收到
			if (mStartStatus.isComplete()){
				Notification notification = new Notification(pPluginNotification.getName(), this, 
						0, System.currentTimeMillis(), pPluginNotification.getNotificationJson());
				sendNotification(notification);
			}
		}
	}

	public void dispatchCmdToAgent(String resName, String jsonBean) {
		System.out.println("resName " + resName);
	}
	
	public boolean start(){
		System.out.println("initialize");
		if (!mInitFlag){
			mStartStatus = new PluginsNotifyStatus();
			mPool = ThreadBuilder.getCachedThreadPool();
			mConfigureManager = ConfigureManager.getInstance(getPluginConfigureClazz());
			PluginManager pluginManager = PluginManager.getInstance();
			pluginManager.setPluginObserver(this);
			mConfigureManager.attach(pluginManager);
			mConfigureManager.startConfigureMonitor(mPool, getConfigurePath());
		}
		mInitFlag = false;
		return true;
	}
	
	public boolean stop(){
		System.out.println("finalize");
		mConfigureManager.stopConfigureMonitor();
		mConfigureManager.detach(PluginManager.getInstance());
		return true;
	}
	
	protected Class<? extends PluginConfigure> getPluginConfigureClazz(){
		return PluginConfigure.class;
	}
	
	protected String getConfigurePath(){
		return null;
	}
}
