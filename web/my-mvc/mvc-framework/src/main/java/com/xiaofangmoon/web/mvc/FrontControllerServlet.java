package com.xiaofangmoon.web.mvc;

import com.alibaba.fastjson.JSON;
import com.xiaofangmoon.web.mvc.controller.Controller;
import com.xiaofangmoon.web.mvc.controller.PageController;
import com.xiaofangmoon.web.mvc.controller.RestController;
import com.xiaofangmoon.web.mvc.management.servlet.ServletConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Path;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.*;


/**
 * @author: xiaofang
 * @createTime: 2021/03/03 14:53
 * @description:
 */
@Slf4j
public class FrontControllerServlet extends HttpServlet {
    static {
        System.out.println("xiaofang-go");
    }

    /**
     * 请求路径和 Controller 的映射关系缓存
     */
    private Map<String, Controller> controllersMapping = new HashMap<>();

    /**
     * 请求路径和 {@link HandlerMethodInfo} 映射关系缓存
     */
    private Map<String, HandlerMethodInfo> handleMethodInfoMapping = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        initHandleMethods();
        initMBean();
    }

    private void initMBean() {
        try {
            super.init();
            // TODO Auto-generated method stub
            System.out.println("enter--init");
//            MBeanServer server = MBeanServerFactory.createMBeanServer("xiaofang-domain");
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            ObjectName configuration = null;
            configuration = new ObjectName("com.xiaofangmoon.web.mvc.FrontControllerServlet:type=Superman");
            server.registerMBean(new ServletConfiguration(this), configuration);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("exit--init");


    }

    private void initHandleMethods() {
        System.out.println("initHandleMethods");
        ServiceLoader<Controller> loads = ServiceLoader.load(Controller.class);
        for (Controller controller : loads) {
            Class<? extends Controller> controllerClass = controller.getClass();
            Path pathFromClass = controllerClass.getAnnotation(Path.class);
            if (pathFromClass != null) {
                String requestPath = pathFromClass.value();
                Method[] methods = controllerClass.getMethods();

                for (Method method : methods) {
                    //支持请求类型
                    Set<String> supportedHttpMethods = findSupportedHttpMethods(method);
                    Path pathFromMethod = method.getAnnotation(Path.class);
                    String path = requestPath;
                    if (pathFromMethod != null) {
                        path += pathFromMethod.value();
                        handleMethodInfoMapping.put(path,
                                new HandlerMethodInfo(path, method, supportedHttpMethods));
                        controllersMapping.put(path, controller);
                    }

                }

            }
        }

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        log.info("requestURI={}" + requestURI);
        String servletContextPath = request.getContextPath();
        log.info("servletContext={}" + servletContextPath);
        String prefixPath = servletContextPath;
        log.info("prefixPath={}" + prefixPath);
        String requestMappingPath = StringUtils.substringAfter(requestURI,
                StringUtils.replace(prefixPath, "//", "/"));

        // 映射到 Controller
        Controller controller = controllersMapping.get(requestMappingPath);
        if (controller != null) {
            HandlerMethodInfo handlerMethodInfo = handleMethodInfoMapping.get(requestMappingPath);
            try {
                if (handlerMethodInfo != null) {
                    String httpMethod = request.getMethod();

                    if (!handlerMethodInfo.getSupportedHttpMethods().contains(httpMethod)) {
                        // HTTP 方法不支持
                        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                        return;
                    }

                    if (controller instanceof PageController) {
                        String viewPath = String.valueOf(handlerMethodInfo.getHandlerMethod().invoke(controller, request, response));

                        ServletContext servletContext = request.getServletContext();
                        if (!viewPath.startsWith("/")) {
                            viewPath = "/" + viewPath;
                        }
                        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(viewPath);
                        requestDispatcher.forward(request, response);
                        return;

                    } else if (controller instanceof RestController) {
                        PrintWriter writer = response.getWriter();
                        Object invoke = handlerMethodInfo.getHandlerMethod().invoke(controller, request, response);
                        if (invoke instanceof String) {
                            writer.write(invoke.toString());
                        } else {
                            writer.write(JSON.toJSONString(invoke));
                        }
                        writer.flush();
                    }

                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            } finally {
            }

        }

    }

    /**
     * 获取处理方法中标注的 HTTP方法集合
     *
     * @param method 处理方法
     * @return
     */
    private Set<String> findSupportedHttpMethods(Method method) {
        Set<String> supportedHttpMethods = new LinkedHashSet<>();
        for (Annotation annotationFromMethod : method.getAnnotations()) {
            HttpMethod httpMethod = annotationFromMethod.annotationType().getAnnotation(HttpMethod.class);
            if (httpMethod != null) {
                supportedHttpMethods.add(httpMethod.value());
            }
        }

        if (supportedHttpMethods.isEmpty()) {
            supportedHttpMethods.addAll(Arrays.asList(HttpMethod.GET, HttpMethod.POST,
                    HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.HEAD, HttpMethod.OPTIONS));
        }

        return supportedHttpMethods;
    }
}
