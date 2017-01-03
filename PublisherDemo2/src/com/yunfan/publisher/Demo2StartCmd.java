package com.yunfan.publisher;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.yunfan.util.bus.ReceiveChannel;
import com.yunfan.util.bus.YunfanBusConnection;
import com.yunfan.util.command.ICmd;
import com.yunfan.util.model.JsonBean.JsonType;

public class Demo2StartCmd extends PublisherCmd implements ICmd {
	@Override
	public void execute() {
		
		// TODO Auto-generated method stub
		try {
			
			Demo2Publisher myPublisher = (Demo2Publisher)this.getPlugin();
			myPublisher.prepareProcessMessage();
			
			System.out.println("Demo Publisher Service Start!");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
