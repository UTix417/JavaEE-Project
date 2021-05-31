package com.example.demo.controller;

import com.example.demo.mapper.PageMapper;
import com.example.demo.mapper.TextMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Page;
import com.example.demo.pojo.Text;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
By 李肖帆
发帖的controller
 */
@Controller
public class TextPublishController {
    @Resource
    private TextMapper textMapper;
    @Resource
    private PageMapper pageMapper;
    @Resource
    private UserMapper userMapper;
    @GetMapping("/TextPublish")
    public String TextPublish(){
        return "/TextPublish";
    }
    @PostMapping("/TextPublish")
    @ResponseBody
    public String TextPublish(HttpSession session, @RequestBody Map<String,Object> map){
        System.out.println("In TextPublishController controller");
        User now_user= (User) session.getAttribute("user");
        String title=String.valueOf(map.get("title"));
        session.setAttribute("blockid",1);
        String content= String.valueOf(map.get("content"));
        int blockid= (int) session.getAttribute("blockid");
        System.out.println(title);
        System.out.println(content);
        Text text=new Text();
        List<Text> texts = textMapper.getTextsByBlockId(blockid);
        Integer textId= texts.size();
        text.setTextId(textId);
        text.setTextTitle(title);
        text.setTextStatus(1);
        text.setTextCreateTime(new Date());
        text.setUser(now_user);
        text.setBlockId(blockid);//Blocks存session
        System.out.println(text);
        textMapper.addText(text);
        Page page=new Page();
        page.setTextId(textId);
        page.setPageFloor(0);
        page.setPageContent(content);
        page.setUser(now_user);
        page.setPageRetime(new Date());
        System.out.println(page);
        pageMapper.addPage(page);
        System.out.println("end");
        return "发帖成功";//返回首页?个人页？
    }
}
