package com.example.demo.controller;

import com.example.demo.mapper.MessageMapper;
import com.example.demo.pojo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/MessageUsers")
    public ResponseEntity<List<User>>getallusers(HttpSession session){
        System.out.println("MessageUsers");
        User now= (User) session.getAttribute("user");
        return ResponseEntity.ok(messageMapper.getMessageUser(now.getUserId()));
    }
    @GetMapping("/MessageRecentUser")
    public ResponseEntity<String> MessageRecentUser(HttpSession session){
        User now= (User) session.getAttribute("user");
        return ResponseEntity.ok(now.getUserId().toString());
    }
}
