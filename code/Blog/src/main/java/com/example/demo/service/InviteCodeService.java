package com.example.demo.service;

import com.example.demo.mapper.InviteCodeMapper;
import com.example.demo.pojo.InviteCode;
import com.example.demo.pojo.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 朱威
 * @create 2021-05-19-20:32
 */
@Service
public class InviteCodeService {
    @Autowired
    InviteCodeMapper inviteCodeMapper;
    //本方法用来处理验证码的申请
    public String getInviteCode(int userId){
        Date lastDate = inviteCodeMapper.getLastDateByUserId(userId);
        if (lastDate == null){
            String code = RandomStringUtils.random(16,true,true);//生成一个16位的包含数字字母的邀请码，数量级在8^24次方左右，因此并未考虑生成两个相同的邀请码的情况
            inviteCodeMapper.createInviteCode(new InviteCode(null,code,0,userId,null,new Date(System.currentTimeMillis())));
            return code;
        }else if((int)(System.currentTimeMillis() - lastDate.getTime()) / (60 * 60 * 24 *1000) > 30){
            String code = RandomStringUtils.random(16,true,true);
            inviteCodeMapper.createInviteCode(new InviteCode(null,code,0,userId,null,new Date(System.currentTimeMillis())));
            return code;
        }else
            return null;
    }

    //本方法用来处理邀请码的使用
    public boolean useInviteCode(User user, String code){
        InviteCode inviteCode = new InviteCode(null, code, null, null, user.getUserId(), null);
        int result = inviteCodeMapper.updateInviteCode(inviteCode);
        if (result > 0 && user.getUserLevel() == 1){
            return true;
        }else
            return false;
    }
}
