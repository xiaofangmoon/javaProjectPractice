package com.xiaofangmoon.web.mvc.user.config;

import com.alibaba.fastjson.JSON;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;

import java.util.*;

/**
 * @author xiaofang
 * @createTime 2021/03/12 01:59
 * @description
 */
public class JavaConfig implements Config {

    /**
     * 内部可变的集合，不要直接暴露在外面
     */
    private List<ConfigSource> configSources = new LinkedList<>();

    private static Comparator<ConfigSource> configSourceComparator = new Comparator<ConfigSource>() {
        @Override
        public int compare(ConfigSource o1, ConfigSource o2) {
            return Integer.compare(o2.getOrdinal(), o1.getOrdinal());
        }
    };

    public JavaConfig() {
        ClassLoader classLoader = getClass().getClassLoader();
        ServiceLoader<ConfigSource> serviceLoader = ServiceLoader.load(ConfigSource.class, classLoader);
        serviceLoader.forEach(configSources::add);
        // 排序
        configSources.sort(configSourceComparator);
        System.out.println(configSources);
    }

    @Override
    public <T> T getValue(String propertyName, Class<T> propertyType) {
        String propertyValue = getPropertyValue(propertyName);

        Optional converter = getConverter(propertyType);
        T value = null;
        Object o = converter.get();
        if (o != null) {
            System.out.println(propertyName);
            value = (T) ((Converter) o).convert(propertyValue);
        } else {
            return value;
        }
        return value;
    }

    protected String getPropertyValue(String propertyName) {
        String propertyValue = null;
        for (ConfigSource configSource : configSources) {
            propertyValue = configSource.getValue(propertyName);
            if (propertyValue != null) {
                break;
            }
        }
        return propertyValue;
    }

    @Override
    public ConfigValue getConfigValue(String propertyName) {

        return null;
    }

    @Override
    public <T> Optional<T> getOptionalValue(String propertyName, Class<T> propertyType) {
        return Optional.empty();
    }

    @Override
    public Iterable<String> getPropertyNames() {
        return null;
    }

    @Override
    public Iterable<ConfigSource> getConfigSources() {
        return Collections.unmodifiableList(configSources);
    }

    @Override
    public <T> Optional getConverter(Class<T> forType) {
        Converter<T> tConverter = value -> {
            //通过反射机制获取泛型的类类型
            if (forType == Integer.class) {
                return (T) Integer.valueOf(value);
            } else if (forType == String.class) {
                return (T) value;
            }else if(forType ==Double.class) {
                return (T) Double.valueOf(value);
            }
            else {
                return JSON.parseObject(value, forType);
            }
        };
        return Optional.ofNullable(tConverter);
    }

    @Override
    public <T> T unwrap(Class<T> type) {
        return null;
    }

}
