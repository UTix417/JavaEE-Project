package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.TextMapper;
import com.example.demo.pojo.PageDiv;
import com.example.demo.pojo.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/**
 * @author  王令
 * @date  ${DATE} ${TIME}
 * @version 1.0
 */

@RestController
public class TextController {


    @Autowired
    private TextMapper textMapper;

    //跳转
    @RequestMapping("/jump/{blockId}")
    public String jump(@PathVariable("blockId") int blockId, Model model){
        model.addAttribute("blockId",blockId);
        return "要跳转到的页面的路径";
    }

//    根据ID获得所有帖子信息
    @ResponseBody
    @RequestMapping ("/getTextsByBlockId/{id}")
    public Object getTextsByBlockId(int pageno,int pagesize,@PathVariable("id") Integer blockId){
        System.out.println("in getTextsByBlockId");
        List<Text> ans=textMapper.getTextsByBlockId(blockId,2);
        ans.addAll(textMapper.getTextsByBlockId(blockId,1));
        List<Text> res=new ArrayList();
        pageno--;
        int totalpage=ans.size()/pagesize;
        if(ans.size()%pagesize!=0){
            totalpage++;
        }
        for(int i=0;i<pagesize;i++){
            if(i+pageno*pagesize==ans.size()){
                break;
            }
            res.add(ans.get(i+pageno*pagesize));
        }
        PageDiv pageDiv=new PageDiv();
        pageDiv.setTexts(res);
        pageDiv.setTotalpage(totalpage);
        return JSONObject.toJSONString(pageDiv);
    }
//    根据标题查询帖子
    @GetMapping("/getTextsByTitle/{blockTitle}")
    public List<Text> getTextsByTitle(@PathVariable("blockTitle") String blockTitle){
        return textMapper.getTextsByTitle(blockTitle);
    }


}
