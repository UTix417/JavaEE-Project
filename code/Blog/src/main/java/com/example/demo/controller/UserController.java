package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.BlockMapper;
import com.example.demo.mapper.InviteCodeMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Block;
import com.example.demo.pojo.User;
import com.example.demo.service.InviteCodeService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

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
    @Autowired
    UserMapper userMapper;
    @Autowired
    InviteCodeService inviteCodeService;

    //本方法实现登录后返回所有的板块信息,同时在用户每次登录时都会检查其是否已经到达升级时限
    @ResponseBody
    @RequestMapping("/getAllBlock")
    public List<Block> getAllBlock(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        long time = (System.currentTimeMillis() - user.getUserCreateTime().getTime());
        int days = (int)(time/(1000 * 60 * 60 *24));
        if(days >= 180 && user.getUserLevel() == 1 ){
            user.setUserLevel(2);
            userMapper.updateUser(user);
        }
        List<Block> blocks = blockMapper.getBlockByLevel(user.getUserLevel());
        return blocks;
    }

    //本方法用来在各处查询用户的全部信息
    @ResponseBody
    @RequestMapping("/getUserInfo/{userId}")
    public User getUserInfo(@PathVariable("userId") int userId,Model model){
        User user = userMapper.getUserById(userId);
        return user;
    }

    //本方法用于用户获取自己的信息
    @ResponseBody
    @RequestMapping("/getMyInfo")
    public User getMyInfo(HttpSession session){
        User user = (User) session.getAttribute("user");
        return user;
    }

    //本方法用来实现用户更新自己的信息,传所有要更新的User属性就可以，但UserId是必备的，同时密码不应该在这里修改
    @ResponseBody
    @RequestMapping("/updateInfo")
    public User updateUserInfo(@RequestBody Map<String,Object> map){
        System.out.println(map);
        User user = new User();
        user.setUserId((Integer) map.get("userId"));
        user.setUserName((String) map.get("userName"));
        System.out.println(user);
        userMapper.updateUser(user);
        return user;
    }

    //本方法用来更新密码,传参时记得传一新一旧两个密码
    @ResponseBody
    @RequestMapping("/updatePassword/{userId}")
    public boolean updatePassword(@PathVariable("userId") int userId,Model model,String oldPassword,String newPassword,HttpSession session){
        String password = userMapper.getUserById(userId).getUserPassword();
        if (oldPassword.equals(password)){
            userMapper.updateUser(new User(userId,null,newPassword,null,null,null,null));
            return true;
        }else {
            model.addAttribute("msg","旧密码错误，请确认密码正确");
            return false;
        }
    }

    //本方法用来处理用户申请一个邀请码，直接返回一个String类型的验证码，推荐使用ajax发送请求
    @ResponseBody
    @RequestMapping("/inviteCode")
    public String getInviteCode(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        String inviteCode = inviteCodeService.getInviteCode(user.getUserId());
        return inviteCode;
    }

    //本方法用来处理用户使用一个验证码
    @ResponseBody
    @RequestMapping("/inviteUpLevel")
    public String inviteUpLevel(HttpSession session,String inviteCode,Model model){
        User user = (User) session.getAttribute("user");
        if (inviteCodeService.useInviteCode(user,inviteCode)){
            return "使用成功";
        }else {
            return "邀请码错误或者您已经是会员用户";//输入邀请码的页面
        }
    }

//    @ResponseBody
//    @RequestMapping(value = "/banUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public int banUser(@RequestBody JSONObject jsonParam,HttpSession session){
//        User now_user= (User) session.getAttribute("user");
//        int level = now_user.getUserLevel();
//        if(level<3)//3等级以上的人才能BAN用户
//        return -1;
//        int userid=0;
//        int res=0;
//
//        userid=jsonParam.getInteger("userId");
//        //res = userMapper.banUser(userid);
//
//        return res;
//    }

    //本方法用来封禁一个用户请传入用户Id和解封时间,-1代表封禁失败
    //传入一个用户的ID，直接禁言1天
    @ResponseBody
    @RequestMapping("/banUser/{id}")
    public int banUser(@PathVariable("id") int userId,HttpSession session){
        User now_user= (User) session.getAttribute("user");
        Integer userLevel = now_user.getUserLevel();
        System.out.println(userLevel);

        Date date = new Date(); //取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1); //把日期往后增加一天,整数  往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果

        if (userLevel<3)//3等级以上的人才能BAN用户
            return -1;
        return userMapper.banUser(userId,date);
    }
}
