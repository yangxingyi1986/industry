package com.yunfan.publisher.manager;
import com.yunfan.util.configure.*;
import com.yunfan.util.plugin.PluginManagerService;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.yunfan.publisher.PublisherConfigure;
import com.yunfan.publisher.util.*;

public class PublisherManagerService extends PluginManagerService {

	@Override
	protected Class<? extends PluginConfigure> getPluginConfigureClazz() {
		return PublisherConfigure.class;
	}

	@Override
	protected String getConfigurePath() {
		
		return Constant.PUBLISHER_CONFIGURE_PATH;
	}

}

