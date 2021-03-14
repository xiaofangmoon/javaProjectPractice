package com.xiaofangmoon.web.mvc.user.management.mbean;

import com.xiaofangmoon.web.mvc.user.domain.Person;

/**
 * @author xiaofang
 * @createTime 2021/03/14 14:29
 * @description
 */
public interface SuperManManagerMBean {


    Integer getId();

    void setId(Integer id);

    String getName();

    void setName(String name);

    Person getPerson();

    void setPerson(Person person);
}
