package com.yunfan.transfer.manager;
import com.yunfan.util.configure.*;
import com.yunfan.util.plugin.PluginManagerService;
import com.yunfan.transfer.TransferConfigure;
import com.yunfan.transfer.util.*;

public class TransferManagerService extends PluginManagerService {

	@Override
	protected Class<? extends PluginConfigure> getPluginConfigureClazz() {
		return TransferConfigure.class;
	}

	@Override
	protected String getConfigurePath() {
		return Constant.TRANSFER_CONFIGURE_PATH;
	}

}
