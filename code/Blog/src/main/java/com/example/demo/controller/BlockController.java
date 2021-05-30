package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.BlockMapper;
import com.example.demo.pojo.Block;
import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
/**
 * @author  王令
 * @date  ${DATE} ${TIME}
 * @version 1.0
 */

@Controller
public class BlockController {

    @Autowired
    private BlockMapper blockMapper;

//  获取所有板块信息
//    @ResponseBody
//    @GetMapping("/getAllBlocks")
//    @RequestMapping(value = "getAllBlocks", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public String getAllBlocks(){
//        List<Block> list=blockMapper.getAllBlocks();
//        JSONObject res=new JSONObject();
//        for(int i =0;i<list.size();i++)
//        {
//            Block b =(Block)list.get(i);
//            res.put("blockId",b.getBlockId());
//            res.put("blockName",b.getBlockName());
//            res.put("blockLevel",b.getBlockLevel());
//            res.put("blockNumber",b.getBlockNumber());
//        }
//        return res.toString();
//    }

    //    获得所有板块的信息
    @RequestMapping("/GetAllBlocks")
    public String getAllBlocks(Model model)throws Exception{
        List<Block> list = blockMapper.getAllBlocks();
        model.addAttribute("block_list",list);
        return "/visit";
    }

    @RequestMapping("/visit")
    public String visit() {
        return "visit";
    }
    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    @ResponseBody
    @GetMapping("/getblocksByLevel")
    public List <Block> getblocksByLevel(@RequestParam(value = "level") int level, Model model){
        List <Block> list = blockMapper.getBlockByLevel(level);
        model.addAttribute("block_list",list);
        return blockMapper.getBlockByLevel(level);
    }

    @ResponseBody
    @GetMapping("/getAllBlocks2")
    public List<Block> getAllBlocks(HttpSession session){
        //User user = new User(4,'111','654321',0,'2021-05-12','kkk',1);
        //session.setAttribute("user",user);
        return blockMapper.getAllBlocks();
    }

    //    获取对应板块的信息
    @ResponseBody
    @GetMapping("/getBlockById/{id}")
    public Block getBlockById(@PathVariable("id") int blockId, HttpSession session){
        /*
        edit by 李肖帆
        */
        session.setAttribute("blockid",blockId);
        return blockMapper.getBlockById(blockId);
    }

    @ResponseBody
    @RequestMapping(value = "/addBlock", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addBlock(@RequestBody JSONObject jsonParam,HttpSession session,Model model){
        User now_user= (User) session.getAttribute("user");
        int level = now_user.getUserLevel();
        if(level<=3)//等级不够则无法删除
            return "add_fail";
        int block_id,block_level,block_number;
        String block_name;
        int res=0;
        block_id=jsonParam.getInteger("block_id");
        block_name=jsonParam.getString("block_name");
        block_level=jsonParam.getInteger("block_level");
        block_number=jsonParam.getInteger("block_number");
        res=blockMapper.addBlock(block_id,block_name,block_level,block_number);
        return "add_success";
    }

    @RequestMapping("/blockadd")
    public String blockadd(@RequestParam(value = "block_id") Integer id,@RequestParam(value = "block_name") String name,@RequestParam(value = "block_level") Integer level) {
        blockMapper.addBlock(id, name, level, 0);
        return "main";
    }



}