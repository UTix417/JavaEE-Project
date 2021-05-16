package com.example.demo.controller;


import com.example.demo.pojo.Message;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @author  王令
 * @date  2021/5/16
 * @version 1.0
 */

@RestController
public class MessageController {

    @Autowired
    private MessageController messageController;

//    获取与该用户有过私信联系的用户信息
    @GetMapping("/getMessageUser/{userid}")
    public List<User> getMessageUser(@PathVariable("uesrid") int userId){
        return messageController.getMessageUser(userId);
    }

}
