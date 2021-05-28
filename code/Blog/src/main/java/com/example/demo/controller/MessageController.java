package com.example.demo.controller;

import com.example.demo.mapper.MessageMapper;
import com.example.demo.pojo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MessageController {
    @Resource
    private MessageMapper messageMapper;
    @GetMapping("/Message")
    public String Message(){
        return "/Message";
    }
    @ResponseBody
    @GetMapping("/MessageUsers")
    public List<User>getallusers(HttpSession session){
        System.out.println("MessageUsers");
        User now= (User) session.getAttribute("user");
        System.out.println(messageMapper.getMessageUser(now.getUserId()));
        return messageMapper.getMessageUser(now.getUserId());
    }
    @ResponseBody
    @GetMapping("/MessageRecentUser")
    public String MessageRecentUser(HttpSession session){
        User now= (User) session.getAttribute("user");
        return now.getUserId().toString();
    }
}
