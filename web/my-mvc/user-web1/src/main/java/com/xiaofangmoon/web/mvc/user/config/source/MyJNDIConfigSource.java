package com.xiaofangmoon.web.mvc.user.config.source;

import org.apache.commons.configuration.JNDIConfiguration;
import org.eclipse.microprofile.config.spi.ConfigSource;

import javax.naming.NamingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author xiaofang
 * @createTime 2021/03/12 02:12
 * @description
 */
public class MyJNDIConfigSource implements ConfigSource {

    /**
     * Java 系统属性最好通过本地变量保存，使用 Map 保存，尽可能运行期不去调整
     * -Dapplication.name=user-web
     */
    private final Map<String, Object> properties;

    public MyJNDIConfigSource() throws NamingException {
        properties = new HashMap<>();
        JNDIConfiguration jndiConfiguration = new JNDIConfiguration("java:comp/env");
        System.out.println("MyJNDIConfigSource : init");
        jndiConfiguration.getKeys().forEachRemaining(key -> properties.put(key, jndiConfiguration.getProperty(key)));

    }

    @Override
    public Set<String> getPropertyNames() {
        return properties != null ? properties.keySet() : null;
    }

    @Override

    public String getValue(String propertyName) {
        return properties != null ?((properties.get(propertyName)  !=null ? String.valueOf(properties.get(propertyName)) : null)) : null;
    }

    @Override
    public String getName() {
        return "System config";
    }

    @Override
    public int getOrdinal() {
        return 1;
    }
}
