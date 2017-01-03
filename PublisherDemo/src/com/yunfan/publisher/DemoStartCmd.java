package com.yunfan.publisher;

import com.yunfan.util.command.ICmd;

public class DemoStartCmd extends PublisherCmd implements ICmd {
	@Override
	public void execute() {
		
		// TODO Auto-generated method stub
		try {
			
			
			
			DemoPublisher myPublisher = (DemoPublisher)this.getPlugin();
			myPublisher.prepareProcessMessage();
			
			System.out.println("Demo Publisher Service Start!");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
