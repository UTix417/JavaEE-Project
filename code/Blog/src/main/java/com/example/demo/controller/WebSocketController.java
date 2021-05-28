package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Message;
import com.example.demo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/WebSocket")
@Component
@Slf4j
public class WebSocketController {
    @Resource
    MessageMapper messageMapper;
    @Resource
    UserMapper userMapper;
    @OnOpen
    public void onOpen(Session session) {
        log.info("onOpen前存活: ");
        Map<String, List<String>> requestParameterMap = session.getRequestParameterMap();
        String userId = requestParameterMap.get("fromId").get(0);
        log.info("用户上线了, userId为:{}, sessionId为:{}", userId, session.getId());
        //将新用户存入在线的组
    }
    @OnClose
    public void onClose(Session session) {
        log.info("onClose前存活:");
        Map<String, List<String>> requestParameterMap = session.getRequestParameterMap();
        String userId = requestParameterMap.get("fromId").get(0);
        log.info("有用户断开了, userId为:{}, sessionId为:{}", userId, session.getId());
        //将掉线的用户移除在线的组里
    }
    @OnError
    public void onError(Throwable throwable) {
        log.error("onError: {}", throwable.getMessage());
    }
    /**
     * 收到客户端发来消息
     */
    @OnMessage
    public void onMessage(String dataStr,Session session){
        JSONObject data = JSON.parseObject(dataStr);
        String type=data.getString("type");
        System.out.println(dataStr);
        if(!StringUtils.isEmpty(type) && "heartbeat".equals(type)){
            List<Message> messages=messageMapper.getAllMessageByUserId(data.getInteger("fromId"),data.getInteger("toId"));
            messages.addAll(messageMapper.getAllMessageByUserId(data.getInteger("toId"),data.getInteger("fromId")));
            Collections.sort(messages);
            session.getAsyncRemote().sendText(JSON.toJSONString(messages));
        }else{
            String fromId=data.getString("fromId");
            String toId=data.getString("toId");
            Message message=new Message();
            User from=userMapper.getUserById(Integer.valueOf(fromId));
            User to=userMapper.getUserById(Integer.valueOf(toId));
            message.setUser1(from);
            message.setUser2(to);
            message.setMessageContent(data.getString("context"));
            message.setMessageSendTime(new Date());
            messageMapper.addMessage(message);
        }
    }

}

