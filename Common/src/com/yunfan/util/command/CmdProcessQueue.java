package com.yunfan.util.command;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class CmdProcessQueue {
	Queue<ICmd> mQueue;
	public CmdProcessQueue() {
		super();
		mQueue = new LinkedBlockingQueue<ICmd>();
	}

	public void enqueue(ICmd pCmd){
		mQueue.add(pCmd);
	}
	
	public ICmd dequeue(){
		return mQueue.poll();
	}
}
