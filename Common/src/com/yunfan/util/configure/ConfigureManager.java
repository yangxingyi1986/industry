package com.yunfan.util.configure;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ConfigureManager implements ConfigureDetectorObserver{

	ConfigureFileDetector mDetector;
	ConfigureRepo mConfigureRepo;
	List<ConfigureObserver> mObserverList;
	Class<? extends PluginConfigure> mConfigureClazz;
	String mConfigurePath;
	
	private static ConfigureManager mConfigureManager;
	
	public static ConfigureManager getInstance(Class<? extends PluginConfigure> pConfigureClazz) {
        if (mConfigureManager == null) {  
            synchronized (ConfigureManager.class) {  
               if (mConfigureManager == null) {  
            	   mConfigureManager = new ConfigureManager(pConfigureClazz); 
               }
            }
        }
        return mConfigureManager; 
    }
	
	private ConfigureManager(Class<? extends PluginConfigure> pConfigureClazz) {
		super();
		mDetector = new ConfigureFileDetector();
		mConfigureRepo = new ConfigureRepo();
		mObserverList = new ArrayList<ConfigureObserver>();
		mConfigureClazz = pConfigureClazz;
		mConfigurePath = "";
		// TODO Auto-generated constructor stub
	}

	/* 开始配置文件监控
	 */
	public boolean startConfigureMonitor(ExecutorService pPool, String pPath){
		try{
			mConfigurePath = pPath;
			mDetector.initialize(pPath);
			mDetector.attach(this);
			pPool.execute(mDetector);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return true;
	}

	/* 停止配置文件监控
	 * */
	public boolean stopConfigureMonitor(){
		List<PluginConfigure> configureList = mConfigureRepo.clear();
		for (PluginConfigure conf : configureList){
			notifyFileChanged(conf, ConfigureFileDetector.FileChangeStatus.FILE_DELETE);
		}
		mDetector.detach(this);
		return true;
	}

	/* 通知配置文件发生变化
	 * */
	public void notifyChangeFileName(String pFileName, ConfigureFileDetector.FileChangeStatus pFileChangeStatus){
		try{
			ConfigureFile configureFile = new ConfigureFile();
			PluginConfigure configure  = null;
			if (ConfigureFileDetector.FileChangeStatus.FILE_DELETE != pFileChangeStatus){
				configure = configureFile.getConfigure(mConfigurePath + pFileName, mConfigureClazz);
			}
			
			switch (pFileChangeStatus){
				case FILE_CREATE:
					mConfigureRepo.add(configure, pFileName);
					notifyFileChanged(configure, ConfigureFileDetector.FileChangeStatus.FILE_CREATE);
					break;
				case FILE_MODIFY:
					ConfigureRepo.ChangeState changeState = mConfigureRepo.isChange(configure, pFileName) ;
					if (changeState == ConfigureRepo.ChangeState.NO_CHANGE){
					}else if (changeState == ConfigureRepo.ChangeState.CHANGE){
						notifyFileChanged(configure, ConfigureFileDetector.FileChangeStatus.FILE_MODIFY);
						mConfigureRepo.replace(configure, pFileName);
					}else{
						notifyFileChanged(configure, ConfigureFileDetector.FileChangeStatus.FILE_DELETE);
						notifyFileChanged(configure, ConfigureFileDetector.FileChangeStatus.FILE_CREATE);
						mConfigureRepo.replace(configure, pFileName);
					}
					break;
				case FILE_DELETE:
					configure = mConfigureRepo.remove(pFileName);
					notifyFileChanged(configure, ConfigureFileDetector.FileChangeStatus.FILE_DELETE);
					break;
				default:
					break;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*  @pattern 观察者模式
	 * */
	public void attach(ConfigureObserver pConfigureDetectorObserver){
		 mObserverList.add(pConfigureDetectorObserver);
	}
	
	/* @pattern 观察者模式
	 * */
	public void detach(ConfigureObserver pConfigureDetectorObserver){
		mObserverList.remove(pConfigureDetectorObserver);
	}
	    
	/* @pattern  观察者模式
	 * 通知观察者
	 * */
	public void notifyFileChanged(PluginConfigure pConfigure, ConfigureFileDetector.FileChangeStatus pFileChangeStatus){
		for(ConfigureObserver configureObserver : mObserverList){
			switch (pFileChangeStatus){
			case FILE_CREATE:
				configureObserver.notifyAddConfigure(pConfigure);
				break;
			case FILE_MODIFY:
				configureObserver.notifyChangeConfigure(pConfigure);
				break;
			case FILE_DELETE:
				configureObserver.notifyDeleteConfigure(pConfigure);
				break;
			default:
				break;
			}
		}
	}
	 
}
