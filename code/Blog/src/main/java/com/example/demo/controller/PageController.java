package com.example.demo.controller;

import com.example.demo.mapper.PageMapper;
import com.example.demo.mapper.TextMapper;
import com.example.demo.pojo.Page;
import com.example.demo.pojo.Text;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 朱威
 * @create 2021-05-19-11:29
 */
@Controller
@RequestMapping("/page")
public class PageController {
    @Autowired
    PageMapper pageMapper;
    @Autowired
    TextMapper textMapper;

    //本方法用于实现点击进入某个板块后返回这个板块中所有的帖子
    @ResponseBody
    @RequestMapping("/getAllTexts/{blockId}")
    public List<Text> getAllTexts(@PathVariable("blockId") int blockId, Model model){
        List<Text> texts = textMapper.getTextsByBlockId(blockId);
        return texts;
    }

    //本方法用来进入帖子查看详细内容
    @ResponseBody
    @RequestMapping("getDetailPage/{textId}")
    public List<Page> getDetailPage(@PathVariable("textId") int textId, Model model){
        List<Page> pages = pageMapper.getAllPagesByTextId(textId);
        return pages;
    }

    
}
