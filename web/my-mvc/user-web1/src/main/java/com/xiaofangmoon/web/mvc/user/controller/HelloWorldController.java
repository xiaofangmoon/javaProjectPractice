package com.xiaofangmoon.web.mvc.user.controller;


import com.xiaofangmoon.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * 输出 “Hello,World” Controller
 */
@Path("/hello")
public class HelloWorldController implements PageController {

    @GET
    @Path("/world") // /hello/world -> HelloWorldController
    public String index(HttpServletRequest request, HttpServletResponse response) throws Throwable {

        return "index.jsp";
    }

    @GET
    @Path("/xiaofang")
    public String demo(HttpServletRequest request, HttpServletResponse response) throws Throwable {

        return "xiaofang.jsp";
    }
}
