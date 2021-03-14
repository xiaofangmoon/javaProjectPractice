package com.xiaofangmoon.web.mvc.user.management.mxbean;


import com.sun.jdmk.comm.HtmlAdaptorServer;
import com.xiaofangmoon.web.mvc.user.domain.Person;
import com.xiaofangmoon.web.mvc.user.domain.User;
import com.xiaofangmoon.web.mvc.user.management.notice.ServerConfigureNotificationListener;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class UserMXBeanDemo {

    public static void main(String[] args) throws Exception {
        // 获取平台 MBean Server
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // 为 UserMXBean 定义 ObjectName
        ObjectName objectName = new ObjectName("com.xiaofangmoon.web.mvc.user.management.mxbean.UserManagerX:type=UserManagerX");
        // 创建 UserMBean 实例
        User user = new User();
        user.setName("xiafang-go");
        UserManagerMX userManagerX = new UserManagerMX(user);
        userManagerX.setAttribute(new Attribute("id", 1L));
        userManagerX.setAttribute(new Attribute("name", "xiaofang"));
        userManagerX.setAttribute(new Attribute("email", "go"));


        mBeanServer.registerMBean(userManagerX, objectName);

//        // 自定义观察者
//        ServerConfigureNotificationListener listener = new ServerConfigureNotificationListener();
//        // 加入MBeanServer
//        mBeanServer.addNotificationListener(objectName, listener, null, null);



        //开启远程服务访问
        String domainName = "fang";
        ObjectName adapterName = new ObjectName(domainName + ":name=htmladapter,port=8082");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        adapter.start();
        mBeanServer.registerMBean(adapter, adapterName);

        int rmiPort = 1199;
        Registry registry = LocateRegistry.createRegistry(rmiPort);

        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + rmiPort + "/" + domainName);
        JMXConnectorServer jmxConnector = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mBeanServer);
        jmxConnector.start();

        while (true) {
            Thread.sleep(2000);
        }

    }

    private static void testMXBean() throws Exception {
        // 获取平台 MBean Server
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // 为 UserMXBean 定义 ObjectName
        ObjectName objectName = new ObjectName("com.xiaofangmoon.web.mvc.user.domain.User:type=UserX");
        // 创建 UserMBean 实例
        User user = new User();
        Person person = new Person();
        UserManagerX userMXBean = createUserMXBean(user);
        userMXBean.setPerson(person);


        mBeanServer.registerMBean(userMXBean, objectName);
        while (true) {
            Thread.sleep(2000);
            System.out.println(user);
        }
    }

    private static UserManagerX createUserMXBean(User user) throws Exception {
        return new UserManagerX(user);
    }
}
