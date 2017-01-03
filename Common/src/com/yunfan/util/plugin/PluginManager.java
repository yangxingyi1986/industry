package com.yunfan.util.plugin;

import java.net.URL;
import java.net.URLClassLoader;

import com.yunfan.util.bus.YunfanBus;
import com.yunfan.util.command.ICmd;
import com.yunfan.util.configure.ConfigureObserver;
import com.yunfan.util.configure.PluginConfigure;
import com.yunfan.util.thread.ThreadBuilder;

public class PluginManager implements ConfigureObserver{
	private PluginContainer mPluginContainer;
	private IPluginObserver mPluginObserver; 
	
	private static PluginManager mPluginManager = new PluginManager();
	
	public static PluginManager getInstance(){
		return mPluginManager;
	}
	
	protected PluginManager() {
		super();
		mPluginContainer = PluginContainer.getInstance();
		// TODO Auto-generated constructor stub
	}

	public void setPluginObserver(IPluginObserver mPluginObserver) {
		this.mPluginObserver = mPluginObserver;
	}

	/*
	 * Plugin增加通知
	 */
	public void notifyAddConfigure(PluginConfigure pPluginConfigure) {
		IPlugin plugin = getPluginInstance(pPluginConfigure);
		mPluginContainer.add(pPluginConfigure.getPluginFullName(), plugin);
		initPlugin(plugin, pPluginConfigure);
		startPlugin(plugin);
	}	

	/*
	 * Plugin删除通知
	 */
	public void notifyDeleteConfigure(PluginConfigure pPluginConfigure) {
		IPlugin plugin = mPluginContainer.delete(pPluginConfigure.getPluginFullName());
		stopPlugin(plugin);
	}	

	/*
	 * Plugin变化通知
	 */
	public void notifyChangeConfigure(PluginConfigure pPluginConfigure) {
		IPlugin deletePlugin =  mPluginContainer.delete(pPluginConfigure.getPluginFullName());
		stopPlugin(deletePlugin);
		IPlugin addPlugin = getPluginInstance(pPluginConfigure);
		mPluginContainer.add(pPluginConfigure.getPluginFullName(), addPlugin);
		initPlugin(addPlugin, pPluginConfigure);
		startPlugin(addPlugin);
	}

	/*
	 * Plugin初始化
	 */
	private void initPlugin(IPlugin pPlugin, PluginConfigure pPluginConfigure){
		pPlugin.setConfigure(pPluginConfigure);
		pPlugin.setBus(new YunfanBus());
		pPlugin.setThreadPool(ThreadBuilder.getCachedThreadPool());
		pPlugin.attach(mPluginObserver);
	}

	/*
	 * Plugin开始
	 */
	private void startPlugin(IPlugin pPlugin){
		Plugin plugin = (Plugin)pPlugin;
		ThreadBuilder.getCachedThreadPool().execute(plugin);
		ICmd cmd = pPlugin.getCmd("Start");
		pPlugin.pushCmd(cmd);
	}
	
	/*
	 * Plugin停止
	 */
	private void stopPlugin(IPlugin pPlugin){
		ICmd cmd = pPlugin.getCmd("Stop");
		pPlugin.pushCmd(cmd);
	}

	
	/*
	 * Plugin实例动态加载
	 */
	private IPlugin getPluginInstance(PluginConfigure pPluginConfigure) {
		try{
	        URL base = this.getClass().getResource("");
//	        System.out.println(base);
//	        String path = new File(base.getFile(), "../../../../../" + "plugin").getCanonicalPath();
			URL url = new URL("file:" + pPluginConfigure.getPluginFilePath());  
			System.out.println(url);
			
            @SuppressWarnings("resource")
			URLClassLoader urlClassLoader = new URLClassLoader(new URL[] {url}, Thread.currentThread().getContextClassLoader());  
            Class<?> clazz = urlClassLoader.loadClass(pPluginConfigure.getPluginClass());
            IPlugin plugin = (IPlugin)clazz.newInstance();
            return plugin;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
}
