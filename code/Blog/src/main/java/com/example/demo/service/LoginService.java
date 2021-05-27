package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 朱威
 * @create 2021-05-18-21:01
 */
@Service
public class LoginService {
    @Autowired
    UserMapper userMapper;
    public User login(int userId,String userPassword){
        User user = userMapper.getUserById(userId);
        if (userPassword.equals(user.getUserPassword()))
            return user;
        else
            return null;
    }
}
