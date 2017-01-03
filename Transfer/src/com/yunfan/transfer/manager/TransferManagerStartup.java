package com.yunfan.transfer.manager;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

public class TransferManagerStartup {

	public static void main(String[] args) {
		
		try {
			// Get the Platform MBean Server
	        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

	        // Construct the ObjectName for the Hello MBean we will register
	        ObjectName mbeanName = new ObjectName("com.yunfan.transfer:type=TransferManager");

	        // Create the Hello World MBean
	        TransferManagerService mbean = new TransferManagerService();

	        // Register the Hello World MBean
	        mbs.registerMBean(mbean, mbeanName);
	        
	        LocateRegistry.createRegistry(6601);
	        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://123.56.252.82:6601/jmxrmi");
	        JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
	        cs.start();
	        // Wait forever
			System.out.println("Service Start!");
	        System.out.println("Waiting for incoming requests...");
	        Thread.sleep(Long.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
