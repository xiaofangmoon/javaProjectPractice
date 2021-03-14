package com.xiaofangmoon.web.mvc.user.logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author xiaofang
 * @createTime 2021/03/12 10:58
 * @description
 */
public class LoggerTest {
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger("org.geektimes");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("META-INF/logging.properties")) {
            LogManager logManager = LogManager.getLogManager();
            // 读取日志配置
            logManager.readConfiguration(inputStream);
        }

        System.out.println("***");
        System.out.println(logger);

        System.out.println("level : " + logger.getLevel());
        logger.warning("10086");
        logger.log(Level.INFO, "Hello,World");
        System.out.println("***");
        for (Handler handler : logger.getHandlers()) {
            System.out.println(handler);
        }

        System.out.println("****");
    }
}
