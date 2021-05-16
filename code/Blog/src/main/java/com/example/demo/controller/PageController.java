package com.example.demo.controller;

import com.example.demo.mapper.PageMapper;
import com.example.demo.pojo.Page;
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
public class PageController {

    @Autowired
    private PageMapper pageMapper;

//    根据ID获得某个帖子中所有楼层
    @GetMapping("/getAllPagesByTextId/{textId}")
    public List<Page> getAllPagesByTextId(@PathVariable("textId") Integer textId){
        return pageMapper.getAllPagesByTextId(textId);
    }

}
