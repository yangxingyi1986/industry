package com.yunfan.util.configure.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.yunfan.agent.util.Constant;
import com.yunfan.util.configure.ConfigureFileDetector;

public class ConfigureFileDetectorTest {

	private static ConfigureFileDetector configureFileDetector =new ConfigureFileDetector();
	
	@Before
	public void setUp() throws Exception {
		configureFileDetector.initialize(Constant.AGENT_CONFIGURE_PATH);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRun() {
		configureFileDetector.run();
		fail("Not yet implemented");
	}
}
