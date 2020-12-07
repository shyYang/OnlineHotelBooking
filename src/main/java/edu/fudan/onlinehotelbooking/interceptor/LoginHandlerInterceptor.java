package edu.fudan.onlinehotelbooking.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

import static edu.fudan.onlinehotelbooking.core.ProjectConstant.*;

/**
 * created by 姜向阳
 * on 2020/12/7
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception { }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception { }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute(USER_ID_SESSION);
        System.out.println("preHandle----" + user + " ::: " + request.getRequestURL());
        System.out.println(user);
        if (user == null) {
            response.sendRedirect("");
            return false;
        }
        return true;
    }

}
