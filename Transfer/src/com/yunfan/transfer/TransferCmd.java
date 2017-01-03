package com.yunfan.transfer;
import com.yunfan.util.command.ICmd;
import com.yunfan.util.plugin.IPlugin;

public class TransferCmd implements ICmd {

	Transfer mTransfer;
	
	@Override
	public void execute() {
	}

	public void setPlugin(IPlugin pPlugin){
		mTransfer = (Transfer)pPlugin;
	}
	public IPlugin getPlugin(){
		return mTransfer;
	}

}
