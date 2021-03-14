package com.xiaofangmoon.web.mvc.user.management;


import com.xiaofangmoon.web.mvc.user.domain.User;
import com.xiaofangmoon.web.mvc.user.management.mbean.UserManager;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class UserMBeanDemo {

    public static void main(String[] args) throws Exception {
        // 获取平台 MBean Server
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // 为 UserMXBean 定义 ObjectName
        ObjectName objectName = new ObjectName("com.xiaofangmoon.web.mvc.user.domain.User:type=User");
        // 创建 UserMBean 实例
        User user = new User();
        mBeanServer.registerMBean(createUserMBean(user), objectName);
        while (true) {
            Thread.sleep(2000);
            System.out.println(user);
        }
    }

    private static Object createUserMBean(User user) throws Exception {
        return new UserManager(user);
    }
}
