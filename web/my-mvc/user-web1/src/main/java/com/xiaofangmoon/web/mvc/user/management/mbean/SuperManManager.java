package com.xiaofangmoon.web.mvc.user.management.mbean;

import com.xiaofangmoon.web.mvc.user.domain.Person;
import com.xiaofangmoon.web.mvc.user.domain.SuperMan;

/**
 * @author xiaofang
 * @createTime 2021/03/14 14:44
 * @description
 */
public class SuperManManager implements SuperManManagerMBean {

    private SuperMan superMan;

    public SuperManManager(SuperMan superMan) {
        this.superMan = superMan;
    }

    @Override
    public Integer getId() {
        return superMan.getId();
    }

    @Override
    public void setId(Integer id) {
        superMan.setId(id);
    }

    @Override
    public String getName() {
        return superMan.getName();
    }

    @Override
    public void setName(String name) {
        superMan.setName(name);
    }

    @Override
    public Person getPerson() {
        return superMan.getPerson();
    }

    @Override
    public void setPerson(Person person) {
        superMan.setPerson(person);
    }
}
