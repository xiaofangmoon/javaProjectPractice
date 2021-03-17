package com.xiaofangmoon.web.mvc.user.management;

import com.alibaba.fastjson.JSON;
import com.xiaofangmoon.web.mvc.user.management.mxbean.Book;
import com.xiaofangmoon.web.mvc.user.management.mxbean.Libraries;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaofang
 * @createTime 2021/03/14 15:10
 * @description
 */
public class LibraryMXBeanDemo {
    public static void main(String[] args) throws Exception {
        testMXBean1();
    }

    private static void testMXBean1() throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, InterruptedException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName mxbeanName = new ObjectName("com.xiaofangmoon.web.mvc.user.management.mxbean.Libraries:type=Libraries");
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(1, "Thinking in Java", 99, "Bruce Eckel"));
        bookList.add(new Book(2, "Effective Java", 88, "Joshua Bloch"));
//        bookList.add(new Book(3, "Core java", 42, "Cay S. Horstmann"));
        Libraries libraries = new Libraries(bookList);
        mbs.registerMBean(libraries, mxbeanName);
        System.out.println("Waiting forever...");
        while (true) {
            Thread.sleep(2000);
            System.out.println(JSON.toJSONString(libraries));
        }
    }

    private static void testMXBean() throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, InterruptedException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName mxbeanName = new ObjectName("com.jmx.demo3:type=Libraries");
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(1, "Thinking in Java", 99, "Bruce Eckel"));
        bookList.add(new Book(2, "Effective Java", 88, "Joshua Bloch"));
//        bookList.add(new Book(3, "Core java", 42, "Cay S. Horstmann"));
        Libraries libraries = new Libraries(bookList);
        mbs.registerMBean(libraries, mxbeanName);
        System.out.println("Waiting forever...");

        while (true) {
            Thread.sleep(2000);
            System.out.println(JSON.toJSONString(libraries));
        }
    }
}
