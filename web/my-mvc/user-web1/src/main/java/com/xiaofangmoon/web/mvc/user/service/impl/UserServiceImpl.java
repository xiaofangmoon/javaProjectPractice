package com.xiaofangmoon.web.mvc.user.service.impl;

import com.xiaofangmoon.web.mvc.user.domain.User;
import com.xiaofangmoon.web.mvc.user.repository.DatabaseUserRepository;
import com.xiaofangmoon.web.mvc.user.service.IUserService;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author: xiaofang
 * @createTime: 2021/03/03 20:08
 * @description:
 */
public class UserServiceImpl implements IUserService {
    @Resource(name = "bean/UserRepository")
    private DatabaseUserRepository userRepository;

    @Resource(name = "bean/EntityManager")
    EntityManager entityManager;

    @Resource(name = "bean/Validator")
    Validator validator;


    @Override
    public User getUserById(Long id) {
        return (id == null || entityManager == null) ? null : entityManager.find(User.class, id);
//        return (id == null || userRepository == null) ? null : userRepository.getById(id);
    }

    @Override
    public Integer registerUser(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Set<ConstraintViolation<User>> validate = validator.validate(user);
        for (ConstraintViolation<User> userConstraintViolation : validate) {
            throw new RuntimeException(userConstraintViolation.getMessage());
        }
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        return Math.toIntExact(user.getId());
    }

}
