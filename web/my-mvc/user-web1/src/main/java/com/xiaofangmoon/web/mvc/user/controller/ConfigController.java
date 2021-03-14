package com.xiaofangmoon.web.mvc.user.controller;


import com.xiaofangmoon.web.mvc.controller.RestController;
import com.xiaofangmoon.web.mvc.user.context.ComponentContext;
import com.xiaofangmoon.web.mvc.user.domain.User;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * 输出 “Hello,World” Controller
 *
 * @author xiaofang
 */
@Path("/config")
public class ConfigController implements RestController {

    @GET
    @Path("/info")
    public String index(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        ComponentContext componentContext = ComponentContext.getInstance();
        ConfigProviderResolver configProviderResolver = componentContext.getComponent("bean/Config");
        Config config = configProviderResolver.getConfig();
        return config.getValue(request.getParameter("key"), String.class);
    }

    @GET
    @Path("/user")
    public User getConfigUser(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        ComponentContext componentContext = ComponentContext.getInstance();
        ConfigProviderResolver configProviderResolver = componentContext.getComponent("bean/Config");
        Config config = configProviderResolver.getConfig();
        return config.getValue("user.obj", User.class);
    }


}
