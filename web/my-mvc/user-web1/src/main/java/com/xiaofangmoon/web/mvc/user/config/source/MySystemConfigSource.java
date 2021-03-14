package com.xiaofangmoon.web.mvc.user.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author xiaofang
 * @createTime 2021/03/12 02:12
 * @description
 */
public class MySystemConfigSource implements ConfigSource {

    /**
     * Java 系统属性最好通过本地变量保存，使用 Map 保存，尽可能运行期不去调整
     * -Dapplication.name=user-web
     */
    private final Map<String, String> properties;

    public MySystemConfigSource() {
        this.properties = new HashMap<>();
        for (String propertyName : System.getProperties().stringPropertyNames()) {
            this.properties.put(propertyName, System.getProperties().getProperty(propertyName));
        }
    }

    @Override
    public Set<String> getPropertyNames() {
        return properties != null ? properties.keySet() : null;
    }

    @Override
    public String getValue(String propertyName) {
        return properties != null ? properties.get(propertyName) : null;
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
