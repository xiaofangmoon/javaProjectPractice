package com.xiaofangmoon.web.mvc.user.config.source;

import com.xiaofangmoon.web.mvc.user.context.ComponentContext;
import org.eclipse.microprofile.config.spi.ConfigSource;

import javax.servlet.ServletContext;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author xiaofang
 * @createTime 2021/03/12 02:12
 * @description
 */
public class MyServletContextConfigSource implements ConfigSource {

    /**
     * Java 系统属性最好通过本地变量保存，使用 Map 保存，尽可能运行期不去调整
     * -Dapplication.name=user-web
     */
    private final Map<String, String> properties;

    public MyServletContextConfigSource() {
        System.out.println("MyServletContextConfigSource init...");
        properties = new HashMap<>();
        ServletContext servletContext = ComponentContext.getServletContext();
        Enumeration<String> initParameterNames = servletContext.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String key = initParameterNames.nextElement();
            properties.put(key, servletContext.getInitParameter(key));
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
        return "ServletContext config";
    }

    @Override
    public int getOrdinal() {
        return 1;
    }
}
