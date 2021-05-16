package com.example.demo.controller;

import com.example.demo.mapper.BlockMapper;
import com.example.demo.pojo.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @author  王令
 * @date  2021/5/16
 * @version 1.0
 */

@RestController
public class BlockController {

    @Autowired
    private BlockMapper blockMapper;

//  获取所有板块信息
    @GetMapping("/getAllBlocks")
    public List<Block> getAllBlocks(){
        return blockMapper.getAllBlocks();
    }


//    获取对应板块的信息
    @GetMapping("/getBlockById/{id}")
    public Block getBlockById(@PathVariable("id") int blockId){
        return blockMapper.getBlockById(blockId);
    }


}
