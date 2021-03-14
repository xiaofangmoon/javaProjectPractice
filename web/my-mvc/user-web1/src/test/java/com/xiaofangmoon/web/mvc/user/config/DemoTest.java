package com.xiaofangmoon.web.mvc.user.config;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import static org.junit.Assert.*;

/**
 * @author xiaofang
 * @createTime 2021/03/11 20:25
 * @description
 */
public class DemoTest {


    @Test
    public void testDev() {
        System.out.println(JSON.toJSONString(System.getenv()));
    }

    @Test
    public void testProperties() {
        System.out.println(JSON.toJSONString(System.getProperties()));
    }

    @Test
    public void testPreferences() throws BackingStoreException {
        Preferences userPreference = Preferences.userRoot();
        userPreference.put("xiaofang-go", "xiaofang-go");
        userPreference.flush();
        System.out.println(userPreference.get("xiaofang-go", null));


    }

}