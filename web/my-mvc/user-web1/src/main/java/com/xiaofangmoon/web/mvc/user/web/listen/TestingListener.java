package com.xiaofangmoon.web.mvc.user.web.listen;

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Logger;

/**
 * 测试用途
 */
@Deprecated
public class TestingListener implements ServletContextListener {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    private void testUser(EntityManager entityManager) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
