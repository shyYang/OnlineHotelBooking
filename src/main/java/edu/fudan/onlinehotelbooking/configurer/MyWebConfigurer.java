package edu.fudan.onlinehotelbooking.configurer;

import edu.fudan.onlinehotelbooking.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * created by 姜向阳
 * on 2020/12/7
 */
@Configuration
public class MyWebConfigurer implements WebMvcConfigurer {
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/index.html").setViewName("login");
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        registry.addInterceptor(new LoginHandlerInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/login","/hotel/sign_up","/customer/sign_up")
//                .excludePathPatterns("/hotel/**");
//
//    }
}
