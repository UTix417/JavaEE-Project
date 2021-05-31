package com.example.demo.controller;

import com.example.demo.pojo.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @author  王令
 * @date  ${DATE} ${TIME}
 * @version 1.0
 */

@RestController
public class TextController {


    @Autowired
    private TextController textController;

    //跳转
    @RequestMapping("/jump/{blockId}")
    public String jump(@PathVariable("blockId") int blockId, Model model){
        model.addAttribute("blockId",blockId);
        return "要跳转到的页面的路径";
    }

//    根据ID获得所有帖子信息
    @GetMapping("/getTextsByBlockId/{id}")
    public List<Text> getTextsByBlockId(@PathVariable("id") Integer blockId){
        return textController.getTextsByBlockId(blockId);
    }
//    根据标题查询帖子
    @GetMapping("/getTextsByTitle/{blockTitle}")
    public List<Text> getTextsByTitle(@PathVariable("blockTitle") String blockTitle){
        return textController.getTextsByTitle(blockTitle);
    }


}
