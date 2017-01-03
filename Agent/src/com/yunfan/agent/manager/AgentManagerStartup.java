package com.yunfan.agent.manager;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.log4j.Logger;

public class AgentManagerStartup {

	private static AgentManagerService mBean;
	private static Logger sLog = Logger.getLogger("log.agent");
	
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		try {
			sLog.debug("Agent Start");
			
			// Get the Platform MBean Server
	        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

	        // Construct the ObjectName for the Hello MBean we will register
	        ObjectName mbeanName = new ObjectName("com.yunfan.agent:type=AgentManager");

	        // Create the Hello World MBean
	        mBean = new AgentManagerService();

	        // Register the Hello World MBean
	        mbs.registerMBean(mBean, mbeanName);
	        
			LocateRegistry.createRegistry(6600);
			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://123.56.252.82:6600/jmxrmi");
			JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
			cs.start();
			System.out.println("Service Start!");
	        System.out.println("Waiting for incoming requests...");
	        
	        doShutDownWork();
	        
	        Thread.sleep(Long.MAX_VALUE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void doShutDownWork(){
		Runtime run = Runtime.getRuntime();
		run.addShutdownHook(new Thread(){
			public void run(){
				try{
					mBean.stop();
					Thread.sleep(1000);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
	}
	
}
