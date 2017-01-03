package com.yunfan.util.redis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	private static Logger log = LoggerFactory.getLogger(JedisPoolUtil.class);
	private static String ADDR = "101.200.80.28";
	private static int PORT = 6379;
	private static String AUTH = "admin";
	private static int MAX_ACTIVE = 1024;
	private static int MAX_IDLE = 256;
	private static int MAX_WAIT = 10000;
	private static int TIMEOUT = 10000;
	private static boolean TEST_ON_BORROW = true;
	private static Properties props = null;
	
	private static JedisPool jedisPool = null;
	
	static {
		FileInputStream fis = null;
		try{
			System.out.println(" load properties ");
			fis = new FileInputStream(new File("yunfan.properties"));
			props = new Properties();
			props.load(fis);
			ADDR = props.getProperty("redis.addr");
			PORT = NumberUtils.toInt(props.getProperty("redis.port"), 6379);
			AUTH = props.getProperty("redis.auth");
			MAX_ACTIVE=NumberUtils.toInt("redis.maxactive",1024);
			MAX_IDLE=NumberUtils.toInt("redis.maxidle",256);
			MAX_WAIT=NumberUtils.toInt("redis.maxwait",1000);
			TIMEOUT=NumberUtils.toInt("redis.timeout",1000);
			
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			poolConfig.setMaxIdle(MAX_IDLE);
			poolConfig.setMaxWaitMillis(MAX_WAIT);
			poolConfig.setTestOnBorrow(TEST_ON_BORROW);
			poolConfig.setMaxTotal(MAX_ACTIVE);
			jedisPool = new JedisPool(poolConfig, ADDR, PORT, TIMEOUT, AUTH);
		}catch(Throwable t){
			log.error(t.getMessage(), t);
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static synchronized Jedis getJedis(){
		try{
			if(jedisPool != null){
				Jedis resource = jedisPool.getResource();
				return resource;
			}else{
				return null;
			}
		}catch(Throwable t){
			t.printStackTrace();
			return null;
		}
	}
	
	public static void returnResource(final Jedis jedis){
		if(jedis != null){
			jedisPool.returnResource(jedis);
		}
	}
	
	@Test
	public void test(){
		Jedis jedis = JedisPoolUtil.getJedis();
		System.out.println(jedis.get("foo"));
		
		jedis.set("name", "test");
		jedis.append("name", " is a simple test !");
		System.out.println(jedis.get("name"));
		
		jedis.del("name");
		System.out.println(jedis.get("name"));
		
		Set<String> keys = jedis.keys("*na*");
		for(String tmp : keys){
			System.out.println(tmp);
		}
		jedis.mset("name","test","age","30","mail","live@live.com");
		jedis.incr("age");
		System.out.println(jedis.get("name")+"-" +jedis.get("age")+"-" +jedis.get("mail"));
		jedis.set("name", "simple test");
		System.out.println(jedis.get("name")+"-" +jedis.get("age")+"-" +jedis.get("mail"));
	}
}
