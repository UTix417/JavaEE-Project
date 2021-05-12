package com.example.demo.pojo;

import lombok.*;

import java.util.Date;


/**
 * @author 朱威
 * @create 2021-05-12-14:06
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Text {
    private Integer textId;
    private String textTitle;
    private Integer textStatus;//0为封禁，1为正常，2为置顶
    private Date textCreateTime;
    private Integer blockId;
    private User user;
}
