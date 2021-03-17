package com.xiaofangmoon.web.mvc.management.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author xiaofang
 * @createTime 2021/03/14 21:40
 * @description
 */
public interface ServletConfigurationMXBean {
    String getName();

    void setName(String name);

}
