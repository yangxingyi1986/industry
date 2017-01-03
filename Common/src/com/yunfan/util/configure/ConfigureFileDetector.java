package com.yunfan.util.configure;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class ConfigureFileDetector extends Thread {
	private Path mPath = null;
    private WatchService watchService = null;
    private List<ConfigureDetectorObserver> mObserverList;
    private static Logger sLog = Logger.getLogger( "log.agent" );
    
    public enum FileChangeStatus{ 
    		FILE_CREATE,
    		FILE_DELETE,
    		FILE_MODIFY
    }
    
    public ConfigureFileDetector() {
		super();
		mObserverList = new ArrayList<ConfigureDetectorObserver>();
		// TODO Auto-generated constructor stub
	}

	/**
     * 初始化监控文件
     */
    public void initialize(String pathValue) {
    	//被监测的路径
    	mPath = Paths.get(pathValue); 
    	
        try {
            watchService = FileSystems.getDefault().newWatchService();
            mPath.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY); 
        } catch (IOException e) {
            System.out.println("IOException" + e.getMessage());
        }
        
    }
 
    /**
     * 循环监控文件变化
     */
    @Override
    public void run() {	
    	System.out.println(Thread.currentThread().getName() + "正在执行。。。");
        WatchKey key = null;
        scanConfigureFolder();
        while (true) { 
            try {
                key = watchService.take();
                for (WatchEvent event : key.pollEvents()) {
                	Kind kind = event.kind();
                	String fileFullName =  event.context().toString();
                    if (fileFullName.endsWith(".json")){
                    	 notifyFileChanged(kind, fileFullName);
                    }
                    //Context是文件的FullName
                    System.out.println("Event on " + event.context().toString() + " is " + kind);
                }
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException: " + e.getMessage());
            }
            
            // 重设WatchKey
            boolean valid = key.reset();
            // 如果重设失败，退出监听
            if (!valid) {
                break;
            }
        	
        }
    }
    
    private void scanConfigureFolder(){
    	File[] files = mPath.toFile().listFiles();
    	for(File file: files){
    		notifyFileChanged(java.nio.file.StandardWatchEventKinds.ENTRY_CREATE, file.getName());
    	}
    }
    
    public void attach(ConfigureDetectorObserver pConfigureDetectorObserver){
    	mObserverList.add(pConfigureDetectorObserver);
    }
    
    public void detach(ConfigureDetectorObserver pConfigureDetectorObserver){
    	mObserverList.remove(pConfigureDetectorObserver);
    }
    
    private void notifyFileChanged(Kind pKind, String pFileName){
    	FileChangeStatus fileChangeStatus = FileChangeStatus.FILE_CREATE;
    	for (ConfigureDetectorObserver oberver : mObserverList){
	    	if (pKind.equals(ENTRY_CREATE)) {
	    		fileChangeStatus = FileChangeStatus.FILE_CREATE;
	    	} else if (pKind.equals(ENTRY_DELETE)){
	    		fileChangeStatus = FileChangeStatus.FILE_DELETE;
	    	} else if (pKind.equals(ENTRY_MODIFY)){
	    		fileChangeStatus = FileChangeStatus.FILE_MODIFY;
	    	}
	    	oberver.notifyChangeFileName(pFileName, fileChangeStatus);
    	}
    }
    
}

