package com.example.demo.mapper;

import com.example.demo.pojo.InviteCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author 朱威
 * @create 2021-05-12-16:04
 */
//本接口用来管理邀请码
@Repository
@Mapper
public interface InviteCodeMapper {
    //某个用户创建了一个邀请码
    public int createInviteCode(InviteCode inviteCode);

    //查找一个用户上一次创建邀请码的时间，防止一个用户短时间内反复创建邀请码
    public Date getLastDateByUserId(Integer CreatorId);

    //使用验证码,这里的inviteCode只需要有使用者的ID以及验证码内容
    public int updateInviteCode(InviteCode inviteCode);

}
