package com.xiaofangmoon.web.mvc.user.config.source;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * @author xiaofang
 * @createTime 2021/03/12 02:12
 * @description
 */
public class MyPropertiesConfigSource implements ConfigSource {


    /**
     * Java 系统属性最好通过本地变量保存，使用 Map 保存，尽可能运行期不去调整
     * -Dapplication.name=user-web
     */
    private final Map<String, String> properties;

    public MyPropertiesConfigSource() {
        this.properties = new HashMap<>();
        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();
        try {
//            propertiesConfiguration.load(this.getClass().getResourceAsStream("/META-INF/custom-config.properties"));
            Properties prs = new Properties();
            prs.load(this.getClass().getResourceAsStream("/META-INF/custom-config.properties"));
            prs.forEach(new BiConsumer<Object, Object>() {
                @Override
                public void accept(Object o, Object o2) {
                    properties.put(o.toString(), o2.toString());
                }
            });
          /*  Iterator<String> keys = propertiesConfiguration.getKeys();
            keys.forEachRemaining(key -> this.properties.put(key, propertiesConfiguration.getString(key)));*/
            System.out.println(this.properties);
        } catch (Exception e) {
            e.printStackTrace();
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
        return "properties-config";
    }

    @Override
    public int getOrdinal() {
        return 0;
    }
}
