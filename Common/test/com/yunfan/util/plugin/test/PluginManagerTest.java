package com.yunfan.util.plugin.test;


import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.yunfan.util.configure.PluginConfigure;
import com.yunfan.util.plugin.PluginManager;

public class PluginManagerTest {
	
	private static PluginManager pluginManager = PluginManager.getInstance();
	
	@Before
	public void setUp() throws Exception {
		PropertyConfigurator.configure("log4j.properties");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		PluginConfigure pluginConfigure = new PluginConfigure();
		//pluginConfigure.setPluginFilePath("PluginMock1");
		pluginConfigure.setPluginClass("com.yunfan.util.plugin.test.pluginMock1.PluginMock1");
		pluginConfigure.setPluginFullName("PluginMock1");
		pluginManager.notifyAddConfigure(pluginConfigure);
		
		//fail("Not yet implemented");
	}
	
	@Test
	public void testDelete() {
		PluginConfigure pluginConfigure = new PluginConfigure();
		//pluginConfigure.setPluginFilePath("com.yunfan.plugin.manager.test.pluginMock1.PluginMock1");
		pluginConfigure.setPluginClass("com.yunfan.util.plugin.test.pluginMock1.PluginMock1");
		pluginConfigure.setPluginFullName("PluginMock1");
		pluginManager.notifyDeleteConfigure(pluginConfigure);
		
		//fail("Not yet implemented");
	}

	@Test
	public void testChange() {
		PluginConfigure pluginConfigure = new PluginConfigure();
		//pluginConfigure.setPluginFilePath("com.yunfan.plugin.manager.test.pluginMock1.PluginMock1");
		pluginConfigure.setPluginClass("com.yunfan.util.plugin.test.pluginMock1.PluginMock1");
		pluginConfigure.setPluginFullName("PluginMock1");
		pluginManager.notifyChangeConfigure(pluginConfigure);
		
		//fail("Not yet implemented");
	}
}
