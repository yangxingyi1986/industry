package com.yunfan.util.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ThreadBuilder {
	
	private static final ExecutorService mScheduledThreadPool = new ScheduledThreadPoolExecutor(1);
	private static final ExecutorService mCachedThreadPool = Executors.newCachedThreadPool();
	
	public static ExecutorService getCachedThreadPool(){
		return mCachedThreadPool;
	}
	
	public static ExecutorService getScheduledThreadPool(){
		return mScheduledThreadPool;
	}
	
}
