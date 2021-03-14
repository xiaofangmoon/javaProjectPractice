package com.xiaofangmoon.web.mvc.user.management.notice;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * @author xiaofang
 */
public class ServerStartup {
    public static void main(String[] args) throws Exception {
        // 创建MBeanServer   
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        // 新建MBean ObjectName, 在MBeanServer里标识注册的MBean   
        ObjectName name = new ObjectName("com.xiaofangmoon.web.mvc.user.management.notice.ServerConfigure:type=ServerConfigure");
        // 创建MBean   
        ServerConfigure mbean = new ServerConfigure();
        // 在MBeanServer里注册MBean, 标识为ObjectName(com.dxz.mbean.server:type=ServerConfigure)   
        mbs.registerMBean(mbean, name);
        // 自定义观察者   
        ServerConfigureNotificationListener listener = new ServerConfigureNotificationListener();
        // 加入MBeanServer   
        mbs.addNotificationListener(name, listener, null, null);
        Thread.sleep(Long.MAX_VALUE);
    }
}