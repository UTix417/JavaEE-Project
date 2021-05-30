
package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author 朱威
 * @create 2021-05-12-15:03
 */
//本接口用于从数据库查询用户数据
//@Repository
@Repository
public interface UserMapper {
    //根据用户ID获取用户信息
    public User getUserById(Integer userId);

    //注册用户,用户id应该为null
    public int addUser(User user);

    //更新用户信息,不要更新ID与注册时间，用户id不是null且不能修改
    public  int updateUser(User user);

    //禁言用户 默认一天 导入ID
    public int banUser(int userId, Date outTime);

    //解封用户
    public int recoverUser(int userId);
}

