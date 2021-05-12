package com.example.demo.mapper;

import com.example.demo.pojo.Message;
import com.example.demo.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 朱威
 * @create 2021-05-12-15:47
 */
//本接口用来管理私信功能
@Repository
public interface MessageMapper {
    //按用户id在表中查找所有与该用户有过私信（不论接发）的所有用户信息
    public List<User> getMessageUser(int userId);

    //根据发送者ID与接收者ID获取所有从sender发送到recipient的信息，如果像获取到两人所有信息请调用两次本方法并交换参数
    public List<Message> getAllMessageByUserId(Integer senderId,Integer recipientId);

    //发送私信
    public int addMessage(Message message);
}
