package com.xiaofangmoon.web.mvc.user.service.impl;

import com.xiaofangmoon.web.mvc.user.domain.User;
import com.xiaofangmoon.web.mvc.user.repository.DatabaseUserRepository;
import com.xiaofangmoon.web.mvc.user.service.IUserService;
import com.xiaofangmoon.web.mvc.user.sql.DBConnectionManager;
import com.xiaofangmoon.web.mvc.user.util.DbUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: xiaofang
 * @createTime: 2021/03/03 20:08
 * @description:
 */
public class UserServiceImpl implements IUserService {
    private static DatabaseUserRepository userRepository;

    static {
        try {
            DBConnectionManager dbConnectionManager = new DBConnectionManager(DbUtils.getConnection());
            userRepository = new DatabaseUserRepository(dbConnectionManager);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public User getUserById(Long id) {
        return (id == null || userRepository == null) ? null : userRepository.getById(id);
    }

    @Override
    public Integer registerUser(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return userRepository.registerUser(user);
    }

}
