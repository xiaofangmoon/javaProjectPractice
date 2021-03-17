package com.xiaofangmoon.web.mvc.management.servlet;

import javax.servlet.http.HttpServlet;

/**
 * @author xiaofang
 */
public class ServletConfiguration implements ServletConfigurationMXBean {
    private HttpServlet httpServlet;

    private String name;

    public ServletConfiguration(HttpServlet httpServlet) {
        this.httpServlet = httpServlet;
        name = "httpServletName";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;

    }
}