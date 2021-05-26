package com.example.demo.mapper;

import com.example.demo.pojo.Text;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 朱威
 * @create 2021-05-12-15:15
 */
//本接口用来处理帖子主题
@Repository
public interface TextMapper {
    //按板块id获取该板块所有帖子信息
    public List<Text> getTextsByBlockId(Integer blockId);

    //按帖子标题查询帖子
    public List<Text> getTextsByTitle(String blockTitle);

    //向数据库中插入帖子，可以用来发帖,应当配合PageMapper中的addPage使用,在插入一个帖子时也插入一层楼,Text中的user属性只需要id，textStatus不需要封装默认为1
    public int addText(Text text);

    //根据帖子ID更新帖子信息，用于管理员管理帖子,暴露在外可以修改的应该只有帖子的状态
    public int updateText(Text text);

    //根据帖子ID删除帖子
    public int deleteText(Integer textId);

}
