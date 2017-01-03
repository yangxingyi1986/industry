package com.yunfan.util.configure.test;

import static org.junit.Assert.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.yunfan.agent.AgentConfigure;
import com.yunfan.agent.util.Constant;
import com.yunfan.util.configure.ConfigureManager;
import com.yunfan.util.configure.test.mock.AgentManagerMocker;
import com.yunfan.util.thread.ThreadBuilder;

public class AgentConfigureManagerTest {

	private static ConfigureManager configureManager = ConfigureManager.getInstance(AgentConfigure.class);
	
	@Before
	public void setUp() throws Exception {
		PropertyConfigurator.configure("log4j.properties");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStartAgentMon() {
		
		AgentManagerMocker agentManagerMocker = new AgentManagerMocker();
		configureManager.attach(agentManagerMocker);
		ExecutorService pool = ThreadBuilder.getCachedThreadPool();
		boolean result = configureManager.startConfigureMonitor(pool, Constant.AGENT_CONFIGURE_PATH);
		try{
			Thread.sleep(120000);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		pool.shutdown();
		
		assertEquals(true, agentManagerMocker.isNotifyAddAgentCall);
		assertEquals(true, agentManagerMocker.isNotifyDeleteAgentCall);
		assertEquals(true, agentManagerMocker.isNotifyChangeAgentCall);
		assertEquals(true, result);
	}
	
	@Test
	public void testStartAgentMon2() {
		assertEquals(1, 1);
	}

}
