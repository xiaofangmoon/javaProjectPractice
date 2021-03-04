package com.xiaofangmoon.web.mvc.user.controller;


import com.xiaofangmoon.web.mvc.controller.PageController;
import com.xiaofangmoon.web.mvc.controller.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * 输出 “Hello,World” Controller
 */
@Path("/user/html")
public class LoginPageController implements PageController {

    @GET
    @Path("/register")
    public String register(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "form.jsp";
    }

    @GET
    @Path("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "login.jsp";
    }


}
