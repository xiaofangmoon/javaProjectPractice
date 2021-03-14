package com.xiaofangmoon.web.mvc.user;

import com.xiaofangmoon.web.mvc.user.domain.User;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.junit.Test;

import java.io.InputStream;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author: xiaofang
 * @createTime: 2021/03/03 22:29
 * @description:
 */
public class TestConfig {


    @Test
    public void testConfigProvice() {
        ServiceLoader<ConfigProviderResolver> load = ServiceLoader.load(ConfigProviderResolver.class);

        Iterator<ConfigProviderResolver> iterator = load.iterator();
        if (iterator.hasNext()) {
            ConfigProviderResolver next = iterator.next();
            Config config = next.getConfig();
            User value = config.getValue("user.obj", User.class);
            System.out.println(value);

            String value1 = config.getValue("application.name", String.class);

            System.out.println(value1);
        }


    }

    @Test
    public void testSystemConfig() {
        System.out.println(Integer.class.getSimpleName());
        for (String propertyName : System.getProperties().stringPropertyNames()) {
            System.out.println(propertyName + " : " + System.getProperties().getProperty(propertyName));
        }
    }

    @Test
    public void testConfig() {
        ClassLoader classLoader = TestConfig.class.getClassLoader();
        System.out.println(classLoader);

    }

    @Test
    public void testProperties() throws ConfigurationException {
        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();
        InputStream resourceAsStream = TestConfig.class.getResourceAsStream("/META-INF/custom-config.properties");
        propertiesConfiguration.load(resourceAsStream);
        String id = propertiesConfiguration.getString("id");
        System.out.println(id);

    }


    @Test
    public void testCommonConfig() {

        ServiceLoader<ConfigProviderResolver> load = ServiceLoader.load(ConfigProviderResolver.class);

        Iterator<ConfigProviderResolver> iterator = load.iterator();

        if (iterator.hasNext()) {
            ConfigProviderResolver configProviderResolver = iterator.next();
            System.out.println(configProviderResolver);
            Config config = configProviderResolver.getConfig();
            Object value = config.getValue("user.country", String.class);

            System.out.println(value);
        }

    }
}
