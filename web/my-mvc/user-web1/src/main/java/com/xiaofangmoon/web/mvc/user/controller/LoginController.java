package com.xiaofangmoon.web.mvc.user.controller;


import com.xiaofangmoon.web.mvc.controller.RestController;
import com.xiaofangmoon.web.mvc.user.domain.User;
import com.xiaofangmoon.web.mvc.user.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * 输出 “Hello,World” Controller
 * @author xiaofang
 */
@Path("/user")
public class LoginController implements RestController {

    @GET
    @Path("/info")
    public User index(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        UserServiceImpl userService = new UserServiceImpl();
        return userService.getUserById(Long.valueOf(request.getParameter("id")));
    }

    @POST
    @Path("/register")
    public Integer register(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        UserServiceImpl userService = new UserServiceImpl();
        return userService.registerUser(request, response);
    }


}
