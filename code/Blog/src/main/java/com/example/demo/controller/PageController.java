package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.PageMapper;
import com.example.demo.mapper.TextMapper;
import com.example.demo.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 朱威 王令
 * @create 2021-05-19-11:29
 */
@Controller
@Slf4j
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
        List<Text> text1 = textMapper.getTextsByBlockId(blockId,1);
        List<Text> text2 = textMapper.getTextsByBlockId(blockId,2);
        text2.addAll(text1);
        return text2;
    }

    //本方法用来进入帖子查看详细内容
    @ResponseBody
    @RequestMapping("/getDetailPage/{textId}")
    public Object getDetailPage(@PathVariable("textId") int textId, int pageno,int pagesize){
        System.out.println("in getDetailPage");
        List<Page> pages = pageMapper.getAllPagesByTextId(textId);
        pageno--;
        List<Page> res=new ArrayList();
        System.out.println(pages);
        int totalpage=pages.size()/pagesize;
        if(pages.size()%pagesize!=0){
            totalpage++;
        }
        for(int i=0;i<pagesize;i++){
            if(i+pageno*pagesize==pages.size()){
                break;
            }
            res.add(pages.get(i+pageno*pagesize));
        }
        pagein Pagein=new pagein();
        Pagein.setPages(res);
        Pagein.setTotalpage(totalpage);
        return JSONObject.toJSONString(Pagein);
    }

    //该方法用来删除帖子 传入json表单 检测等级和发帖人的ID
    @ResponseBody
    @RequestMapping(value = "/deletePage")
    public int deletePage(Integer textId,Integer pageFloor,HttpSession session){
        User now_user= (User) session.getAttribute("user");
        int level = now_user.getUserLevel();
        System.out.println(textId);
        int res=0;
        if(level>=3){//如果为管理员，则可以直接删除
            res = pageMapper.deletePage(textId,pageFloor);
            return res;
        }
        else if(now_user.getUserId()==pageMapper.getTextmaster(textId)){//如果是发帖人进行删贴，也可以删除
            res = pageMapper.deletePage(textId,pageFloor);
            return res;
        }
        else //否则返回错误-1
            return -1;

    }

    //通过传入textId可以使该帖子置顶
    @ResponseBody
    @RequestMapping("topPage/{textId}")
    public String TopPage(@PathVariable("textId") int textId,HttpSession session){
        User now_user= (User) session.getAttribute("user");
        int level = now_user.getUserLevel();//获得等级
        if (level>=3)//如果不是管理员，无权置顶
//        log.info("textId");
        pageMapper.TopPage(textId);
        return "main";
    }

    //帖子状态恢复正常
    @ResponseBody
    @RequestMapping("PageBack/{textId}")
    public String PageBack(@PathVariable("textId") int textId,HttpSession session){
        User now_user= (User) session.getAttribute("user");
        int level = now_user.getUserLevel();//获得等级
        if (level>=3)//如果不是管理员，无权恢复
//        log.info("textId");
        pageMapper.PageBack(textId);
        return "main";
    }

    //回帖,传入帖子id和回帖内容即可
    @ResponseBody
    @RequestMapping("/reply/{textId}")
    public String replyText(@PathVariable("textId") int textId,String pageContent,HttpSession session,int replyFor){
        System.out.println("in reply");
        int pageFloor = pageMapper.getMaxFloor(textId);
        User user = (User) session.getAttribute("user");
        if(user.getUserState()==1)
            return "回复失败";
        Page page = new Page(textId, pageFloor+1, new Date(), pageContent, user,replyFor);
        System.out.println(page);
        pageMapper.addPage(page);
        return "回复成功";
    }


}
