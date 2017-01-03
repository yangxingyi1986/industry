package com.yunfan.transfer;
import com.yunfan.transfer.TransferCmd;

public class DemoStartCmd extends TransferCmd{
	@Override
	public void execute() {
		
		// TODO Auto-generated method stub
		try {
			
			DemoTransfer myTransfer = (DemoTransfer)this.getPlugin();
			myTransfer.prepareProcessMessage();
			
			System.out.println("Demo Transfer Service Start!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
