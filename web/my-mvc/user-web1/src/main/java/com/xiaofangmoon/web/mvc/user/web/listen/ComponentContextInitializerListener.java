package com.xiaofangmoon.web.mvc.user.web.listen;

import com.xiaofangmoon.web.mvc.user.context.ComponentContext;

import javax.naming.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

/**
 * @author: xiaofang
 * @createTime: 2021/03/07 15:47
 * @description:
 */
public class ComponentContextInitializerListener implements ServletContextListener {
    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ComponentContextInitializerListener");
        this.servletContext = sce.getServletContext();
        ComponentContext componentContext = new ComponentContext();
        componentContext.init(servletContext);
    }

    public void testDemo() {
        Context ctx = null;
        try {
            ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");

            DataSource ds = (DataSource) envCtx.lookup("jdbc/UserPlatformDB");
            //引用数据源
            ds.getConnection();
            System.out.println("数据库初始化成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
