package com.xiaofangmoon.web.mvc.user.management.notice;

public interface ServerConfigureMBean {
    public void setPort(int port);

    public int getPort();

    public void setHost(String host);

    public String getHost();
}