package com.xiaofangmoon.web.mvc.user.config;

import com.alibaba.fastjson.JSON;
import com.xiaofangmoon.web.mvc.user.domain.User;
import org.eclipse.microprofile.config.spi.Converter;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author xiaofang
 * @createTime 2021/03/14 19:42
 * @description
 */
public class ConfigConverter<T> implements Converter<T> {
    @Override
    public T convert(String value) throws IllegalArgumentException, NullPointerException {
        //通过反射机制获取泛型的类类型
        Type actualTypeArgument = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        String typeName = actualTypeArgument.getTypeName();
        System.out.println(typeName);

        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println("xiaofang-gogo");
        System.out.println(entityClass);
        if (entityClass == Integer.class) {
            return (T) Integer.valueOf(value);
        } else if (entityClass == String.class) {
            return (T) value;
        } else {
            return JSON.parseObject(value, entityClass);
        }
    }

    public static void main(String[] args) {
        ConfigConverter<String> configConverter = new ConfigConverter<>();
        configConverter.convert("xiaofang");

    }
}
