package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.PageMapper;
import com.example.demo.mapper.TextMapper;
import com.example.demo.pojo.Page;
import com.example.demo.pojo.Text;
import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 朱威 王令
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

    //该方法用来删除帖子 传入json表单 检测等级和发帖人的ID
    @ResponseBody
    @RequestMapping(value = "/deletePage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int deletePage(@RequestBody JSONObject jsonParam,HttpSession session){
        User now_user= (User) session.getAttribute("user");
        int level = now_user.getUserLevel();
        int textId;
        int pageFloor;
        int res=0;
        textId=jsonParam.getInteger("textId");
        pageFloor=jsonParam.getInteger("pageFloor");
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

//    JSON文件的测试
    @ResponseBody
    @RequestMapping(value = "/json/data", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getByJSON(@RequestBody JSONObject jsonParam)
    { // 直接将json信息打印出来
         System.out.println(jsonParam.toJSONString());
//         将获取的json数据封装一层，然后在给返回
         JSONObject result = new JSONObject();
         result.put("msg", "ok");
         result.put("method", "json");
         result.put("data", jsonParam);
         return result.toJSONString();
    }
    
}
