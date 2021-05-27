package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @author 朱威
 * @create 2021-05-18-20:57
 */
//本类实现用户登录以及注册的业务
@Controller
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/login")
    public String userLogin(int userId, String userPassword, HttpSession session, Model model){
        User user = loginService.login(userId, userPassword);
        if(user != null)
        {
            session.setAttribute("user",user);
            return "redirect:/user/getAllBlock";
        }
        else
        {
            model.addAttribute("msg","用户名或密码错误");
            return "index";
        }
    }

    @RequestMapping("/register")
    public String userRegister(String userName,String userPassword, HttpSession session){
        User user = new User(null, userName, userPassword, 1, new Date(System.currentTimeMillis()), null);
        userMapper.addUser(user);
        session.setAttribute("user",user);
        return "redirect:/user/getAllBlock";
    }
}
