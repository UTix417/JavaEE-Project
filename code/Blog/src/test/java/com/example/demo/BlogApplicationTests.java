package com.example.demo;
import com.example.demo.mapper.*;
import com.example.demo.pojo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    BlockMapper blockMapper;
    @Autowired
    TextMapper textMapper;
    @Autowired
    PageMapper pageMapper;
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    InviteCodeMapper inviteCodeMapper;
    @Test
    void contextLoads() {
//        System.out.println(userMapper.getUserById(1));
//        User user1 = new User(1, "shit", "654321", 0, 0,new Date(System.currentTimeMillis()), "pppp");
//        User user2 = new User(2, "shit", "654321", 0,0, new Date(System.currentTimeMillis()), "pppp");
//        User user3 = new User(null, "user5", "654321", 0,0, new Date(System.currentTimeMillis()), "path");
//        User user4 = new User(9, "newuser", "123456", 0,1, new Date(System.currentTimeMillis()), "path");
//        Block block = new Block(null, "修改后的板块2", null, null);
//        Text text = new Text(8,"芜湖起飞",2,new Date(System.currentTimeMillis()),1,user1);
//        Page page = new Page(1, 5, new Date(System.currentTimeMillis()), "我是第五", new User(4, null, null,null, null, null, null));
//        Message message = new Message(null, user1, user2, new Date(System.currentTimeMillis()), "你好");
//        InviteCode inviteCode = new InviteCode(null,"ASDFGHJ",null,1,2,new Date(System.currentTimeMillis()));
//        List<Text> text1 = textMapper.getTextsByBlockId(1,1);
//        List<Text> text2 = textMapper.getTextsByBlockId(1,2);
//        System.out.println(text2);
//        text2.addAll(text1);
//        System.out.println(text2);
        System.out.println(pageMapper.getAllPagesByTextId(1));
//        System.out.println(messageMapper.getAllMessageByUserId(1, 2));
//        System.out.println(userMapper.getUserById(1));
//        userMapper.updateUser(user);
//        System.out.println(blockMapper.getAllBlocks());
//        System.out.println(blockMapper.getBlockById(1));
//        blockMapper.updateBlock(block);
//        System.out.println(textMapper.getTextsByBlockId(1));
//        System.out.println(textMapper.getTextsByTitle("我的"));
//        textMapper.addText(text);
//            textMapper.updateText(text);
//        textMapper.deleteText(8);
//        System.out.println(pageMapper.getAllPagesByTextId(1));
//        pageMapper.addPage(page);
//        pageMapper.deletePage(1,5);
//        System.out.println(pageMapper.getMaxFloor(1));
//        System.out.println(messageMapper.getMessageUser(1));
//        messageMapper.addMessage(message);
//        System.out.println(messageMapper.getAllMessageByUserId(1, 2));
//        System.out.println(messageMapper.getAllMessageByUserId(2, 1));
//        inviteCodeMapper.createInviteCode(inviteCode);
//        inviteCodeMapper.updateInviteCode(inviteCode);
        List<Page> allPagesByTextId = pageMapper.getAllPagesByTextId(1);
//        System.out.println(inviteCodeMapper.getLastDateByUserId(1));
    }

}
