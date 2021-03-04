package com.xiaofangmoon.web.mvc.user.service;

import com.xiaofangmoon.web.mvc.user.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: xiaofang
 * @createTime: 2021/03/03 20:07
 * @description:
 */
public interface IUserService {

    User getUserById(Long id);

    Integer registerUser(HttpServletRequest request, HttpServletResponse response);
}
