package com.yunfan.transfer;

import com.yunfan.transfer.TransferCmd;

public class DemoStopCmd extends TransferCmd{
	
	@Override
	public void execute() {
		
		// TODO Auto-generated method stub
		try {
			
			DemoTransfer myTransfer = (DemoTransfer)this.getPlugin();
			myTransfer.stopProcessMessage();
			
			System.out.println("Demo Transfer Service Stop!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}