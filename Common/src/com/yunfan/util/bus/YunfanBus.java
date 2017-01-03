package com.yunfan.util.bus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunfan.util.exception.YunfanException;

/**
 * Factory,依据不同配置获取不同的mq配置信息
 * @author 云帆
 *
 */
public class YunfanBus implements Serializable {
	
	private static final long serialVersionUID = 8832687387609065403L;

	private Logger log = LoggerFactory.getLogger(YunfanBus.class);
	
	private static Properties props = null;
	
	public YunfanBus() {
		super();
		try {
			//仅读取一次就OK了
			if(props == null || props.size() <= 0){
				synchronized (YunfanBus.class) {
					if(props == null || props.size() <= 0){
						System.out.println(" load properties ");
						FileInputStream fis = new FileInputStream("yunfan.properties");
						props = new Properties();
						props.load(fis);
						fis.close();
					}
				}
			}
		} catch (FileNotFoundException e) {
			if(log.isErrorEnabled()){
				log.error(e.getMessage(), e);
			}
		} catch (IOException e) {
			if(log.isErrorEnabled()){
				log.error(e.getMessage(), e);
			}
		}
	}

	public YunfanBusConnection connectToBus(){
		String connClassName = null;
		if(props != null){
			connClassName = props.getProperty("mq");
		}
		if(StringUtils.isEmpty(connClassName)){
			connClassName = "com.yunfan.util.bus.impl.YunfanKafkaBusConnection";
		}
		YunfanBusConnection obj = null;
		try {
			Class<YunfanBusConnection> class1 = (Class<YunfanBusConnection>) Class.forName(connClassName);
			Constructor<YunfanBusConnection> cons = class1.getConstructor(new Class[]{String.class});
			obj = cons.newInstance(props.getProperty("bootstrap.servers"));
		} catch (InstantiationException e) {
			if(log.isErrorEnabled()){
				log.error(e.getMessage(), e);
			}
		} catch (IllegalAccessException e) {
			if(log.isErrorEnabled()){
				log.error(e.getMessage(), e);
			}
		} catch (ClassNotFoundException e) {
			if(log.isErrorEnabled()){
				log.error(e.getMessage(), e);
			}
		} catch (NoSuchMethodException e) {
			if(log.isErrorEnabled()){
				log.error(e.getMessage(), e);
			}
		} catch (SecurityException e) {
			if(log.isErrorEnabled()){
				log.error(e.getMessage(), e);
			}
		} catch (IllegalArgumentException e) {
			if(log.isErrorEnabled()){
				log.error(e.getMessage(), e);
			}
		} catch (InvocationTargetException e) {
			if(log.isErrorEnabled()){
				log.error(e.getMessage(), e);
			}
		}
		if(obj != null && obj instanceof YunfanBusConnection){
			return (YunfanBusConnection) obj;
		}
		throw new YunfanException("","MQ配置异常，请检查yunfan.properties中的mq配置");
	}
	
	public void disconnectToBus(YunfanBusConnection pConnection){
		pConnection.close();
	}
}
