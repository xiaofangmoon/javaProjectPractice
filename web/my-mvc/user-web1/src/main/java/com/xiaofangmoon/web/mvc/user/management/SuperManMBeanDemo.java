package com.xiaofangmoon.web.mvc.user.management;

import com.xiaofangmoon.web.mvc.user.domain.SuperMan;
import com.xiaofangmoon.web.mvc.user.management.mbean.SuperManManager;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @author xiaofang
 * @createTime 2021/03/14 14:48
 * @description
 */
public class SuperManMBeanDemo {
    public static void main(String[] args) throws Exception {
        // 获取平台 MBean Server
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // 为 UserMXBean 定义 ObjectName
        ObjectName objectName = new ObjectName("com.xiaofangmoon.web.mvc.user.domain.SuperMan:type=Superman");
        // 创建 UserMBean 实例
        SuperMan superMan = new SuperMan();

        mBeanServer.registerMBean(createMBean(superMan), objectName);
        while (true) {
            Thread.sleep(2000);
            System.out.println(superMan);
        }
    }

    private static Object createMBean(SuperMan info) throws Exception {
        return new SuperManManager(info);
    }
}
