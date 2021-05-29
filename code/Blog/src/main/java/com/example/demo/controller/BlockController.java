package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.BlockMapper;
import com.example.demo.pojo.Block;
import com.example.demo.pojo.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
/**
 * @author  王令
 * @date  ${DATE} ${TIME}
 * @version 1.0
 */

@RestController
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
    @ResponseBody
    @GetMapping("/getAllBlocks")
    public String getAllBlocks(Model model){
        List <Block> list  =blockMapper.getAllBlocks();
        model.addAttribute("block_list",list);
        return "getAllBlocks";
    }

    //以list形式返回所有板块信息
    @ResponseBody
    @GetMapping("/getAllBlocks_visitor")
    public List <Block> getAllBlocks(){
        return blockMapper.getAllBlocks();
    }

    @ResponseBody
    @GetMapping("/getblocksByLevel/{level}")
    public List <Block> getBlockByLevel(@PathVariable("level") int level,Model model){
        List <Block> list = blockMapper.getBlockByLevel(5);
        model.addAttribute("block_list",list);
        System.out.println(model);
        return list;
    }


//    获取对应板块的信息
    @ResponseBody
    @GetMapping("/getBlockById/{id}")
    public String getBlockById(@PathVariable("id") int blockId, HttpSession session, Model model){
        /*
        edit by 李肖帆
        */
        session.setAttribute("blockid",blockId);
        Block block=blockMapper.getBlockById(blockId);
        model.addAttribute("block",block);
        return "oneBlock";
    }


    @RequestMapping("blockAdd")
    public String blockadd(@RequestParam(value = "block_id") Integer id,@RequestParam(value = "block_name") String name,@RequestParam(value = "level") Integer level,
                           @RequestParam(value = "user_level") Integer u_level,Model model) {
        String msg;
        if (u_level < 3) {//等级不够则无法添加
            System.out.println(222);
            msg = "无添加资格";
            model.addAttribute("msg", msg);
            return "main";}
        blockMapper.addBlock(id, name, level, 0);
        msg = "添加成功";
        model.addAttribute("msg", msg);
        System.out.println(111);
        return "main";
    }
    
    @RequestMapping("/blockadd")
    public String blockadd(@RequestParam(value = "block_id") Integer id,@RequestParam(value = "block_name") String name,@RequestParam(value = "block_level") Integer level) {
        blockMapper.addBlock(id, name, level, 0);
        return "main";
        }

}
