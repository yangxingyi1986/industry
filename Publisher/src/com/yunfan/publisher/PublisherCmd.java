package com.yunfan.publisher;

import com.yunfan.util.command.ICmd;
import com.yunfan.util.plugin.IPlugin;

public class PublisherCmd implements ICmd {
	Publisher mPublisher;
	
	@Override
	public void execute() {
	}
	
	public void setPlugin(IPlugin pPlugin){
		mPublisher = (Publisher)pPlugin;
	}
	
	public IPlugin getPlugin(){
		return mPublisher;
	}
	
}
