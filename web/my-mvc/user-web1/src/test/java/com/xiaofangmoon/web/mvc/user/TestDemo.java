package com.xiaofangmoon.web.mvc.user;

import com.xiaofangmoon.web.mvc.controller.Controller;
import org.junit.Test;

import java.sql.Driver;
import java.util.ServiceLoader;

/**
 * @author: xiaofang
 * @createTime: 2021/03/03 22:29
 * @description:
 */
public class TestDemo {

    @Test
    public void testConn() {
        ServiceLoader<Driver> load = ServiceLoader.load(Driver.class);
        for (Driver driver : load) {
            System.out.println(driver);
        }
        System.out.println(load);
        ServiceLoader<Controller> loads = ServiceLoader.load(Controller.class);
        for (Controller driver : loads) {
            System.out.println(driver);
        }
    }
}
