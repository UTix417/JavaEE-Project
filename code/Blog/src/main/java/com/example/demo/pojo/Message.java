package com.example.demo.pojo;

import lombok.*;

import java.util.Date;

/**
 * @author 朱威
 * @create 2021-05-12-15:01
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Comparable<Message>{
    private Integer messageId;
    private User user1;//发送者
    private User user2;//接收者
    private Date messageSendTime;
    private String messageContent;
    @Override
    public int compareTo(Message user) {
        return this.messageSendTime.compareTo(messageSendTime);
    }
}
