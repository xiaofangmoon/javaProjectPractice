package com.xiaofangmoon.web.mvc.user.management.mbean;

/**
 * @author xiaofang
 * @createTime 2021/03/14 14:29
 * @description
 */
public interface UserManagerMBean {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getPassword();

    void setPassword(String password);

    String getEmail();

    void setEmail(String email);

    String getPhone();

    void setPhone(String phone);
}
