package com.example.demo.mapper;

import com.example.demo.pojo.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 朱威
 * @create 2021-05-12-15:24
 */
//本接口用来处理帖子中的每一层楼
@Repository
public interface PageMapper {
    //根据帖子ID获取到一个帖子中的全部楼层
    public List<Page> getAllPagesByTextId(Integer textId);

    //向帖子表中插入数据,可用于回复帖子
    public int addPage(Page page);

    //根据帖子ID与楼层号进行删楼
    public int deletePage(@Param("textId") int textId,@Param("pageFloor") int pageFloor);

    //根据帖子id查询最大楼层数用来在回复之前确定该回复的楼层
    public int getMaxFloor(int textId);

    //根据帖子id查找发帖人
    public int getTextmaster(int textId);

}
