package com.xiaofangmoon.web.mvc.user.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author xiaofang
 * @createTime 2021/03/12 09:57
 * @description
 */
public class DelegatingConfig extends ConfigProviderResolver {
    ConfigProviderResolver configProviderResolver;

    @PostConstruct
    public void init() {
        ServiceLoader<ConfigProviderResolver> load = ServiceLoader.load(ConfigProviderResolver.class);
        Iterator<ConfigProviderResolver> iterator = load.iterator();
        if (iterator.hasNext()) {
            this.configProviderResolver = iterator.next();
        }
    }

    @Override
    public Config getConfig() {
        return configProviderResolver.getConfig();
    }

    @Override
    public Config getConfig(ClassLoader loader) {
        return configProviderResolver.getConfig(loader);
    }

    @Override
    public ConfigBuilder getBuilder() {
        return configProviderResolver.getBuilder();
    }

    @Override
    public void registerConfig(Config config, ClassLoader classLoader) {
        configProviderResolver.registerConfig(config, classLoader);
    }

    @Override
    public void releaseConfig(Config config) {
        configProviderResolver.releaseConfig(config);
    }

    public static ConfigProviderResolver instance() {
        return ConfigProviderResolver.instance();
    }

    public static void setInstance(ConfigProviderResolver resolver) {
        ConfigProviderResolver.setInstance(resolver);
    }
}
