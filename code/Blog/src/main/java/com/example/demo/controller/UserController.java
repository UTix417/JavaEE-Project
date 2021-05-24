package com.example.demo.controller;

import com.example.demo.mapper.BlockMapper;
import com.example.demo.pojo.Block;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 朱威
 * @create 2021-05-19-9:02
 */
//本类实现普通用户的功能
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    BlockMapper blockMapper;

    //本方法实现登录后返回所有的板块信息
    @RequestMapping("/getAllBlock")
    public String getAllBlock(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Block> blocks = blockMapper.getBlockByLevel(user.getUserLevel());
        model.addAttribute("blocks",blocks);
        return "blocks";//这里返回登录成功后的页面
    }
}