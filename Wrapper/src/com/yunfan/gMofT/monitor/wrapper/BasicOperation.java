package com.yunfan.gMofT.monitor.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import com.yunfan.util.plugin.PluginManagerServiceMBean;

/**
 * 启动调用各个模块的plugin功能
 * @author yangxingyi
 *
 */
public class BasicOperation {
	
	
	List<IBasicOperationObserver> mObserverList;
	
	public BasicOperation() {
		mObserverList = new ArrayList<IBasicOperationObserver>();
	}

	/**
	 * 定义Agent客户端的监听或观察者
     * Inner class that will handle the notifications.
     */
    public static class AgentClientListener implements NotificationListener {
        public void handleNotification(Notification notification, Object handback) {
        	if ("start".equals(notification.getType())){
        		for(IBasicOperationObserver basicOperationOberver : WrapperFactory.getBasicOperation().mObserverList){
        			basicOperationOberver.notifyAgentStart(true);
        		}
        	}
        }
    }

    
	/**
	 * 定义Transfer客户端的监听或观察者
     * Inner class that will handle the notifications.
     */
    public static class TransferClientListener implements NotificationListener {
        public void handleNotification(Notification notification, Object handback) {
        	if ("start".equals(notification.getType())){
        		for(IBasicOperationObserver basicOperationOberver : WrapperFactory.getBasicOperation().mObserverList){
        			basicOperationOberver.notifyTransferStart(true);
        		}
        	}
        }
    }
    
	/**
	 * 定义Publish客户端的监听或观察者
     * Inner class that will handle the notifications.
     */
    public static class PublisherClientListener implements NotificationListener {
        public void handleNotification(Notification notification, Object handback) {
        	if ("start".equals(notification.getType())){
        		for(IBasicOperationObserver basicOperationOberver : WrapperFactory.getBasicOperation().mObserverList){
        			basicOperationOberver.notifyPublisherStart(true);
        		}
        	}
        }
    }
    
	public boolean startAgent(){
		boolean result = false;
		try{
			//定义Agent观察者
			AgentClientListener listener = new AgentClientListener();
			
	        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://123.56.252.82:6600/jmxrmi");
	        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
	        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
	        ObjectName mbeanName = new ObjectName("com.yunfan.agent:type=AgentManager");
	        PluginManagerServiceMBean mbeanProxy = JMX.newMBeanProxy(mbsc, mbeanName, PluginManagerServiceMBean.class, true);
            
	        // 增加Notify的监控
	        mbsc.addNotificationListener(mbeanName, listener, null, null);
	        
	        result = mbeanProxy.start();
	        
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	public boolean startTransfer(){
		boolean result = false;
		try{
			TransferClientListener listener = new TransferClientListener();
			
			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://123.56.252.82:6601/jmxrmi");
	        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
	        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
	        ObjectName mbeanName = new ObjectName("com.yunfan.transfer:type=TransferManager");
	        PluginManagerServiceMBean mbeanProxy = JMX.newMBeanProxy(mbsc, mbeanName, PluginManagerServiceMBean.class, true);

	        // 增加Notify的监控
	        mbsc.addNotificationListener(mbeanName, listener, null, null);
	        
	        result = mbeanProxy.start();
		        
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	public boolean startPublisher(){
		boolean result = false;
		try{
			PublisherClientListener listener = new PublisherClientListener();
			
			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://123.56.252.82:6602/jmxrmi");
	        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
	        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
	        ObjectName mbeanName = new ObjectName("com.yunfan.publisher:type=PublisherManager");
	        PluginManagerServiceMBean mbeanProxy = JMX.newMBeanProxy(mbsc, mbeanName, PluginManagerServiceMBean.class, true);

	        // 增加Notify的监控
	        mbsc.addNotificationListener(mbeanName, listener, null, null);
	        
	        result = mbeanProxy.start();
	        
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	/*  @pattern 观察者模式
	 * */
	public void attach(IBasicOperationObserver pBasicOperationOberver){
		 mObserverList.add(pBasicOperationOberver);
	}
	
	/* @pattern 观察者模式
	 * */
	public void detach(IBasicOperationObserver pBasicOperationOberver){
		mObserverList.remove(pBasicOperationOberver);
	}
}
