package com.xiaofangmoon.web.mvc.user.management.mbean;

import com.xiaofangmoon.web.mvc.user.domain.User;

/**
 * @author xiaofang
 * @createTime 2021/03/14 14:30
 * @description
 */
public class UserManager implements UserManagerMBean {

    private User user;


    public UserManager(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        return user.equals(o);
    }

    @Override
    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public Long getId() {
        return user.getId();
    }

    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getPhone() {
        return user.getPhone();
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }

    @Override
    public void setEmail(String email) {
        user.setEmail(email);
    }

    @Override
    public void setId(Long id) {
        user.setId(id);
    }

    @Override
    public void setName(String name) {
        user.setName(name);
    }

    @Override
    public void setPassword(String password) {
        user.setPassword(password);
    }

    @Override
    public void setPhone(String phone) {
        user.setPhone(phone);
    }

    @Override
    public String toString() {
        return user.toString();
    }

}
