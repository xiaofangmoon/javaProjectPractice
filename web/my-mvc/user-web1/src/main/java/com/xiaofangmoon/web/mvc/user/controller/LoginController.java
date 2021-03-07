package com.xiaofangmoon.web.mvc.user.controller;


import com.xiaofangmoon.web.mvc.controller.RestController;
import com.xiaofangmoon.web.mvc.user.context.ComponentContext;
import com.xiaofangmoon.web.mvc.user.domain.User;
import com.xiaofangmoon.web.mvc.user.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * 输出 “Hello,World” Controller
 *
 * @author xiaofang
 */
@Path("/user")
public class LoginController implements RestController {

    @GET
    @Path("/info")
    public User index(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        ComponentContext componentContext = (ComponentContext) request.getServletContext().getAttribute(ComponentContext.CONTEXT_NAME);
        UserServiceImpl userService = componentContext.getComponent("bean/UserServices");
        return userService.getUserById(Long.valueOf(request.getParameter("id")));
    }

    @POST
    @Path("/register")
    public Object register(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        ComponentContext componentContext = (ComponentContext) request.getServletContext().getAttribute(ComponentContext.CONTEXT_NAME);
        UserServiceImpl userService = componentContext.getComponent("bean/UserServices");
        Integer integer;
        try {
            integer = userService.registerUser(request, response);
        } catch (Exception e) {
            return e.getMessage();
        }
        return integer;
    }


}
