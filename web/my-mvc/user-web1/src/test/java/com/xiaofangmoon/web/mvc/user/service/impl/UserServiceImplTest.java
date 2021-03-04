package com.xiaofangmoon.web.mvc.user.service.impl;


import org.apache.commons.lang3.ClassUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: xiaofang
 * @createTime: 2021/03/03 20:26
 * @description:
 */
public class UserServiceImplTest {
    UserServiceImpl userService;

    @Before
    public void before() {
        userService = new UserServiceImpl();
    }

    @Test
    public void getUserById() {
        userService.getUserById(1L);
    }

    @Test
    public void test() {
        System.out.println(ClassUtils.wrappersToPrimitives(Integer.class));
        System.out.println(ClassUtils.primitiveToWrapper(int.class));
    }
}