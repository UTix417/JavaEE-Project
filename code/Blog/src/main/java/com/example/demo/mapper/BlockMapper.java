package com.example.demo.mapper;

import com.example.demo.pojo.Block;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 朱威
 * @create 2021-05-12-15:09
 */
//本接口用来处理板块信息
@Repository
@Mapper
public interface BlockMapper {
    //获取所有板块
    public List<Block> getAllBlocks();

    //根据板块id获取某一个板块的信息
    public Block getBlockById(int blockId);

    //更新板块信息，主要是用来设置板块访问等级,板块应该ID在对象中
    public int updateBlock(Block block);
}
