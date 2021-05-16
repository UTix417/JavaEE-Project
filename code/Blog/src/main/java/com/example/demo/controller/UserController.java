package com.example.demo.controller;

import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author  王令
 * @date  2021/5/16
 * @version 1.0
 */

@RestController
public class UserController {

    @Autowired
    private UserController userController;

//    根据用户ID获得用户信息
    @GetMapping("/getUserById/{userid}")
    public User getUserById(@PathVariable("userid") Integer userId){
        return userController.getUserById(userId);
    }


}
