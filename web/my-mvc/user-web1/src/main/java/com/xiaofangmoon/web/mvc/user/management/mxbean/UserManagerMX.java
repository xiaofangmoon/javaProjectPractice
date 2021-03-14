package com.xiaofangmoon.web.mvc.user.management.mxbean;

import com.xiaofangmoon.web.mvc.user.domain.User;
import lombok.SneakyThrows;
import lombok.ToString;

import javax.management.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaofang
 */
@ToString
public class UserManagerMX implements DynamicMBean {


    // 五个属性
    // id、name、password、email、phoneNumber
    private Map<String, Object> attributesMap = new HashMap<>();
    private User user;
    private Map<String, Method> methodMap = new HashMap<>();

    public UserManagerMX() {
        this(null);
    }

    public UserManagerMX(User user) {
        this.user = user;
        initMethod();
    }

    void initMethod() {
        Method[] declaredMethods = User.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            methodMap.put(declaredMethod.getName(), declaredMethod);
        }
        System.out.println(methodMap);
    }

    @Override
    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        if (!attributesMap.containsKey(attribute)) {
            throw new AttributeNotFoundException("...");
        }
        return attributesMap.get(attribute);
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        attributesMap.put(attribute.getName(), attribute.getValue());
    }

    @Override
    public AttributeList getAttributes(String[] attributes) {
        AttributeList attributeList = new AttributeList();
        for (String attribute : attributes) {
            try {
                Object attributeValue = getAttribute(attribute);
                attributeList.add(new Attribute(attribute, attributeValue));
            } catch (AttributeNotFoundException | MBeanException | ReflectionException e) {
            }
        }
        return attributeList;
    }

    @Override
    public AttributeList setAttributes(AttributeList attributesList) {
        for (Object attribute : attributesList) {
            Attribute att = (Attribute) attribute;
            attributesMap.put(att.getName(), att.getValue());
        }
        return attributesList;
    }

    @SneakyThrows
    @Override
    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        return methodMap.containsKey(actionName) ? methodMap.get(actionName).invoke(user, params) : null;
    }

    @SneakyThrows
    @Override
    public MBeanInfo getMBeanInfo() {
        List<MBeanAttributeInfo> attributeInfoList = new ArrayList<MBeanAttributeInfo>();
        attributeInfoList.add(new MBeanAttributeInfo("id", "java.lang.Integer", "ID1", true, true, false));
        attributeInfoList.add(new MBeanAttributeInfo("name", "java.lang.String", "name1", true, true, false));
        attributeInfoList.add(new MBeanAttributeInfo("email", "java.lang.String", "email1", true, true, false));
        List<MBeanOperationInfo> operationInfoList = new ArrayList<>();
        operationInfoList.add(new MBeanOperationInfo("print", User.class.getMethod("printHello")));

        List<MBeanNotificationInfo> beanNotificationInfoList = new ArrayList<>();
        beanNotificationInfoList.add(new MBeanNotificationInfo(new String[]{"jmx.attribute.change"}, "com.xiaofangmoon.web.mvc.user.management.notice.ServerConfigureNotificationListener", "gggoo"));

        return new MBeanInfo("com.xiaofangmoon.web.mvc.user.domain.User", "hello xiaofang MXBean",
                attributeInfoList.toArray(new MBeanAttributeInfo[0]),
                null,
                operationInfoList.toArray(new MBeanOperationInfo[0]),
                beanNotificationInfoList.toArray(new MBeanNotificationInfo[0]));
    }

}