package com.example.demo.config;

import com.example.demo.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 朱威
 * @create 2021-05-19-10:54
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        登录成功之后应该保存用户的session
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            request.setAttribute("msg","权限不足，请先登录");
            response.sendRedirect(request.getContextPath() + "/index.html");
            return false;
        }else {
            return true;
        }
    }
}
