package com.xiaofangmoon.web.mvc.user.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author xiaofang
 * @createTime 2021/03/12 10:56
 * @description
 */
public class UserWebLoggingConfiguration1 {

    public UserWebLoggingConfiguration1() throws Exception {
        System.out.println("UserWebLoggingConfiguration1");
        // 通过代码的方式调整日志级别
        Logger logger = Logger.getLogger("org.geektimes");
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setEncoding("UTF-8");
        consoleHandler.setLevel(Level.INFO);
        logger.addHandler(consoleHandler);
    }

}
