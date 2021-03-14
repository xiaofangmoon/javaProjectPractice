package com.xiaofangmoon.web.mvc.user.web.listen;

import com.xiaofangmoon.web.mvc.user.context.ComponentContext;
import org.apache.commons.configuration.JNDIConfiguration;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Hashtable;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * 测试用途
 */
@Deprecated
public class TestingListener implements ServletContextListener {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("TestingListener.contextInitialized ");
        testJndiEvn();
        testServletConfig(sce);
//        testJNDIConfig();
    }

    private void testJNDIConfig() {
        System.out.println("testJNDIConfig : ");

        try {
            JNDIConfiguration jndiConfiguration = new JNDIConfiguration("java:comp/env");
            jndiConfiguration.getKeys().forEachRemaining(System.out::println);
            System.out.println("testJNDIConfig end " );
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    private void testServletConfig(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();
        Object attribute = servletContext.getInitParameter("xiaofang-go");
        System.out.println("testServletConfig : " + attribute);
    }

    private void testJndiEvn() {
        ComponentContext instance = ComponentContext.getInstance();
        Object maxValue = instance.getComponent("maxValue");
        System.out.println("testJndiEvn : maxValue= " + maxValue);
    }

    private void testUser(EntityManager entityManager) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
