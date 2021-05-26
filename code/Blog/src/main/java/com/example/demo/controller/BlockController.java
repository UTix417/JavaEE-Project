package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.BlockMapper;
import com.example.demo.pojo.Block;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Block> getAllBlocks(){
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
    public int addBlock(@RequestBody JSONObject jsonParam,HttpSession session){
        int level=0;
        level = (int) session.getAttribute("level");
        if(level<=3)
            return -1;
        int block_id,block_level,block_number;
        String block_name;
        int res=0;
        block_id=jsonParam.getInteger("block_id");
        block_name=jsonParam.getString("block_name");
        block_level=jsonParam.getInteger("block_level");
        block_number=jsonParam.getInteger("block_number");
        res=blockMapper.addBlock(block_id,block_name,block_level,block_number);
        return res;
    }


}
